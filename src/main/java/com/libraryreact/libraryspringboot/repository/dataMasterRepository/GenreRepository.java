package com.libraryreact.libraryspringboot.repository.dataMasterRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataMaster.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
    // cari berdasrkan id yang belum dihapus
    @Query(value = "select * from genre where is_deleted = 1 and id = ?", nativeQuery = true)
    public Genre findByIdGenre(Integer id);

    // tampil semua data berdasarkan status is deleted - nya
    public List<Genre> findByIsDeleted(boolean isDeleted);

    // cari berdasarkan nama genre dan yang belum dihapus
    @Query(value = "select * from genre where is_deleted = 1 and nama_genre = ?", nativeQuery = true)
    public Genre findByNamaGenre(String namaGenre);
}
