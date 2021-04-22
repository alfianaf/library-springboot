package com.libraryreact.libraryspringboot.repository;

import java.util.List;

import com.libraryreact.libraryspringboot.models.dto.UsersDto;
import com.libraryreact.libraryspringboot.models.entity.UserDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    @Query(value = "select * from user_detail where user_id = ?", nativeQuery = true)
    public UserDetail findDetailByUserId(Integer userId);
}
