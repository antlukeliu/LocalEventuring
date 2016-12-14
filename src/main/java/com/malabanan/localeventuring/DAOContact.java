package com.malabanan.localeventuring;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//Date Accessing Object
public class DAOContact {

	private static SessionFactory factory;

	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			;
		}
		Configuration configuration = new Configuration();
		// modify these to match your XML files
		configuration.configure("hibernate.cfg.xml"); //connecting to database
		configuration.addResource("contact.hbm.xml"); //getting data
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static int addContact(Contact c) {
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession(); //opens connection
		hibernateSession.getTransaction().begin(); //starts send the data
		// save this specific record
		int i = (Integer) hibernateSession.save(c); // save contact c to database
		hibernateSession.getTransaction().commit(); // apply to database 
		hibernateSession.close(); // closes connection to prevent too many connections
		return i;
	}
	
	public static List<Contact> getContacts(String str) { 
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		List<Contact> contact = hibernateSession.createQuery(str).list();// "(Select *) From Contact where email = 'localeventuring@gmail.com' "
		hibernateSession.getTransaction().commit(); //apply query string
		hibernateSession.close();// closes
		return contact;
	}
}
