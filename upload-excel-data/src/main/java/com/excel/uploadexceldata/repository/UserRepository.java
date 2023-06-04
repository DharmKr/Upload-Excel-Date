package com.excel.uploadexceldata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.uploadexceldata.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
