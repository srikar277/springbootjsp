package com.hcl.satish.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.satish.demo.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
}
