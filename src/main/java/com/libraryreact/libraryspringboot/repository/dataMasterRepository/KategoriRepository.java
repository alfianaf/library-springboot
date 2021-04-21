package com.libraryreact.libraryspringboot.repository.dataMasterRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataMaster.Kategori;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KategoriRepository extends JpaRepository<Kategori, Integer>{
    // cari berdasarkan id yang belum dihapus
    @Query(value = "select * from kategori where is_deleted = 0 and id = ?", nativeQuery = true)
    public Kategori findByIdKategori(Integer id);

    // tampil semua data berdasarkan status is deleted - nya
    public List<Kategori> findByIsDeleted(boolean isDeleted);

    // cari berdasarkan Kode Kategori dan yang belum dihapus
    @Query(value = "select * from kategori where is_deleted = 0 and kode_kategori = ?", nativeQuery = true)
    public Kategori findByKodeKategori(String kodeKategori);

    // cari berdasarkan Nama Kategori dan yang belum dihapus
    @Query(value = "select * from kategori where is_deleted = 0 and nama_kategori = ?", nativeQuery = true)
    public Kategori findByNamaKategori(String namaKategori);
}
