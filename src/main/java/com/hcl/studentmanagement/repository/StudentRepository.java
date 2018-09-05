package com.hcl.studentmanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.studentmanagement.model.Student;

@Repository
public class StudentRepository {

	SessionFactory sessionFactory;
	Transaction t;
	//cretaes physical connection with database.
	Session session;

	public StudentRepository() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
	}

	
	// To get all the data.
	@SuppressWarnings("unchecked")
	public List<?> select(Map<String, Object> params) {

		List<Object> rs = null;

		try {
			session = sessionFactory.getCurrentSession();

			String fname = "Kairav";
			int id = 0;
			if (params != null)
				id = Integer.parseInt(params.getOrDefault("id", "0").toString());
			System.err.println("id :" + id);

			session.beginTransaction();
			if (id == 0) {
				rs = session.createQuery(" from Student ").list();
			} else {
				Student s = session.get(Student.class, fname);
				rs = new ArrayList<>();
				rs.add(s);
			}

			session.getTransaction().commit();
			System.out.println(rs.size());
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			System.out.println("Transaction is rolled back.");
		}
		return rs;
	}

	
	// To create or update the transaction
	public void create(Map<String, Object> params) {

		try {
			session = sessionFactory.getCurrentSession();

			int id = Integer.parseInt(params.get("id").toString());
			Student student = new Student();

			JSONObject obj = new JSONObject(params);
			System.out.println(obj);

			student.setFname(params.get("fname").toString());
			student.setLname(params.get("lname").toString());
			student.setEmail(params.get("email").toString());
			student.setPassword(params.get("password").toString());

			System.out.println(student);

			// Save the Student object to the database
			if (id == 0) {
				
				session.beginTransaction();
				session.save(student);
				session.getTransaction().commit();

			} 
			//Update the transaction.
			else {
				student.setId(id);
				session.beginTransaction();
				session.update(student);
				session.getTransaction().commit();
				
			}
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			System.out.println("Transaction rollback" + e);
		}
	}

	
	//To check the rolback condition
	public String bulkInsert(List<?> list,boolean committable) {

		String response="Failed to commit in Bulk.";
		try {
			session = sessionFactory.getCurrentSession();

			session.beginTransaction();
			
			int total=list.size();
			
			if(committable)
				total=3;
			
			int i = 0;
			for (; i < total; i++) {
				System.out.println("i=" + i);
				if (list.get(i) instanceof Student) {
					Student student = (Student) list.get(i);
					student.setId(0);
					session.save(student);	
					}
					
				}
			
			
			if (i<=3) {
				System.out.println("Committing Transactions.");
				session.getTransaction().commit();
				response="Bulk commit successfull.";
			} else {
				System.out.println("Rolling back Transactions.");
				session.getTransaction().rollback();
				response="Please send committable=true to see success.";
			}

		} catch (HibernateException e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {

		}
		return response;
	}
}
