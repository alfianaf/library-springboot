package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.BukuUsersDto;
import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.KodeBukuUsersDto;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.KodeBuku;
import com.libraryreact.libraryspringboot.repository.PeminjamanRepository;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.BukuRepository;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.KodeBukuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BukuUsersServisImpl implements BukuUsersService{
    @Autowired 
    private BukuRepository bukuRepo;
    @Autowired
    private KodeBukuRepository kodeRepo;
    @Autowired
    private PeminjamanRepository peminjamanRepo;

    @Override
    public List<BukuUsersDto> getAllCollections() {
        List<BukuUsersDto> bukuUsersDtos = new ArrayList<>();
        List<Buku> bukus = bukuRepo.findByIsDeleted(false);
        for (Buku buku : bukus) {
            BukuUsersDto tempBuku = new BukuUsersDto();

            List<KodeBuku> kodeBukus = kodeRepo.findByBuku(buku);
            List<KodeBukuUsersDto> listKode = new ArrayList<>();
            for (KodeBuku kodeBuku : kodeBukus) {
                KodeBukuUsersDto tempKode = new KodeBukuUsersDto();

                String username = null;
                if(kodeBuku.getDonatur() != null){
                    username = kodeBuku.getDonatur().getUsername();
                }

                tempKode.setKodeBuku(kodeBuku.getKodeBuku());
                tempKode.setIsAvailable(kodeBuku.getIsAvailable());
                tempKode.setDonatur(username);

                listKode.add(tempKode);
            }

            tempBuku.setId(buku.getId());
            tempBuku.setJudul(buku.getJudul());
            tempBuku.setPengarang(buku.getPengarang());
            tempBuku.setTahun(buku.getTahunTerbit());
            tempBuku.setIsbn(buku.getIsbn());
            tempBuku.setHarga(buku.getHarga());
            tempBuku.setDeskripsi(buku.getDeskripsi());
            tempBuku.setSampul(buku.getSampul());
            tempBuku.setPenerbit(buku.getPenerbit().getNamaPenerbit());
            tempBuku.setKategori(buku.getKategori().getNamaKategori());
            tempBuku.setGenre(buku.getGenre().getNamaGenre());
            tempBuku.setLokasi(buku.getLokasi().getKodeLokasi());
            tempBuku.setKodeBuku(listKode);

            bukuUsersDtos.add(tempBuku);
        }

        return bukuUsersDtos;
    }
    
    
    @Override
    public List<BukuUsersDto> getNewCollections(){
        List<BukuUsersDto> bukuUsersDtos = new ArrayList<>();
        List<Buku> bukus = bukuRepo.findTerbaru();
        for (Buku buku : bukus) {
            BukuUsersDto tempBuku = new BukuUsersDto();

            List<KodeBuku> kodeBukus = kodeRepo.findByBuku(buku);
            List<KodeBukuUsersDto> listKode = new ArrayList<>();
            for (KodeBuku kodeBuku : kodeBukus) {
                KodeBukuUsersDto tempKode = new KodeBukuUsersDto();

                String username = null;
                if(kodeBuku.getDonatur() != null){
                    username = kodeBuku.getDonatur().getUsername();
                }

                tempKode.setKodeBuku(kodeBuku.getKodeBuku());
                tempKode.setIsAvailable(kodeBuku.getIsAvailable());
                tempKode.setDonatur(username);

                listKode.add(tempKode);
            }

            tempBuku.setId(buku.getId());
            tempBuku.setJudul(buku.getJudul());
            tempBuku.setPengarang(buku.getPengarang());
            tempBuku.setTahun(buku.getTahunTerbit());
            tempBuku.setIsbn(buku.getIsbn());
            tempBuku.setHarga(buku.getHarga());
            tempBuku.setDeskripsi(buku.getDeskripsi());
            tempBuku.setSampul(buku.getSampul());
            tempBuku.setPenerbit(buku.getPenerbit().getNamaPenerbit());
            tempBuku.setKategori(buku.getKategori().getNamaKategori());
            tempBuku.setGenre(buku.getGenre().getNamaGenre());
            tempBuku.setLokasi(buku.getLokasi().getKodeLokasi());
            tempBuku.setKodeBuku(listKode);

            bukuUsersDtos.add(tempBuku);
        }

        return bukuUsersDtos;
    }


    @Override
    public List<BukuUsersDto> getHotCollections() {
        Set<Integer> bukuPopuler = peminjamanRepo.findIdBukuTerpopuler();
        List<BukuUsersDto> bukuUsersDtos = new ArrayList<>();

        for (Integer idBukuPopuler : bukuPopuler) {
            Buku buku = bukuRepo.findByIdBuku(idBukuPopuler);
            BukuUsersDto tempBuku = new BukuUsersDto();

            List<KodeBuku> kodeBukus = kodeRepo.findByBuku(buku);
            List<KodeBukuUsersDto> listKode = new ArrayList<>();
            for (KodeBuku kodeBuku : kodeBukus) {
                KodeBukuUsersDto tempKode = new KodeBukuUsersDto();

                String username = null;
                if(kodeBuku.getDonatur() != null){
                    username = kodeBuku.getDonatur().getUsername();
                }

                tempKode.setKodeBuku(kodeBuku.getKodeBuku());
                tempKode.setIsAvailable(kodeBuku.getIsAvailable());
                tempKode.setDonatur(username);

                listKode.add(tempKode);
            }

            tempBuku.setId(buku.getId());
            tempBuku.setJudul(buku.getJudul());
            tempBuku.setPengarang(buku.getPengarang());
            tempBuku.setTahun(buku.getTahunTerbit());
            tempBuku.setIsbn(buku.getIsbn());
            tempBuku.setHarga(buku.getHarga());
            tempBuku.setDeskripsi(buku.getDeskripsi());
            tempBuku.setSampul(buku.getSampul());
            tempBuku.setPenerbit(buku.getPenerbit().getNamaPenerbit());
            tempBuku.setKategori(buku.getKategori().getNamaKategori());
            tempBuku.setGenre(buku.getGenre().getNamaGenre());
            tempBuku.setLokasi(buku.getLokasi().getKodeLokasi());
            tempBuku.setKodeBuku(listKode);

            bukuUsersDtos.add(tempBuku);

        }

        return bukuUsersDtos;
    }
}
