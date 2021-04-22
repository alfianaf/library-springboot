package com.libraryreact.libraryspringboot.models.dto.dataBukuDto;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.KategoriDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.LokasiDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.PenerbitDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BukuDto {
    private Integer id;
    private String judul;
    private String pengarang;
    private String tahunTerbit;
    private String isbn;
    private Double harga;
    private String deskripsi;
    private String sampul;
    private KategoriDto kategori;
    private PenerbitDto penerbit;
    private LokasiDto lokasi;
    private GenreDto genre;
}
