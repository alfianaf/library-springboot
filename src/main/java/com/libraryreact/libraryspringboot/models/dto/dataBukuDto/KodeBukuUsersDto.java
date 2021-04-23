package com.libraryreact.libraryspringboot.models.dto.dataBukuDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KodeBukuUsersDto {
    private String kodeBuku;
    private Boolean isAvailable;
    private String donatur;
}
