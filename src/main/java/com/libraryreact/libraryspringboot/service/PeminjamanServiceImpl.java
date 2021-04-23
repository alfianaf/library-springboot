package com.libraryreact.libraryspringboot.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import javax.transaction.Transactional;

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

    @Override
    public StatusMessageDto<PeminjamanDto> sewaBuku(PeminjamanDto dto) {
        StatusMessageDto<PeminjamanDto> response = new StatusMessageDto<>();
        PeminjamanDto peminjamanDto = new PeminjamanDto();

        KodeBuku kodeBuku = kodeBukuRepo.findByKodeBukuAvailable(dto.getKodeBuku().getKodeBuku());
        Users peminjam = usersRepo.findByIdUser(dto.getPeminjam().getId());
        Users pencatat = usersRepo.findByIdUser(dto.getPencatat().getId());
        if(kodeBuku == null ){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Buku tidak tersedia atau sudah dipinjam ...");
            response.setData(dto);
            return response;
        }
        if(peminjam == null){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Data peminjam tidak ditemukan ...");
            response.setData(dto);
            return response;
        }
        if(pencatat == null){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Data admin pencatat tidak ditemukan");
            response.setData(dto);
            return response;
        }

        
        // save peminjaman
        Peminjaman sewa = new Peminjaman();

        sewa.setKodeBuku(kodeBuku);
        sewa.setPencatat(pencatat);
        sewa.setUser(peminjam);
        sewa.setTanggalPinjam(Timestamp.from(Instant.now()));

        Date batasPinjam = new Date( sewa.getTanggalPinjam().getTime());
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

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Buku berhasil disewa ...");
        response.setData(peminjamanDto);
        
        return response;
    }

}
