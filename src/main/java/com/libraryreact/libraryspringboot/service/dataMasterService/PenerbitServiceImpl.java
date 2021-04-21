package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.PenerbitDto;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Penerbit;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.PenerbitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PenerbitServiceImpl implements PenerbitService{
    @Autowired
    private PenerbitRepository penerbitRepo;

    // Create Penerbit Entity
    @Override
    public PenerbitDto create(PenerbitDto dto) {
        Penerbit penerbit = new Penerbit();

        penerbit.setNamaPenerbit(dto.getNamaPenerbit());
        penerbit.setDeleted(false);
        
        penerbitRepo.save(penerbit);

        return new PenerbitDto(penerbit.getId(), penerbit.getNamaPenerbit());
    }

    // Get All Penerbit (return without is deleted status)
    @Override
    public List<PenerbitDto> getAll() {
        List<Penerbit> penerbits = penerbitRepo.findByIsDeleted(false);
        List<PenerbitDto> PenerbitDtos = new ArrayList<>();
        for (Penerbit penerbit : penerbits) {
            PenerbitDtos.add(new PenerbitDto(penerbit.getId(), penerbit.getNamaPenerbit()));
        }
        return PenerbitDtos;
    }

    // Get Penerbit by id (return without is deleted status)
    @Override
    public PenerbitDto getById(Integer id) {
        Penerbit penerbit = penerbitRepo.findByIdPenerbit(id);
        if(penerbit == null){
            return null;
        }
        else{
            return new PenerbitDto(penerbit.getId(), penerbit.getNamaPenerbit());
        }
    }

    // Get genre by name (return without is deleted status)
    @Override
    public PenerbitDto getByNama(String namaPenerbit) {
        Penerbit penerbit = penerbitRepo.findByNamaPenerbit(namaPenerbit);
        if(penerbit == null){
            return null;
        }
        else{
            return new PenerbitDto(penerbit.getId(), penerbit.getNamaPenerbit());
        }
    }

    // Update name of genre (return without is deleted status)
    @Override
    public PenerbitDto update(PenerbitDto dto) {
        Penerbit penerbit = penerbitRepo.findByIdPenerbit(dto.getId());
        if(penerbit != null){
            penerbit.setNamaPenerbit(dto.getNamaPenerbit());
            penerbitRepo.save(penerbit);
            return new PenerbitDto(dto.getId(), penerbit.getNamaPenerbit());
        }
        else{
            return null;
        }
    }

    // Soft deleted and return without is deleted status
    @Override
    public PenerbitDto delete(Integer id) {
        Penerbit penerbit = penerbitRepo.findByIdPenerbit(id);
        if(penerbit != null){
            penerbit.setDeleted(true);
            penerbitRepo.save(penerbit);
            return new PenerbitDto(penerbit.getId(), penerbit.getNamaPenerbit());
        }
        else{
            return null;
        }
    }
}
