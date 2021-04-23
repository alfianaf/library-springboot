package com.libraryreact.libraryspringboot.models.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDto {
    private Timestamp tanggalPinjam;
    private Date batasPinjam;
    private Date tanggalPengembalian;
    private Double harga;
    private Double denda;
    private boolean isFinished;
}
