package com;

import java.util.Scanner;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.RoleBean;

/**
 * Hello world!
 *
 */

	
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// use to create session factory from give configuration
		Configuration cfg = new Configuration().configure("hibernate-config.xml");

		// use to create session
		SessionFactory factory = cfg.buildSessionFactory();

		// use to execute query
		Session session = factory.openSession();
		
		Scanner scr = new Scanner(System.in);
		System.out.println("Enter role name");
		String roleName = scr.next();
		
		 Transaction tx = session.beginTransaction();
		 
		RoleBean roleBean = new RoleBean();
		roleBean.setRoleName(roleName);
		session.save(roleBean);
		tx.commit();
		session.close();
	}
}
