package com.java2us.jpa2us.jpa;

import java.io.Closeable;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * Class responsible for managing an EntityManager open by the factory. Each
 * instance of {@link JPASessionManager} only has an EntityManager during its
 * life cycle.
 * 
 * @author Otavio Ferreira
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class JPASessionManager implements Closeable {

	private EntityManager entityManager;

	/**
	 * Constructor for class {@link JPASessionManager}. While building this
	 * class, your EntityManager is also prompted for the respective JPAFactory.
	 * 
	 * @param factory
	 *            EntityManagerFactory responsible for managing the
	 *            EntityManager of this class.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public JPASessionManager(EntityManagerFactory factory) {
		try {
			this.entityManager = factory.createEntityManager();
		} catch (Exception ex) {
			throw new PersistenceException("Erro ao instanciar uma entity manager.", ex);
		}
	}

	/**
	 * Method responsible for informing the currently used EntityManager instance.
	 * 
	 * @return The requested EntityManager.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * Starts a transaction within the current EntityManager.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void beginTransaction() {
		try {
			this.entityManager.getTransaction().begin();
		} catch (Exception ex) {
			throw new PersistenceException("Erro ao iniciar uma transação no entity manager.", ex);
		}
	}

	/**
	 * Commits a transaction within the current EntityManager.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void commitTransaction() {
		try {
			this.entityManager.getTransaction().commit();
		} catch (Exception ex) {
			throw new PersistenceException("Erro ao commitar uma transação no entity manager.", ex);
		}
	}

	/**
	 * Closes the EntityManager, and consequently, the {@link JPASessionManager}.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	@Override
	public void close() throws IOException {
		try {
			this.entityManager.close();
		} catch (Exception ex) {
			throw new PersistenceException("Erro ao fechar a entity manager.", ex);
		}
	}
}
