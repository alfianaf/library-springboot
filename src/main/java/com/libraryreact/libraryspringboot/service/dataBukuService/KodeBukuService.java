package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.KodeBukuDto;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;

public interface KodeBukuService {
    public KodeBukuDto create(KodeBukuDto dto);
    public List<KodeBukuDto> getAll();
    public List<KodeBukuDto> getByByBuku(Buku buku);
    public KodeBukuDto getById(Integer id);
    public KodeBukuDto getByKode(String kodeBuku);
    public KodeBukuDto update(KodeBukuDto dto);
    public KodeBukuDto delete(Integer id);
}
