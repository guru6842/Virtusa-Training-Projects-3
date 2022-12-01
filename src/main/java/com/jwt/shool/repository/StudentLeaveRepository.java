package com.jwt.shool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwt.shool.entity.StudentLeave;
@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Integer> {
	@Query(value = "select * from student_leave where status='requested'",nativeQuery = true)
	List<StudentLeave> newLeaverRequest();
	@Query(value = "select * from student_leave where studentid=?1",nativeQuery = true)
	List<StudentLeave> myLeaves(int id);
}
