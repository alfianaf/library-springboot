package com.libraryreact.libraryspringboot.service;

import com.libraryreact.libraryspringboot.models.dto.SaldoDto;

import org.springframework.http.ResponseEntity;

public interface SaldoLogService {

    public ResponseEntity<?> addSaldo(Integer id, SaldoDto saldoDto);

}
