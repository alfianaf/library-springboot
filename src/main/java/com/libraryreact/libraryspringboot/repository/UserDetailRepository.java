package com.libraryreact.libraryspringboot.repository;

import com.libraryreact.libraryspringboot.models.entity.UserDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

}
