package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuUsersDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.PopulerDto;

public interface BukuUsersService {
    public List<BukuUsersDto> getAllCollections();
    public List<BukuUsersDto> getNewCollections();
    public List<BukuUsersDto> getHotCollections();
    public List<PopulerDto> getHotCollections2();
    public List<BukuUsersDto> getDonationByIdUser(Integer id);
}
