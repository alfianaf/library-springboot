package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.FilesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Integer> {

}
