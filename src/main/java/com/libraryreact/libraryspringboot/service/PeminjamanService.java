package com.libraryreact.libraryspringboot.service;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;

import org.springframework.http.ResponseEntity;

public interface PeminjamanService {
    public StatusMessageDto<PeminjamanDto> sewaBuku(PeminjamanDto dto);

    public ResponseEntity<?> pengembalianBuku(PeminjamanDto peminjamanDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getDetailById(Integer id);

}
