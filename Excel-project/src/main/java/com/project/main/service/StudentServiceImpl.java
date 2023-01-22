package com.project.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.dto.StudentDto;
import com.project.main.entity.StudentEntity;
import com.project.main.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<StudentDto> showAll() {
List<StudentEntity> lists = studentRepository.findAll();
List<StudentDto> dtos = new ArrayList<>();
  for(StudentEntity element :lists ) {
	  StudentDto dto = new StudentDto();
	  BeanUtils.copyProperties(element, dto);
	  dtos.add(dto);
  }
		return dtos;
	}

	@Override
	public void saveStudent(StudentDto dto) {

		StudentEntity entity = new StudentEntity();
		BeanUtils.copyProperties(dto, entity);
		studentRepository.save(entity);
	}

	@Override
	public List<StudentDto> getTheListStudent() {

		List<StudentEntity> lists = studentRepository.findAll();
		List<StudentDto> dtos = lists.stream().map(s-> convertToStudent(s)).collect(Collectors.toList());

		return dtos;
	}

	private StudentDto convertToStudent(StudentEntity s) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentName(s.getStudentName());
		studentDto.setId(s.getId());
		studentDto.setEmail(s.getEmail());
		studentDto.setMobileNo(s.getMobileNo());
		return studentDto;


	}


}
