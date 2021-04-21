package com.libraryreact.libraryspringboot.models.dto.dataMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KategoriDto {
    private Integer id;
    private String kodeKategori;
    private String namaKategori;
}
