package com.libraryreact.libraryspringboot.service;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;
import com.libraryreact.libraryspringboot.models.entity.Peminjaman;

import org.springframework.http.ResponseEntity;

public interface PeminjamanService {
    public StatusMessageDto<PeminjamanDto> sewaBuku(PeminjamanDto dto);
    public StatusMessageDto<List<Peminjaman>> riwayatSewaByPeminjam(Integer id);


    public ResponseEntity<?> pengembalianBuku(PeminjamanDto peminjamanDto);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getDetailById(Integer id);

}
