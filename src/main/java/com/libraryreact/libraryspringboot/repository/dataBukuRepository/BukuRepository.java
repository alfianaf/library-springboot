package com.libraryreact.libraryspringboot.repository.dataBukuRepository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.entity.dataBuku.Buku;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Integer> {
    // get all buku berdasarkan status is deleted
    public List<Buku> findByIsDeleted (Boolean isDeleted);

    // get by id
    @Query(value = "select * from buku where is_deleted = 0 and id = ?", nativeQuery = true)
    public Buku findByIdBuku(Integer id);
      
    // get terbaru
    @Query(value = "select * from buku where is_deleted = 0 order by id desc limit 6", nativeQuery = true)
    public List<Buku> findTerbaru ();
}
