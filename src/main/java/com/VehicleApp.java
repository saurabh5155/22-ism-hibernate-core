package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.UserBean;
import com.bean.VehicleBean;

public class VehicleApp {

	public static void main(String[] args) {

		Configuration cfg = null;

		SessionFactory sessionFactory = null;

		cfg = new Configuration().configure("hibernate-config.xml");

		sessionFactory = cfg.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		UserBean userBean = session.get(UserBean.class, 2);

		Scanner snr = new Scanner(System.in);
		System.out.println("Enter Vehicle Number");
		VehicleBean vehicleBean = new VehicleBean();
		vehicleBean.setVehicleNumber(snr.next());

		System.out.println("Enter Vehicle Number");
		VehicleBean vehicleBean1 = new VehicleBean();
		vehicleBean1.setVehicleNumber(snr.next());

		if (userBean.getVehicle() == null) {
			List<VehicleBean> listVehicle = new ArrayList<VehicleBean>();

			listVehicle.add(vehicleBean);
			listVehicle.add(vehicleBean1);
			userBean.setVehicle(listVehicle);
		} else {
			userBean.getVehicle().add(vehicleBean1);
			userBean.getVehicle().add(vehicleBean);

		}

		session.save(userBean);
		transaction.commit();
		session.close();
		snr.close();
	}
}
