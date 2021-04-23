package com.libraryreact.libraryspringboot.models.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "saldo_log")
public class SaldoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double kredit;

    @Column
    private Double debit;

    @Column
    private Double saldo;

    @Column
    private Timestamp tanggal;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "peminjaman_id", referencedColumnName = "id")
    private Peminjaman peminjaman;

    @ManyToOne
    @JoinColumn(name = "statusTransaksiId", referencedColumnName = "id")
    private StatusTransaksi statusTransaksi;
}
