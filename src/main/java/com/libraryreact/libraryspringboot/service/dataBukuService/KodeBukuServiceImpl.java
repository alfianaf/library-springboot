package com.libraryreact.libraryspringboot.service.dataBukuService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.dataBukuDto.KodeBukuDto;
import com.libraryreact.libraryspringboot.models.entity.Users;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.KodeBuku;
import com.libraryreact.libraryspringboot.repository.UsersRepository;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.BukuRepository;
import com.libraryreact.libraryspringboot.repository.dataBukuRepository.KodeBukuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KodeBukuServiceImpl implements KodeBukuService {
    @Autowired
    private KodeBukuRepository kodeBukuRepo;
    @Autowired
    private BukuRepository bukuRepo;
    @Autowired
    private UsersRepository userRepo;

    @Override
    public KodeBukuDto create(KodeBukuDto dto) {
        KodeBuku kodeBuku = new KodeBuku();
        Buku buku = bukuRepo.findByIdBuku(dto.getBuku().getId());
        Users donatur = null;
        if (dto.getDonatur().getUsername() != null) {
            donatur = userRepo.findByUsername(dto.getDonatur().getUsername());
        }

        String generateCode = buku.getKategori().getKodeKategori() + "-" + buku.getLokasi().getKodeLokasi() + "-"
                + kodeBuku.getId();

        kodeBuku.setKodeBuku(generateCode);
        kodeBuku.setCreatedAt(Timestamp.from(Instant.now()));
        kodeBuku.setIsAvailable(true);
        kodeBuku.setIsDeleted(false);
        kodeBuku.setDonatur(donatur);
        kodeBuku.setBuku(buku);

        kodeBukuRepo.save(kodeBuku);

        generateCode = buku.getKategori().getKodeKategori() + "-" + buku.getLokasi().getKodeLokasi() + "-"
                + kodeBuku.getId();
        kodeBuku.setKodeBuku(generateCode);
        kodeBukuRepo.save(kodeBuku);

        KodeBukuDto newKode = new KodeBukuDto();
        newKode.setId(kodeBuku.getId());
        newKode.setKodeBuku(kodeBuku.getKodeBuku());
        newKode.setCreatedAt(kodeBuku.getCreatedAt());
        newKode.setIsAvailable(kodeBuku.getIsAvailable());
        newKode.setDonatur(kodeBuku.getDonatur());
        newKode.setBuku(kodeBuku.getBuku());

        return newKode;
    }

    @Override
    public List<KodeBukuDto> getAllAvailable() {
        List<KodeBuku> kodeBukus = kodeBukuRepo.findByKodeBukuAvailableAll();
        List<KodeBukuDto> kodeBukuDtos = new ArrayList<>();

        for (KodeBuku kodeBuku : kodeBukus) {
            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodeBuku.getId());
            temp.setKodeBuku(kodeBuku.getKodeBuku());
            temp.setCreatedAt(kodeBuku.getCreatedAt());
            temp.setIsAvailable(kodeBuku.getIsAvailable());
            temp.setDonatur(kodeBuku.getDonatur());
            temp.setBuku(kodeBuku.getBuku());

            kodeBukuDtos.add(temp);
        }
        return kodeBukuDtos;
    }

    @Override
    public List<KodeBukuDto> getAll() {
        List<KodeBuku> kodeBukus = kodeBukuRepo.findByIsDeleted(false);
        List<KodeBukuDto> kodeBukuDtos = new ArrayList<>();

        for (KodeBuku kodeBuku : kodeBukus) {
            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodeBuku.getId());
            temp.setKodeBuku(kodeBuku.getKodeBuku());
            temp.setCreatedAt(kodeBuku.getCreatedAt());
            temp.setIsAvailable(kodeBuku.getIsAvailable());
            temp.setDonatur(kodeBuku.getDonatur());
            temp.setBuku(kodeBuku.getBuku());

            kodeBukuDtos.add(temp);
        }
        return kodeBukuDtos;
    }

    @Override
    public List<KodeBukuDto> getByByBuku(Buku buku) {
        List<KodeBuku> kodeBukus = kodeBukuRepo.findByBuku(buku);
        List<KodeBukuDto> kodeBukuDtos = new ArrayList<>();

        for (KodeBuku kodeBuku : kodeBukus) {
            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodeBuku.getId());
            temp.setKodeBuku(kodeBuku.getKodeBuku());
            temp.setCreatedAt(kodeBuku.getCreatedAt());
            temp.setIsAvailable(kodeBuku.getIsAvailable());
            temp.setDonatur(kodeBuku.getDonatur());
            temp.setBuku(kodeBuku.getBuku());

            kodeBukuDtos.add(temp);
        }
        return kodeBukuDtos;
    }

    @Override
    public KodeBukuDto getById(Integer id) {
        KodeBuku kodeBuku = kodeBukuRepo.findByIdKodeBuku(id);
        if (kodeBuku == null) {
            return null;
        } else {
            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodeBuku.getId());
            temp.setKodeBuku(kodeBuku.getKodeBuku());
            temp.setCreatedAt(kodeBuku.getCreatedAt());
            temp.setIsAvailable(kodeBuku.getIsAvailable());
            temp.setDonatur(kodeBuku.getDonatur());
            temp.setBuku(kodeBuku.getBuku());

            return temp;
        }
    }

    @Override
    public KodeBukuDto getByKode(String kodeBuku) {
        KodeBuku kodBuku = kodeBukuRepo.findByKodeBuku(kodeBuku);
        if (kodBuku == null) {
            return null;
        } else {
            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodBuku.getId());
            temp.setKodeBuku(kodBuku.getKodeBuku());
            temp.setCreatedAt(kodBuku.getCreatedAt());
            temp.setIsAvailable(kodBuku.getIsAvailable());
            temp.setDonatur(kodBuku.getDonatur());
            temp.setBuku(kodBuku.getBuku());

            return temp;
        }
    }

    @Override
    public KodeBukuDto update(KodeBukuDto dto) {
        KodeBuku kodeBuku = kodeBukuRepo.findByIdKodeBuku(dto.getId());
        if (kodeBuku != null) {
            // Buku buku = bukuRepo.findByIdBuku(dto.getBuku().getId());
            Users donatur = null;
            if (dto.getDonatur().getUsername() != null) {
                donatur = userRepo.findByUsername(dto.getDonatur().getUsername());
                if(donatur == null){
                    return null;
                }
            }

            // String generateCode = buku.getKategori().getKodeKategori() + "-" +
            // buku.getLokasi().getKodeLokasi() + "-" + kodeBuku.getId();

            // kodeBuku.setKodeBuku(generateCode);
            // kodeBuku.setCreatedAt(Timestamp.from(Instant.now()));
            // kodeBuku.setIsAvailable(true);
            // kodeBuku.setIsDeleted(false);
            kodeBuku.setDonatur(donatur);
            // kodeBuku.setBuku(buku);

            kodeBukuRepo.save(kodeBuku);

            // generateCode = buku.getKategori().getKodeKategori() + "-" +
            // buku.getLokasi().getKodeLokasi() + "-" + buku.getId();
            // kodeBuku.setKodeBuku(generateCode);
            // kodeBukuRepo.save(kodeBuku);

            KodeBukuDto newKode = new KodeBukuDto();
            newKode.setId(kodeBuku.getId());
            newKode.setKodeBuku(kodeBuku.getKodeBuku());
            newKode.setCreatedAt(kodeBuku.getCreatedAt());
            newKode.setIsAvailable(kodeBuku.getIsAvailable());
            newKode.setDonatur(kodeBuku.getDonatur());
            newKode.setBuku(kodeBuku.getBuku());

            return newKode;
        } else {
            return null;
        }
    }

    @Override
    public KodeBukuDto delete(Integer id) {
        KodeBuku kodeBuku = kodeBukuRepo.findByIdKodeBuku(id);
        if (kodeBuku == null) {
            return null;
        } else {
            kodeBuku.setIsDeleted(true);
            kodeBuku.setIsAvailable(false);
            kodeBukuRepo.save(kodeBuku);

            KodeBukuDto temp = new KodeBukuDto();
            temp.setId(kodeBuku.getId());
            temp.setKodeBuku(kodeBuku.getKodeBuku());
            temp.setCreatedAt(kodeBuku.getCreatedAt());
            temp.setIsAvailable(kodeBuku.getIsAvailable());
            temp.setDonatur(kodeBuku.getDonatur());
            temp.setBuku(kodeBuku.getBuku());

            return temp;
        }
    }

}
