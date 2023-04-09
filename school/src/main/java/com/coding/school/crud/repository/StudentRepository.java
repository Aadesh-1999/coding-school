package com.coding.school.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.school.crud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}
