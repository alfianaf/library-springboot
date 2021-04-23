package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.Peminjaman;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {

}
