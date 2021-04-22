package com.libraryreact.libraryspringboot.models.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nama;

    @Column
    private String nik;

    @Column
    private String tempatLahir;

    @Column
    private Date tanggalLahir;

    @Column
    private String alamat;

    @Column
    private String telp;

    @Column
    private String kelamin;

    @Column
    private Double saldo;

    @Column
    private String foto;

    @Column
    private Boolean isActive;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users user;

    public UserDetail(String nama, String nik, String tempatLahir, Date tanggalLahir, String alamat, String telp,
            String kelamin, Double saldo, String foto, Boolean isActive) {
        this.nama = nama;
        this.nik = nik;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.telp = telp;
        this.kelamin = kelamin;
        this.saldo = saldo;
        this.foto = foto;
        this.isActive = isActive;
    }

}
