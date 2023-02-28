 package com.test;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	public Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		return configuration;
	}

	public void getInsertData() {
		Test test = new Test();
		Configuration configuration = test.getConfiguration();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no");
		int no = sc.nextInt();
		for (int i = 1; i <= no; i++) {

			Customer customer = new Customer();
			System.out.println("Enter name");
			String name = sc.next();
			System.out.println("Enter number");
			String num = sc.next();
			System.out.println("Enter total");
			int total = sc.nextInt();
			customer.setFirstName(name);
			customer.setMobileNumber(num);

			Transections transections = new Transections();
			transections.setDate(new Date());
			transections.setTotal(total);

			customer.setTransection(transections);

			session.save(customer);
		}
		transaction.commit();
		session.close();
		sessionFactory.close();
       sc.close();
		System.out.println("--------------------Data Inserted Succesfully--------------------");

	}

	public void getUpdateData() {
		Test test = new Test();
		Configuration configuration = test.getConfiguration();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.get(Customer.class, 6);
		customer.setFirstName("Kanhaiyya");
		session.update(customer);
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("********Data Updated Successfully********");
	}
	
	public void getDeleteData() {
		Test test = new Test();
		Configuration configuration = test.getConfiguration();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class, 6);
		session.delete(customer);
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("********Data Delete Successfully********");

	}
	
	public void getFetchData() {
		Test test = new Test();
		Configuration configuration = test.getConfiguration();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		int id = 0;
		String name = null;
		String number = null;
		Date date = null;
		int total = 0;
		for (Customer customer : customers) {
			id = customer.getId();
			name = customer.getFirstName();
			number = customer.getMobileNumber();
			Query query1 = session.createQuery("from Transections");
			List<Transections> trans =  query1.list();
			for (Transections transections : trans) {
				date = transections.getDate();
				total = transections.getTotal();
			}
			System.out.println("ID : "+id);
			System.out.println("NAME : "+name);
			System.out.println("NUMBER : "+number);
			System.out.println("DATE : "+date);
			System.out.println("TOTAL : "+total);
				}
	}

}
