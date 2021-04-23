package com.libraryreact.libraryspringboot.service;

import com.libraryreact.libraryspringboot.models.dto.PeminjamanDto;
import com.libraryreact.libraryspringboot.models.dto.StatusMessageDto;

public interface PeminjamanService {
    public StatusMessageDto<PeminjamanDto> sewaBuku(PeminjamanDto dto);
}
