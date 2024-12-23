package com.tka.StudentManagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tka.StudentManagement.Entity.Student;
import com.tka.StudentManagement.dao.StudentDao;

@Service
public class StudentService {
	@Autowired
	StudentDao studentDao;

	public ArrayList<Student> getallstudent() {

		return studentDao.getallstudent();
	}

	public void addstudent(Student s) {

		studentDao.addstudent(s);
	}

	public void deleteStudent(Student s) {
		studentDao.deleteStudent(s);
	}

	public void updateStudent(Student s) {
		studentDao.updateStudent(s);
	}

	public Student isExists(Student s) {
		if(studentDao.isExists(s) != null) {
			System.err.println("Student exists..!");
			return studentDao.isExists(s);
		} else {
			System.err.println("Student does not exists..!");
			studentDao.addstudent(s);
		}
		return null;
	}
}
