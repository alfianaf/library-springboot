package com.libraryreact.libraryspringboot.models.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.KodeBuku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDto {
    private Integer id;
    private KodeBuku kodeBuku; // nyimpen kode buku yang dipinjam
    private Timestamp tanggalPinjam;
    private Date batasPinjam;
    private Timestamp tanggalPengembalian;
    private Double harga;
    private Double denda;
    private boolean isFinished;
    private Users peminjam;
    private Users pencatat;
}
