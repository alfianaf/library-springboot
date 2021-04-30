package com.libraryreact.libraryspringboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String name;
    private String url;
    private String type;
    private long size;
}
