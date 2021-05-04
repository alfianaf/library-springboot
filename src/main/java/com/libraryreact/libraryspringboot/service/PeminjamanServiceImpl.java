package com.libraryreact.libraryspringboot.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import com.libraryreact.libraryspringboot.config.JwtUtils;
import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.entity.EStatusTransaksi;
import com.libraryreact.libraryspringboot.models.entity.Peminjaman;
import com.libraryreact.libraryspringboot.models.entity.SaldoLog;
import com.libraryreact.libraryspringboot.models.entity.StatusTransaksi;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.KodeBuku;
import com.libraryreact.libraryspringboot.repository.PeminjamanRepository;
import com.libraryreact.libraryspringboot.repository.SaldoLogRepository;
import com.libraryreact.libraryspringboot.repository.StatusTransaksiRepository;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.KodeBukuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PeminjamanServiceImpl implements PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepo;
    @Autowired
    private KodeBukuRepository kodeBukuRepo;
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private UserDetailRepository userDetailRepo;
    @Autowired
    private SaldoLogRepository saldoRepo;
    @Autowired
    private StatusTransaksiRepository statusTransaksiRepo;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> pengembalianBuku(PeminjamanDto peminjamanDto) {
        StatusMessageDto<Peminjaman> response = new StatusMessageDto<>();
        Integer idUser = peminjamanDto.getIdUser();
        Users user = usersRepo.findById(idUser).get();

        SaldoLog saldoLog = new SaldoLog();
        StatusTransaksi statusTransaksi = statusTransaksiRepo.findByName(EStatusTransaksi.DENDA);
        System.out.println(statusTransaksi);
        Peminjaman peminjaman = peminjamanRepo.findById(peminjamanDto.getId()).get();
        UserDetail detailUser = userDetailRepo.findDetailByUserId(peminjaman.getUser().getId());
        KodeBuku kodeBuku = kodeBukuRepo.findByKodeBuku(peminjaman.getKodeBuku().getKodeBuku());

        peminjaman.setTanggalPengembalian(Timestamp.from(Instant.now()));
        peminjaman.setPencatat(user);
        Date tanggalKembali = new Date(peminjaman.getTanggalPengembalian().getTime());
        Date batasPinjam = peminjaman.getBatasPinjam();
        long difference = batasPinjam.getTime() - tanggalKembali.getTime();
        long daysDiff = (difference / (1000 * 60 * 60 * 24)) % 365;
        if (daysDiff >= 0) {
            peminjaman.setDenda(0.0);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Silahkan letakkan buku pada rak terkait");
        }
        if (daysDiff < 0) {
            peminjaman.setDenda(100.0);
            if (detailUser.getSaldo() < peminjaman.getDenda()) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Saldo tidak mencukupi ...");
                response.setData(peminjaman);
                return ResponseEntity.badRequest().body(response);
            }
            saldoLog.setDebit(peminjaman.getDenda());
            saldoLog.setSaldo(detailUser.getSaldo() - peminjaman.getDenda());
            detailUser.setSaldo(detailUser.getSaldo() - peminjaman.getDenda());
            saldoLog.setUser(peminjaman.getUser());
            saldoLog.setTanggal(Timestamp.from(Instant.now()));
            saldoLog.setStatusTransaksi(statusTransaksi);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Biaya sejumlah Rp. " + peminjaman.getDenda()
                    + " dibebankan untuk denda, saldo akhir anda : Rp. " + detailUser.getSaldo());

        }
        saldoLog.setPeminjaman(peminjaman);
        peminjaman.setFinished(true);
        kodeBuku.setIsAvailable(true);
        peminjamanRepo.save(peminjaman);
        saldoRepo.save(saldoLog);
        userDetailRepo.save(detailUser);
        response.setData(peminjaman);

        return ResponseEntity.ok(response);
    }

    @Override
    public StatusMessageDto<PeminjamanDto> sewaBuku(PeminjamanDto dto) {
        StatusMessageDto<PeminjamanDto> response = new StatusMessageDto<>();
        PeminjamanDto peminjamanDto = new PeminjamanDto();

        KodeBuku kodeBuku = kodeBukuRepo.findByKodeBukuAvailable(dto.getKodeBuku().getKodeBuku());
        Users peminjam = usersRepo.findByUsernameUser(dto.getPeminjam().getUsername());
        UserDetail peminjamDetail = userDetailRepo.findDetailByUserId(peminjam.getId());
        Users pencatat = usersRepo.findByIdUser(dto.getPencatat().getId());
        // validasi kode buku tidak ditemukan
        if (kodeBuku == null) {
            response.setStatus(HttpStatus.GONE.value());
            response.setMessage("Buku tidak tersedia atau sudah dipinjam ...");
            response.setData(dto);
            return response;
        }
        // validasi pencatat tidak
        if (pencatat == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Data admin pencatat tidak ditemukan");
            response.setData(dto);
            return response;
        }
        // validasi saldo tidak mencukupi
        if (peminjamDetail.getSaldo() < dto.getHarga()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Saldo tidak mencukupi ...");
            response.setData(dto);
            return response;
        }

        // save peminjaman
        Peminjaman sewa = new Peminjaman();

        sewa.setKodeBuku(kodeBuku);
        sewa.setPencatat(pencatat);
        sewa.setUser(peminjam);
        sewa.setTanggalPinjam(Timestamp.from(Instant.now()));

        Date batasPinjam = new Date(sewa.getTanggalPinjam().getTime());
        // batasPinjam.toLocalDate().plusDays(7);
        LocalDate temp = batasPinjam.toLocalDate().plusDays(7);
        sewa.setBatasPinjam(Date.valueOf(temp));
        sewa.setTanggalPengembalian(null);
        sewa.setHarga(dto.getHarga());
        sewa.setDenda(null);
        sewa.setFinished(false);

        peminjamanRepo.save(sewa);

        // update is available in kode buku
        kodeBuku.setIsAvailable(false);
        kodeBukuRepo.save(kodeBuku);

        // save debit saldo and save to log saldo
        SaldoLog saldoLog = new SaldoLog();
        UserDetail detailPeminjam = userDetailRepo.findDetailByUserId(peminjam.getId());
        StatusTransaksi statusSewa = statusTransaksiRepo.findByName(EStatusTransaksi.SEWA);

        saldoLog.setUser(peminjam);
        saldoLog.setPeminjaman(sewa);
        saldoLog.setStatusTransaksi(statusSewa);
        saldoLog.setKredit(null);
        saldoLog.setDebit(sewa.getHarga());
        saldoLog.setSaldo(detailPeminjam.getSaldo() - sewa.getHarga());
        saldoLog.setTanggal(Timestamp.from(Instant.now()));
        saldoRepo.save(saldoLog);

        // update saldo in detail user
        detailPeminjam.setSaldo(saldoLog.getSaldo());
        userDetailRepo.save(detailPeminjam);

        // set sewa result to peminjaman dto for return value
        peminjamanDto.setId(sewa.getId());
        peminjamanDto.setPeminjam(sewa.getUser());
        peminjamanDto.setPencatat(sewa.getPencatat());
        peminjamanDto.setKodeBuku(sewa.getKodeBuku());
        peminjamanDto.setTanggalPinjam(sewa.getTanggalPinjam());
        peminjamanDto.setBatasPinjam(sewa.getBatasPinjam());
        peminjamanDto.setTanggalPengembalian(sewa.getTanggalPengembalian());
        peminjamanDto.setHarga(sewa.getHarga());
        peminjamanDto.setDenda(sewa.getDenda());

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Buku berhasil disewa ...");
        response.setData(peminjamanDto);

        return response;
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {
            StatusMessageDto<List<Peminjaman>> response = new StatusMessageDto<>();
            List<Peminjaman> peminjaman = peminjamanRepo.findByIsFinished(false);
            if (peminjaman.size() == 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Peminjaman Tidak ditemukan!");
                response.setData(peminjaman);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Peminjaman ditemukan!");
                response.setData(peminjaman);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    public ResponseEntity<?> getDetailById(Integer id) {
        Peminjaman peminjaman = peminjamanRepo.findDetailById(id);

        return ResponseEntity.ok().body(peminjaman);
    }

    // menampilkan semua (selesai dan belum dikembalikan) riwayat sewa berdasarkan
    // id peminjammnya
    @Override
    public StatusMessageDto<List<Peminjaman>> riwayatSewaByPeminjam(Integer id) {
        StatusMessageDto<List<Peminjaman>> response = new StatusMessageDto<>();

        List<Peminjaman> peminjaman = peminjamanRepo.getriwayatsewa(id);
        System.out.println(peminjaman);
        if (peminjaman.size() == 0) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Peminjaman Tidak ditemukan!");
            response.setData(peminjaman);
            return response;
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Peminjaman ditemukan!");
            response.setData(peminjaman);
            return response;
        }
    }

    // mengambil buku yang sedang disewa saja
    @Override
    public StatusMessageDto<List<Peminjaman>> sedangDisewaByPeminjam(Integer id) {
        StatusMessageDto<List<Peminjaman>> response = new StatusMessageDto<>();

        List<Peminjaman> peminjaman = peminjamanRepo.getSedangDisewa(id);
        System.out.println(peminjaman);
        if (peminjaman.size() == 0) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Peminjaman Tidak ditemukan!");
            response.setData(peminjaman);
            return response;
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Peminjaman ditemukan!");
            response.setData(peminjaman);
            return response;
        }
    }

    // mengambil riwayat buku yang berstatus sudah selesai
    @Override
    public StatusMessageDto<List<Peminjaman>> riwayatSewaSelesaiByPeminjam(Integer id) {
        StatusMessageDto<List<Peminjaman>> response = new StatusMessageDto<>();

        List<Peminjaman> peminjaman = peminjamanRepo.getRiwayatSewaSelesai(id);
        System.out.println(peminjaman);
        if (peminjaman.size() == 0) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Peminjaman Tidak ditemukan!");
            response.setData(peminjaman);
            return response;
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Peminjaman ditemukan!");
            response.setData(peminjaman);
            return response;
        }
    }

}
