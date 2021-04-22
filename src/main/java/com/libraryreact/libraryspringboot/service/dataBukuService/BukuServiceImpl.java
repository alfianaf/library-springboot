package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.GenreDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.KategoriDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.LokasiDto;
import com.libraryreact.libraryspringboot.models.dto.dataMasterDto.PenerbitDto;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Genre;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Kategori;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Lokasi;
import com.libraryreact.libraryspringboot.models.entity.dataMaster.Penerbit;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.BukuRepository;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.GenreRepository;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.KategoriRepository;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.LokasiRepository;
import com.libraryreact.libraryspringboot.repository.dataMasterRepository.PenerbitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BukuServiceImpl implements BukuService {
    @Autowired
    private BukuRepository bukuRepo;
    @Autowired
    private KategoriRepository katRepo;
    @Autowired
    private GenreRepository genRepo;
    @Autowired
    private PenerbitRepository penerbitRepo;
    @Autowired
    private LokasiRepository lokRepo;

    @Override
    public BukuDto create(BukuDto dto) {
        Buku buku = new Buku();
        Kategori kategori = katRepo.findByIdKategori(dto.getKategori().getId());
        Genre genre = genRepo.findByIdGenre(dto.getGenre().getId());
        Penerbit penerbit = penerbitRepo.findByIdPenerbit(dto.getPenerbit().getId());
        Lokasi lokasi = lokRepo.findByIdLokasi(dto.getLokasi().getId());
        
        
        buku.setJudul(dto.getJudul());
        buku.setPengarang(dto.getPengarang());
        buku.setTahunTerbit(dto.getTahunTerbit());
        buku.setIsbn(dto.getIsbn());
        buku.setHarga(dto.getHarga());
        buku.setDeskripsi(dto.getDeskripsi());
        buku.setSampul(dto.getSampul());
        buku.setIsDeleted(false);
        buku.setKategori(kategori);
        buku.setGenre(genre);
        buku.setLokasi(lokasi);
        buku.setPenerbit(penerbit);
        // save to repository
        bukuRepo.save(buku);

        // set new book dto from inserted book
        BukuDto newBook = new BukuDto();
        KategoriDto kategoriDto = new KategoriDto(buku.getKategori().getId(), buku.getKategori().getKodeKategori(), buku.getKategori().getNamaKategori());
    
        newBook.setJudul(buku.getJudul());
        newBook.setPengarang(buku.getPengarang());
        newBook.setTahunTerbit(buku.getTahunTerbit());
        newBook.setIsbn(buku.getIsbn());
        newBook.setHarga(buku.getHarga());
        newBook.setDeskripsi(buku.getDeskripsi());
        newBook.setSampul(buku.getSampul());
        newBook.setKategori(kategoriDto);
        newBook.setGenre(new GenreDto(buku.getGenre().getId(), buku.getGenre().getNamaGenre()));
        newBook.setLokasi(new LokasiDto(buku.getLokasi().getId(), buku.getLokasi().getKodeLokasi(), buku.getLokasi().getKeteranganLokasi()));
        newBook.setPenerbit(new PenerbitDto(buku.getPenerbit().getId(), buku.getPenerbit().getNamaPenerbit()));

        return newBook;
    }

    @Override
    public List<BukuDto> getAll() {
        List<Buku> bukus = bukuRepo.findByIsDeleted(false);
        List<BukuDto> bukuDtos = new ArrayList<>();
        for (Buku buku : bukus) {
            BukuDto tempBook = new BukuDto();
            tempBook.setId(buku.getId());
            tempBook.setJudul(buku.getJudul());
            tempBook.setPengarang(buku.getPengarang());
            tempBook.setTahunTerbit(buku.getTahunTerbit());
            tempBook.setIsbn(buku.getIsbn());
            tempBook.setHarga(buku.getHarga());
            tempBook.setDeskripsi(buku.getDeskripsi());
            tempBook.setSampul(buku.getSampul());
            tempBook.setKategori(new KategoriDto(buku.getKategori().getId(), buku.getKategori().getKodeKategori(), buku.getKategori().getNamaKategori()));
            tempBook.setGenre(new GenreDto(buku.getGenre().getId(), buku.getGenre().getNamaGenre()));
            tempBook.setLokasi(new LokasiDto(buku.getLokasi().getId(), buku.getLokasi().getKodeLokasi(), buku.getLokasi().getKeteranganLokasi()));
            tempBook.setPenerbit(new PenerbitDto(buku.getPenerbit().getId(), buku.getPenerbit().getNamaPenerbit()));

            bukuDtos.add(tempBook);
        }
        return bukuDtos;
    }

    @Override
    public BukuDto getById(Integer id) {
        Buku buku = bukuRepo.findByIdBuku(id);
        if(buku == null){
            return null;
        }
        else{
            BukuDto tempBook = new BukuDto();
            tempBook.setId(buku.getId());
            tempBook.setJudul(buku.getJudul());
            tempBook.setPengarang(buku.getPengarang());
            tempBook.setTahunTerbit(buku.getTahunTerbit());
            tempBook.setIsbn(buku.getIsbn());
            tempBook.setHarga(buku.getHarga());
            tempBook.setDeskripsi(buku.getDeskripsi());
            tempBook.setSampul(buku.getSampul());
            tempBook.setKategori(new KategoriDto(buku.getKategori().getId(), buku.getKategori().getKodeKategori(), buku.getKategori().getNamaKategori()));
            tempBook.setGenre(new GenreDto(buku.getGenre().getId(), buku.getGenre().getNamaGenre()));
            tempBook.setLokasi(new LokasiDto(buku.getLokasi().getId(), buku.getLokasi().getKodeLokasi(), buku.getLokasi().getKeteranganLokasi()));
            tempBook.setPenerbit(new PenerbitDto(buku.getPenerbit().getId(), buku.getPenerbit().getNamaPenerbit()));

            return tempBook;
        }
    }

    @Override
    public BukuDto update(BukuDto dto) {
        Buku buku = bukuRepo.findByIdBuku(dto.getId());
        if(buku != null){
            Kategori kategori = katRepo.findByIdKategori(dto.getKategori().getId());
            Genre genre = genRepo.findByIdGenre(dto.getGenre().getId());
            Penerbit penerbit = penerbitRepo.findByIdPenerbit(dto.getPenerbit().getId());
            Lokasi lokasi = lokRepo.findByIdLokasi(dto.getLokasi().getId());
            
            
            buku.setJudul(dto.getJudul());
            buku.setPengarang(dto.getPengarang());
            buku.setTahunTerbit(dto.getTahunTerbit());
            buku.setIsbn(dto.getIsbn());
            buku.setHarga(dto.getHarga());
            buku.setDeskripsi(dto.getDeskripsi());
            buku.setSampul(dto.getSampul());
            buku.setKategori(kategori);
            buku.setGenre(genre);
            buku.setLokasi(lokasi);
            buku.setPenerbit(penerbit);
            // save to repository
            bukuRepo.save(buku);
    
            // set new book dto from inserted book
            BukuDto editedBook = new BukuDto();
            editedBook.setId(buku.getId());
            editedBook.setJudul(buku.getJudul());
            editedBook.setPengarang(buku.getPengarang());
            editedBook.setTahunTerbit(buku.getTahunTerbit());
            editedBook.setIsbn(buku.getIsbn());
            editedBook.setHarga(buku.getHarga());
            editedBook.setDeskripsi(buku.getDeskripsi());
            editedBook.setSampul(buku.getSampul());
            editedBook.setKategori(new KategoriDto(buku.getKategori().getId(), buku.getKategori().getKodeKategori(), buku.getKategori().getNamaKategori()));
            editedBook.setGenre(new GenreDto(buku.getGenre().getId(), buku.getGenre().getNamaGenre()));
            editedBook.setLokasi(new LokasiDto(buku.getLokasi().getId(), buku.getLokasi().getKodeLokasi(), buku.getLokasi().getKeteranganLokasi()));
            editedBook.setPenerbit(new PenerbitDto(buku.getPenerbit().getId(), buku.getPenerbit().getNamaPenerbit()));
    
            return editedBook;
        }
        else{
            return null;
        }
    }

    @Override
    public BukuDto delete(Integer id) {
        Buku buku = bukuRepo.findByIdBuku(id);
        if(buku != null){
            buku.setIsDeleted(true);
            bukuRepo.save(buku);

            BukuDto deletedBook = new BukuDto();
            deletedBook.setId(buku.getId());
            deletedBook.setJudul(buku.getJudul());
            deletedBook.setPengarang(buku.getPengarang());
            deletedBook.setTahunTerbit(buku.getTahunTerbit());
            deletedBook.setIsbn(buku.getIsbn());
            deletedBook.setHarga(buku.getHarga());
            deletedBook.setDeskripsi(buku.getDeskripsi());
            deletedBook.setSampul(buku.getSampul());
            deletedBook.setKategori(new KategoriDto(buku.getKategori().getId(), buku.getKategori().getKodeKategori(), buku.getKategori().getNamaKategori()));
            deletedBook.setGenre(new GenreDto(buku.getGenre().getId(), buku.getGenre().getNamaGenre()));
            deletedBook.setLokasi(new LokasiDto(buku.getLokasi().getId(), buku.getLokasi().getKodeLokasi(), buku.getLokasi().getKeteranganLokasi()));
            deletedBook.setPenerbit(new PenerbitDto(buku.getPenerbit().getId(), buku.getPenerbit().getNamaPenerbit()));

            return deletedBook;
        }
        else{
            return null;
        }
    }
    
}
