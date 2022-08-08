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
	
	Configuration cfg = null;
	SessionFactory factory = null;
	
	public App() {
		
		// use to create session factory from give configuration
		cfg = new Configuration().configure("hibernate-config.xml");
		
		// use to create session
		factory = cfg.buildSessionFactory();
	}
	
	void addRole() {
		
		// use to execute query
		Session session = factory.openSession();
		
		Scanner scr = new Scanner(System.in);
		System.out.println("Enter role name");
		String roleName = scr.next();
		
		RoleBean roleBean = new RoleBean();
		roleBean.setRoleName(roleName);
		
		Transaction tx = session.beginTransaction();
		
		
		try {
			session.save(roleBean);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();// undo
		}
		session.close();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		int choice = 0;
		App app = new App();

		while (true) {
			System.out.println("\n0 for exit\n1 for add\n2 for list all roles\n3 for view\n4 for delete role");
			System.out.println("Enter choice....");

			Scanner scr = new Scanner(System.in);

				choice = scr.nextInt();

			switch (choice) {
			case 0:
				System.exit(0);
			case 1:
				app.addRole();
				break;
			}// switch
		} // while
	
	}
}
