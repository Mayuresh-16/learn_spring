package com.tka.StudentManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.StudentManagement.Entity.Student;
import com.tka.StudentManagement.configuration.Config;

@Repository
public class StudentDao {

	private static final String url = "jdbc:mysql://localhost:3306/southwind";
	private static final String username = "root";
	private static final String password = "root";
	public static Connection con;
	public static PreparedStatement pstmt;
	Session session = null;
	@Autowired
	Config config;

	public static Connection getDbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public ArrayList<Student> getallstudent() {
		System.out.println("IN Dao of student");
		ArrayList<Student> al = new ArrayList<Student>();

		String sql = "select * from student";
		con = getDbConnection();

		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getNString(2);
				int age = rs.getInt(3);
				String course = rs.getString(4);
				al.add(new Student(id, name, age, course));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}

	public void addstudent(Student s) {
		/*
		 * String sql = "insert into student values(?,?,?,?)";
		 * 
		 * try { pstmt = getDbConnection().prepareStatement(sql); pstmt.setInt(1,
		 * s.getId()); pstmt.setString(2, s.getName()); pstmt.setInt(3, s.getAge());
		 * pstmt.setString(4, s.getCourse()); pstmt.executeUpdate();
		 * System.err.println("Data inserted successfully"); } catch (SQLException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		System.err.println("In add Method.");
		session = config.getSession();
		Student student = new Student(s.getId(), s.getName(), s.getAge(), s.getCourse());
		session.save(student);
		session.beginTransaction().commit();
		System.out.println("Data inserted.");
	}

	public void deleteStudent(Student s) {
		String sql = "delete from student where id=?";

		try {
			pstmt = getDbConnection().prepareStatement(sql);
			pstmt.setInt(1, s.getId());
			pstmt.executeUpdate();
			System.err.println("Data deleted succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Student s) {
		String sql = "update student set name=?, age=?, course=? where id=?";

		try {
			pstmt = getDbConnection().prepareStatement(sql);
			pstmt.setString(1, s.getName());
			pstmt.setInt(2, s.getAge());
			pstmt.setString(3, s.getCourse());
			pstmt.setInt(4, s.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				System.err.println("Data updated successfully.");
			} else {
				System.err.println("Data not updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Student isExists(Student s) {
		String sql = "select * from student where id=?";
		Student ss = null;
		try {
			pstmt = getDbConnection().prepareStatement(sql);
			pstmt.setInt(1, s.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getNString(2);
				int age = rs.getInt(3);
				String course = rs.getString(4);
				ss = new Student(id, name, age, course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}
}
