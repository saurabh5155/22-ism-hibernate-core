package com;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.CategoryBean;
import com.bean.ProductBean;

public class ProductCategoryApp {

	Configuration cfg = null;
	SessionFactory sf = null;

	public ProductCategoryApp() {
		cfg = new Configuration();
		cfg.configure("hibernate-config.xml");

		sf = cfg.buildSessionFactory();
	}

	void AddCategory() {
		Scanner snr = new Scanner(System.in);
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		while (true) {
			CategoryBean categoryBean = new CategoryBean();
			System.out.println("Enter Category Name:");
			categoryBean.setCategoryName(snr.next());
			session.save(categoryBean);
			System.out.println("Enter 0 For Exit");
			if (snr.nextInt() == 0) {
				break;
			}
		}
		tx.commit();
		session.close();
	}

	void AddProduct() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Scanner snr = new Scanner(System.in);

		while (true) {
			ProductBean product = new ProductBean();
			System.out.println("Enter Product Name:");
			product.setProductName(snr.next());

			System.out.println("Enter Product Price:");
			product.setPrice(snr.nextInt());

			while (true) {
				System.out.println("Enter category in which you want to add product \n0 For Exit");
				int categoryId = snr.nextInt();
				if (categoryId <= 0) {
					break;
				}
				CategoryBean category = session.get(CategoryBean.class, categoryId);

				product.getCategory().add(category);
			}
			
			session.save(product);
			System.out.println("1:Continue \n 0:Go To main menu");
			if(snr.nextInt()==0) {
				break;
			}
		}
		tx.commit();
		session.close();
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");

		int choice = 0;
		ProductCategoryApp pca = new ProductCategoryApp();

		while (true) {
			System.out.println("\n0 exit\n1 add Category\n2 add Products");
			System.out.println("-----Enter choice-----");

			Scanner scr = new Scanner(System.in);

			choice = scr.nextInt();

			switch (choice) {
			case 0:
				System.exit(0);
			case 1:
				pca.AddCategory();
				break;
			case 2:
				pca.AddProduct();
				break;

			}// switch
		} // while

	}

//	Configuration cfg = null;
//	SessionFactory factory = null;
//
//	public ProductCategoryApp() {
//		cfg = new Configuration().configure("hibernate-config.xml");
//		factory = cfg.buildSessionFactory();
//	}
//
//	public static void main(String[] args) { Scanner scr = new
//	  Scanner(System.in); int choice; ProductCategoryApp app = new
//	  ProductCategoryApp();
//	  
//	  while (true) { System.out.println("0 for exit");
//	  System.out.println("1 for add category");
//	  System.out.println("2 for add product"); System.out.println("entr choice");
//	  choice = scr.nextInt();
//	  
//	  if (choice == 1) { Session session = app.factory.openSession();
//	  System.out.println("Enter categoryName"); CategoryBean category = new
//	  CategoryBean(); // transient state category.setCategoryName(scr.next());
//	  Transaction tx = session.beginTransaction(); session.save(category);//
//	  persist tx.commit(); session.close(); } else if (choice == 2) {
//	  
//	  Session session = app.factory.openSession();
//	  
//	  System.out.println("Enter productName  and  price "); ProductBean productBean
//	  = new ProductBean(); productBean.setProductName(scr.next());
//	  productBean.setPrice(scr.nextInt());
//	  
//	  while(true){ System.out.
//	  println("Enter category in which you want to add product? 0 for exit"); int
//	  categoryId = scr.nextInt(); if(categoryId <= 0 ) { break; } CategoryBean
//	  category = session.get(CategoryBean.class, categoryId);
//	  productBean.getCategory().add(category);
//	  
//	  }
//	  
//	  Transaction tx = session.beginTransaction(); session.save(productBean);
//	  tx.commit(); session.close(); } } }

}
