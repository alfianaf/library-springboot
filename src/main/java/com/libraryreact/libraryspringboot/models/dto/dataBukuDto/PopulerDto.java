package com.libraryreact.libraryspringboot.models.dto.dataBukuDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopulerDto {
    private Integer id;
    private String judul;
    private Integer total;
}
