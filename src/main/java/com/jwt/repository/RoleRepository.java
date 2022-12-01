package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
