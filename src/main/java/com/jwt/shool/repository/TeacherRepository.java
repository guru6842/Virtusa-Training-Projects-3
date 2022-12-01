package com.jwt.shool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.shool.entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

}
