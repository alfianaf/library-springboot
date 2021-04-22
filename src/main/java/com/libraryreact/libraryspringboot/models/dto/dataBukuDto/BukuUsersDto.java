package com.libraryreact.libraryspringboot.models.dto.dataBukuDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class BukuUsersDto {
    private Integer id;
    private String judul;
    private String pengarang;
    private String tahun;
    private String isbn;
    private Double harga;
    private String deskripsi;
    private String sampul;
    private String kategori;
    private String penerbit;
    private String lokasi;
    private String genre;
    private List<KodeBukuUsersDto> kodeBuku;
}
