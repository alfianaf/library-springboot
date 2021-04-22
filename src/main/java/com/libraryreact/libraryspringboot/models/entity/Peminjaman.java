package com.libraryreact.libraryspringboot.models.entity;

import java.sql.Date;
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
@Table(name = "peminjaman")
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Timestamp tanggalPinjam;

    @Column
    private Date batasPinjam;

    @Column
    private Date tanggalPengembalian;

    @Column
    private Double harga;

    @Column
    private Double denda;

    @Column
    private boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "pencatatId", referencedColumnName = "id")
    private Users pencatat;

    // @ManyToOne
    // @JoinColumn(name = "kodeBuku", referencedColumnName = "kode_buku")
    @Column(unique = true, nullable = false)
    private String kodeBuku;
}
