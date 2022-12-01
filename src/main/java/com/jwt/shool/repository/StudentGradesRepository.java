package com.jwt.shool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.shool.entity.StudentGrades;
@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer> {

}
