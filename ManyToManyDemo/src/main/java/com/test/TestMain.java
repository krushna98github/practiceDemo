package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMain {

	public static void main(String[] args) {
		//1. load configuration class
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml"); //load the file
	
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		Transaction t= session.beginTransaction();

		User user1= new User();
		user1.setName("rajeshwari");
		user1.setMail("ranu123@gmail.com");
		
		User user2= new User();
		user2.setName("krushna");
		user2.setMail("krushna123@gmail.com");
		
		Nominee nominee1= new Nominee();
		nominee1.setName("pooja");
		
		Nominee nominee2= new Nominee();
		nominee2.setName("akash");
		
		Nominee nominee3= new Nominee();
		nominee3.setName("prajyot");
		
		user1.getNomineeList().add(nominee1);
		user1.getNomineeList().add(nominee2);
		user1.getNomineeList().add(nominee3);
		
		nominee1.getUserlist().add(user1);
		nominee2.getUserlist().add(user1);
		nominee3.getUserlist().add(user1);
		
		user2.getNomineeList().add(nominee2);
		user2.getNomineeList().add(nominee3);
		
		nominee2.getUserlist().add(user2);
		nominee3.getUserlist().add(user2);
		
		session.persist(user1);
		session.persist(user2);
		
		t.commit();
		session.close();
		sessionFactory.close();
		
		System.out.println("-----------------------Record Inserted Successully-----------------------");

	}

}
