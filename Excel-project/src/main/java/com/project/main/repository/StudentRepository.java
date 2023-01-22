package com.project.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.main.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

}
