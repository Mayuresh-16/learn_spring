package com.tka.StudentManagement.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.tka.StudentManagement.Entity.Student;
@Repository
public class Config {
	Session session=null;
	public Session getSession()	{
		Configuration cfg = new Configuration();
		cfg.configure();//locate hibernate.cfg.xml file
		cfg.addAnnotatedClass(Student.class);
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
		return session;
	}
	
	}
	




