package com.libraryreact.libraryspringboot.models.dto.dataMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LokasiDto {
    private Integer id;
    private String kodeLokasi;
    private String keteranganLokasi;

}
