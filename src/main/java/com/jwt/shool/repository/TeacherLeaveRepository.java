package com.jwt.shool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.shool.entity.TeacherLeave;
@Repository
public interface TeacherLeaveRepository extends JpaRepository<TeacherLeave, Integer> {
	@Query(value = "select * from teacher_leave where status='requested'",nativeQuery = true)
	List<TeacherLeave> newLeaverRequest();
	@Query(value = "select * from teacher_leave where phoneno=?1",nativeQuery = true)
	List<TeacherLeave> myLeaves(String phoneno);
}
