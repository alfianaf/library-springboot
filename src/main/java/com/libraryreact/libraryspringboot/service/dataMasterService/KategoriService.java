package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.KategoriDto;

public interface KategoriService {
    public KategoriDto create(KategoriDto dto);
    public List<KategoriDto> getAll();
    public KategoriDto getById(Integer id);
    public KategoriDto getByKode(String kodeKategori);
    public KategoriDto getByNama(String namaKategori);
    public KategoriDto update(KategoriDto dto);
    public KategoriDto delete(Integer id);
}
