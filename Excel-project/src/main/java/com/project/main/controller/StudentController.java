package com.project.main.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.main.dto.StudentDto;
import com.project.main.service.StudentService;
import com.project.main.util.ExcelGenerator;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {


	@Autowired
	StudentService studentService;
	
	@GetMapping("/students")
	public String getAll(Model model) {
		List<StudentDto> lists = studentService.showAll();
		model.addAttribute("list",lists);
		return "student"; //html to create
	}
	
	@GetMapping("/regis")
	public String doLogin(Model model) {


		return "student/register";
	}

	@GetMapping("/download")
	public String download() {
		return "student/index";
	}
		
		@PostMapping("/stars")
		public String createStudent(@ModelAttribute StudentDto dto,Model model) {
			
			studentService.saveStudent(dto);
			model.addAttribute("msg", "save sucessfully....");
			return "student/register"; //html create
			
		}
	
	
	
	 @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
		 response.setContentType("application/octet-stream");
		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		 String currentDateTime = dateFormatter.format(new Date());

		 String headerKey = "Content-Disposition";
		 String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
		 response.setHeader(headerKey, headerValue);

        List <StudentDto> listOfStudents = studentService.getTheListStudent();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }

}
