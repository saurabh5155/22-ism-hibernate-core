package com;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.RoleBean;
import com.bean.UserBean;

public class UserApp {

	Configuration configuration = null;
	SessionFactory sessionFactory = null;

	public UserApp() {

		configuration = new Configuration().configure("hibernate-config.xml");

		sessionFactory = configuration.buildSessionFactory();
	}

	void addUser() {
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		Scanner snr = new Scanner(System.in);

		UserBean userBean = new UserBean();

		System.out.println("Enter FirstName");
		userBean.setFirstName(snr.next());

		System.out.println("Enter LastName");
		userBean.setLastName(snr.next());

		System.out.println("Enter Email");
		userBean.setEmail(snr.next());

		System.out.println("Enter Password");
		userBean.setPassword(snr.next());

		System.out.println("Enter Role Id");
		int roleId = snr.nextInt();

		RoleBean roleBean = session.get(RoleBean.class, roleId);
		userBean.setRole(roleBean);

		try {
			session.save(userBean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	void getAllUsers() {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from UserBean");

		List<UserBean> listUsers = query.getResultList();

		System.out.println("FirstName \t LastName \t Email \t Password \t RoleName");
		for (UserBean userBean : listUsers) {
			System.out.println(userBean.getFirstName() + " \t " + userBean.getLastName() + " \t " + userBean.getEmail()
					+ " \t " + userBean.getPassword() + " \t " + userBean.getRole().getRoleName());

		}
	}

	public static void main(String[] args) {

		System.out.println("Hello World!");

		int choice = 0;

		UserApp userApp = new UserApp();
		while (true) {
			System.out.println("\n0 exit\n1 add\n2 list all roles\n3 view\n4 delete role");
			System.out.println("-----Enter choice-----");

			Scanner scr = new Scanner(System.in);

			choice = scr.nextInt();

			switch (choice) {
			case 0:
				System.exit(0);
			case 1:
				userApp.addUser();
				break;
			case 2:
				userApp.getAllUsers();
				break;
			}
		}

	}
}
