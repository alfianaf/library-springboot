package com.libraryreact.libraryspringboot.models.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private String username;
    private String password;
    private String nik;
    private String nama;
    private String tempatLahir;
    private Date tanggalLahir;
    private String alamat;
    private String telp;
    private String kelamin;
    private Integer saldo;
    private String foto;
    private Integer isActive;

}
