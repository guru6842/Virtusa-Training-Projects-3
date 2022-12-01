package com.jwt.shool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.shool.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
