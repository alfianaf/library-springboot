package com.libraryreact.libraryspringboot.models.dto.dataBukuDto;

import java.sql.Timestamp;

import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KodeBukuDto {
    private Integer id;
    private String kodeBuku;
    private Timestamp createdAt;
    private Boolean isAvailable;
    private Users donatur;
    private Buku buku;
}
