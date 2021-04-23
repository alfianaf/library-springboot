package com.libraryreact.libraryspringboot.models.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoDto {
    private Double kredit;
    private Double debit;
    private Double saldo;
    private Timestamp tanggal;
    private String transaksi;
}
