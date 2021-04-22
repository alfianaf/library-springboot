package com.libraryreact.libraryspringboot.models.dto;

import java.sql.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private String username;
    private String password;
    private Set<String> role;
    private String nik;
    private String nama;
    private String tempatLahir;
    private Date tanggalLahir;
    private String alamat;
    private String telp;
    private String kelamin;
    private Double saldo;
    private String foto;
    private Integer isActive;

}
