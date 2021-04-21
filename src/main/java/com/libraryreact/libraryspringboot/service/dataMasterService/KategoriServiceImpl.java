package com.libraryreact.libraryspringboot.service.dataMasterService;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.KategoriDto;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Kategori;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.KategoriRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KategoriServiceImpl implements KategoriService{
    @Autowired
    private KategoriRepository katRepo;

   // Create lokasi Entity
   @Override
   public KategoriDto create(KategoriDto dto) {
       Kategori kategori = new Kategori();

       kategori.setKodeKategori(dto.getKodeKategori());
       kategori.setNamaKategori(dto.getNamaKategori());
       kategori.setDeleted(false);
       
       katRepo.save(kategori);

       return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
   }

   // Get All lokasi (return without is deleted status)
   @Override
   public List<KategoriDto> getAll() {
       List<Kategori> kategoris = katRepo.findByIsDeleted(false);
       List<KategoriDto> kategoriDtos = new ArrayList<>();
       for (Kategori kategori : kategoris) {
           kategoriDtos.add(new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori()));
       }
       return kategoriDtos;
   }

   // Get lokasi by id (return without is deleted status)
   @Override
   public KategoriDto getById(Integer id) {
       Kategori kategori = katRepo.findByIdKategori(id);
       if(kategori == null){
           return null;
       }
       else{
           return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
       }
   }

   // Get lokasi by kode (return without is deleted status)
   @Override
   public KategoriDto getByKode(String kodeLokasi) { 
       Kategori kategori = katRepo.findByKodeKategori(kodeLokasi);
       if(kategori == null){
           return null;
       }
       else{
           return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
       }
   }

//    Get kategori by nama (return without is deletd status)
   @Override
    public KategoriDto getByNama(String namaKategori) {
        Kategori kategori = katRepo.findByNamaKategori(namaKategori);
        if(kategori == null){
            return null;
        }
        else{
            return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
        }
    }

   // Update kode of lokasi (return without is deleted status)
   @Override
   public KategoriDto update(KategoriDto dto) {
       Kategori kategori = katRepo.findByIdKategori(dto.getId());
       if(kategori != null){
           kategori.setKodeKategori(dto.getKodeKategori());
           kategori.setNamaKategori(dto.getNamaKategori());
           katRepo.save(kategori);
           return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
       }
       else{
           return null;
       }
   }

   // Soft deleted and return without is deleted status
   @Override
   public KategoriDto delete(Integer id) {
       Kategori kategori = katRepo.findByIdKategori(id);
       if(kategori != null){
           kategori.setDeleted(true);
           katRepo.save(kategori);
           return new KategoriDto(kategori.getId(), kategori.getKodeKategori(), kategori.getNamaKategori());
       }
       else{
           return null;
       }
   }
}
