package com.project.main.service;

import java.util.List;

import com.project.main.dto.StudentDto;

public interface StudentService {

	List<StudentDto> showAll();

	void saveStudent(StudentDto dto);

	List<StudentDto> getTheListStudent();

	
}
