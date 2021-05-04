package com.libraryreact.libraryspringboot.service;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.SaldoDto;
import com.libraryreact.libraryspringboot.models.entity.SaldoLog;

import org.springframework.http.ResponseEntity;

public interface SaldoLogService {

    public ResponseEntity<?> addSaldo(Integer id, SaldoDto saldoDto);
    public List<SaldoLog> getLogSaldoByIdUser(Integer id);

}
