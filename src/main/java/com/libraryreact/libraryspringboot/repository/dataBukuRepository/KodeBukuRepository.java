package com.libraryreact.libraryspringboot.repository.dataBukuRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;
import com.libraryreact.libraryspringboot.models.entity.dataBuku.KodeBuku;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KodeBukuRepository extends JpaRepository<KodeBuku, Integer>{
    // get all buku berdasarkan status is deleted
    public List<KodeBuku> findByIsDeleted (Boolean isDeleted);

    // get by id
    @Query(value = "select * from kode_buku where is_deleted = 0 and id = ?", nativeQuery = true)
    public KodeBuku findByIdKodeBuku(Integer id);

    // get by kode buku
    @Query(value = "select * from kode_buku where is_deleted = 0 and kode_buku = ?", nativeQuery = true)
    public KodeBuku findByKodeBuku(String kodeBuku);

    // get all buku berdasarkan status bukunya
    // @Query(value = "select * from kode_buku where is_deleted = 0 and buku = ?", nativeQuery = true)
    public List<KodeBuku> findByBuku(Buku buku);

    // get by kode buku yang tersedia dan belum di hapus
    @Query(value = "select * from kode_buku where is_deleted = 0 and is_available = 1 and kode_buku = ?", nativeQuery = true)
    public KodeBuku findByKodeBukuAvailable(String kodeBuku);

}
