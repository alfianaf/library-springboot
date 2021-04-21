package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.LokasiDto;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Lokasi;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.LokasiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LokasiServiceImpl implements LokasiService{
    @Autowired
    private LokasiRepository lokasiRepo;

   // Create lokasi Entity
   @Override
   public LokasiDto create(LokasiDto dto) {
       Lokasi lokasi = new Lokasi();

       lokasi.setKodeLokasi(dto.getKodeLokasi());
       lokasi.setKeteranganLokasi(dto.getKeteranganLokasi());
       lokasi.setDeleted(false);
       
       lokasiRepo.save(lokasi);

       return new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi());
   }

   // Get All lokasi (return without is deleted status)
   @Override
   public List<LokasiDto> getAll() {
       List<Lokasi> lokasis = lokasiRepo.findByIsDeleted(false);
       List<LokasiDto> lokasiDtos = new ArrayList<>();
       for (Lokasi lokasi : lokasis) {
           lokasiDtos.add(new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi()));
       }
       return lokasiDtos;
   }

   // Get lokasi by id (return without is deleted status)
   @Override
   public LokasiDto getById(Integer id) {
       Lokasi lokasi = lokasiRepo.findByIdLokasi(id);
       if(lokasi == null){
           return null;
       }
       else{
           return new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi());
       }
   }

   // Get lokasi by name (return without is deleted status)
   @Override
   public LokasiDto getByKode(String kodeLokasi) { 
       Lokasi lokasi = lokasiRepo.findByKodeLokasi(kodeLokasi);
       if(lokasi == null){
           return null;
       }
       else{
           return new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi());
       }
   }

   // Update kode of lokasi (return without is deleted status)
   @Override
   public LokasiDto update(LokasiDto dto) {
       Lokasi lokasi = lokasiRepo.findByIdLokasi(dto.getId());
       if(lokasi != null){
           lokasi.setKodeLokasi(dto.getKodeLokasi());
           lokasi.setKeteranganLokasi(dto.getKeteranganLokasi());
           lokasiRepo.save(lokasi);
           return new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi());
       }
       else{
           return null;
       }
   }

   // Soft deleted and return without is deleted status
   @Override
   public LokasiDto delete(Integer id) {
       Lokasi lokasi = lokasiRepo.findByIdLokasi(id);
       if(lokasi != null){
           lokasi.setDeleted(true);
           lokasiRepo.save(lokasi);
           return new LokasiDto(lokasi.getId(), lokasi.getKodeLokasi(), lokasi.getKeteranganLokasi());
       }
       else{
           return null;
       }
   }
    
}
