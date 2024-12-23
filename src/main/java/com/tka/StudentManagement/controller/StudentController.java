package com.tka.StudentManagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tka.StudentManagement.Entity.Student;
import com.tka.StudentManagement.services.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	@GetMapping("getallstudents")
	public ArrayList<Student> getallstudent() {

		return studentService.getallstudent();
	}

	@PostMapping("addstudent")
	public void addstudent(@RequestBody Student s) {
		studentService.addstudent(s);

	}
	
	@DeleteMapping("deletestudent")
	public void deleteStudent(@RequestBody Student s) {
		studentService.deleteStudent(s);
	}
	
	@PutMapping("updatestudent")
	public void updateStudent(@RequestBody Student s) {
		studentService.updateStudent(s);
	}
	
	@GetMapping("getstudent")
	public Student isExists(@RequestBody Student s) {
		Student ss = studentService.isExists(s);
		return ss;
	}
}
