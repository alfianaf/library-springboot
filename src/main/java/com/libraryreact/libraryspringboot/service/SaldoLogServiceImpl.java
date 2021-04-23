package com.libraryreact.libraryspringboot.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import com.libraryreact.libraryspringboot.models.dto.SaldoDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.entity.EStatusTransaksi;
import com.libraryreact.libraryspringboot.models.entity.SaldoLog;
import com.libraryreact.libraryspringboot.models.entity.StatusTransaksi;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.repository.SaldoLogRepository;
import com.libraryreact.libraryspringboot.repository.StatusTransaksiRepository;
import com.libraryreact.libraryspringboot.repository.UserDetailRepository;
import com.libraryreact.libraryspringboot.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaldoLogServiceImpl implements SaldoLogService {
    @Autowired
    SaldoLogRepository saldoLogRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    StatusTransaksiRepository statusTransaksiRepository;
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addSaldo(Integer id, SaldoDto saldoDto) {
        StatusMessageDto<SaldoLog> response = new StatusMessageDto<>();

        SaldoLog saldoLog = new SaldoLog();
        Users user = usersRepository.findById(id).get();
        UserDetail detailUser = userDetailRepository.findDetailByUserId(user.getId());
        // Date object
        Date date = new Date();
        // getTime() returns current time in milliseconds
        long time = date.getTime();
        // Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);

        saldoLog.setTanggal(ts);
        saldoLog.setUser(user);

        String statusTransaksi = saldoDto.getTransaksi();

        switch (statusTransaksi) {
        case "topup":
            try {
                StatusTransaksi topup = statusTransaksiRepository.findByName(EStatusTransaksi.TOPUP);
                saldoLog.setStatusTransaksi(topup);

                saldoLog.setDebit(saldoDto.getSaldo());
                saldoLog.setSaldo(detailUser.getSaldo() + saldoDto.getSaldo());
                detailUser.setSaldo(detailUser.getSaldo() + saldoDto.getSaldo());

                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Saldo sejumlah Rp. " + saldoDto.getSaldo()
                        + " berhasil ditambahkan, saldo akhir anda : Rp. " + detailUser.getSaldo());
            } catch (RuntimeException e) {
                throw new RuntimeException("Status transaksi tidak ditemukan!");
            }
            break;

        case "sewa":
            try {
                StatusTransaksi sewa = statusTransaksiRepository.findByName(EStatusTransaksi.SEWA);
                saldoLog.setStatusTransaksi(sewa);

                saldoLog.setKredit(saldoDto.getSaldo());
                saldoLog.setSaldo(detailUser.getSaldo() - saldoDto.getSaldo());
                detailUser.setSaldo(detailUser.getSaldo() - saldoDto.getSaldo());

                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Biaya sejumlah Rp. " + saldoDto.getSaldo()
                        + " dibebankan untuk sewa buku, saldo akhir anda : Rp. " + detailUser.getSaldo());
            } catch (RuntimeException e) {
                throw new RuntimeException("Status transaksi tidak ditemukan!");
            }
            break;

        case "denda":
            try {
                StatusTransaksi denda = statusTransaksiRepository.findByName(EStatusTransaksi.DENDA);
                saldoLog.setStatusTransaksi(denda);

                saldoLog.setKredit(saldoDto.getSaldo());
                saldoLog.setSaldo(detailUser.getSaldo() - saldoDto.getSaldo());
                detailUser.setSaldo(detailUser.getSaldo() - saldoDto.getSaldo());

                response.setStatus(HttpStatus.OK.value());
                response.setMessage("Biaya sejumlah Rp. " + saldoDto.getSaldo()
                        + " dibebankan untuk denda, saldo akhir anda : Rp. " + detailUser.getSaldo());
            } catch (RuntimeException e) {
                throw new RuntimeException("Status transaksi tidak ditemukan!");
            }
            break;
        }

        response.setData(saldoLog);

        saldoLogRepository.save(saldoLog);

        return ResponseEntity.ok(response);
    }

}
