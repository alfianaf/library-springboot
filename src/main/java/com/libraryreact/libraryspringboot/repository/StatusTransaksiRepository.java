package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.EStatusTransaksi;
import com.libraryreact.libraryspringboot.models.entity.StatusTransaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTransaksiRepository extends JpaRepository<StatusTransaksi, Integer> {
    StatusTransaksi findByName(EStatusTransaksi statusTransaksi);
}
