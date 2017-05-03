package com.java2us.jpa2us.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class is responsible for providing the entire infrastructure for
 * connecting to the database. The system needs a {@link JPASessionManager} to
 * get the EntityManager to connect to the database. This instance is provided
 * by this class.
 * 
 * @author Otavio Ferreira
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class JPAFactory {

	private final EntityManagerFactory factory;
	private JPASessionManager sessionManager;

	/**
	 * Constructor of class {@link JPAFactory}. In the call of this builder, the
	 * factory is also built.
	 * 
	 * @param persistenceUnitName
	 *            PersistenceUnit name that contains the access settings to the
	 *            bank within persistence.xml.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public JPAFactory(String persistenceUnitName) {
		this.factory = createEntityFactory(persistenceUnitName);
	}

	/**
	 * It effectively creates the EntityManagers factory.
	 * 
	 * @param persistenceUnitName
	 *            PersistenceUnit name that contains the access settings to the
	 *            bank within persistence.xml.
	 * @return The EntityManagerFactory built.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	private EntityManagerFactory createEntityFactory(String persistenceUnitName) {
		try {
			return Persistence.createEntityManagerFactory(persistenceUnitName);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	/**
	 * Creates the {@link JPASessionManager}, which is responsible for managing
	 * and requesting the creation of sessions with the Database.
	 * 
	 * @return A new {@link JPASessionManager}.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public JPASessionManager getSessionManager() {
		this.sessionManager = new JPASessionManager(factory);
		return sessionManager;
	}

	/**
	 * Close the EntityManagerFactory.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void closeEntityFactory() {
		factory.close();
	}
}
