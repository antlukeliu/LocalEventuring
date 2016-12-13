package com.malabanan.localeventuring;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DAOVenue {

	private static SessionFactory factory;

	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			;
		}
		Configuration configuration = new Configuration();
		// modify these to match your XML files
		configuration.configure("hibernate.cfg.xml");
		configuration.addResource("venue.hbu.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static int addVenue(Venue v) {
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		// save this specific record
		int i = (Integer) hibernateSession.save(v);
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
		return i;
	}
	
	public static List<Venue> getVenues(String str) {
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		List<Venue> venues = hibernateSession.createQuery(str).list();
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
		return venues;
	}
	
	public static void updateVenue(Venue v) { //name FranktheTank
		if (factory == null)
			setupFactory();	
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		// save this specific record | new FrankD
		hibernateSession.merge(v); //updates database 
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
	}
	
	public static void deleteVenue(Integer v) { //takes venue id
		if (factory == null)
			setupFactory();
	     Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Venue str = (Venue)session.get(Venue.class, v); //venue
	         session.delete(str); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
}
