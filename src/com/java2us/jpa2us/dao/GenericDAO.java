package com.java2us.jpa2us.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;

import com.java2us.jpa2us.jpa.JPAFactory;
import com.java2us.jpa2us.jpa.JPASessionManager;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * This class implements a {@link DAO} interface, and contains all the basic methods for
 * interacting with the database. All repository classes (Example: CarDAO) must
 * be extensions of this class.<br /><br />
 * 
 * Example (<i>Child Implementation</i>): <br />
 * <span style="margin-left: 5%;">	public class CarDAO extends {@link GenericDAO}<Car, Long> {	</span><br />
 * <span style="margin-left: 10%;">     private {@link JPAFactory} factory;	</span><br /><br />
 * <span style="margin-left: 10%;">     public CarDAO({@link JPAFactory} factory) {	</span><br />
 * <span style="margin-left: 15%;">         super(factory, Car.class);	</span><br />
 * <span style="margin-left: 15%;">         this.factory = factory;	</span><br />
 * <span style="margin-left: 10%;">     }	</span><br />
 * <span style="margin-left: 5%;">	}	</span>
 * 
 * @author Otavio Ferreira
 *
 * @param <EntityClass>
 *            Enter the name of the @Entity class that will be managed.
 *            <p>
 *            Example (EntityClass with value <b>Car</b>): <br />
 *            <span style="margin-left: 5%;"><i>@Entity public class <b>Car</b> {...}</i></span>
 *            </p>
 * 
 * @param <EntityPK>
 *            Enter the type of the annotated attribute with @Id within the
 *            Entity.
 *            <p>
 *            Example (EntityPK with value <b>Long</b>):<br />
 *            <span style="margin-left: 5%;"><i>@Id private <b>Long</b> id;</i></span>
 *            </p>
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class GenericDAO<EntityClass, EntityPK> implements DAO<EntityClass, EntityPK> {

	private Class<EntityClass> entityClass;
	private JPAFactory factory;

	/**
	 * {@inheritDoc}
	 */
	public GenericDAO(JPAFactory factory, Class<EntityClass> entityClass) {
		this.entityClass = entityClass;
		this.factory = factory;
	}

	/**
	 * {@inheritDoc}
	 */
	private String getDefaultMessage(String action, String obj) {
		return "[ERROR] EntityClass: " + entityClass.getSimpleName() + " | Action: " + action + " | Object: " + obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(EntityClass obj) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			sessionManager.beginTransaction();
			sessionManager.getEntityManager().persist(obj);
			sessionManager.getEntityManager().flush();
			sessionManager.commitTransaction();
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Persist", Objects.toString(obj)), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Object obj) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			sessionManager.beginTransaction();
			obj = sessionManager.getEntityManager().merge(obj);
			sessionManager.getEntityManager().remove(obj);
			sessionManager.getEntityManager().flush();
			sessionManager.commitTransaction();
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Remove", Objects.toString(obj)), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityClass find(EntityPK id) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			EntityClass obj = sessionManager.getEntityManager().find(entityClass, id);
			return obj;
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Find", Objects.toString(id)), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityClass> findByAttribute(String attr, Object val) {
		EasyCriteria<EntityClass> where = EasyCriteriaFactory.createEasyCTO();

		where.andEquals(attr, val);

		EasyCriteria<EntityClass> query = EasyCriteriaFactory
				.createQueryCriteria(factory.getSessionManager().getEntityManager(), entityClass, where);

		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityClass> findByAttributeLike(String attr, Object val) {
		EasyCriteria<EntityClass> where = EasyCriteriaFactory.createEasyCTO();

		where.andStringLike(attr, "%" + val + "%");

		EasyCriteria<EntityClass> query = EasyCriteriaFactory
				.createQueryCriteria(factory.getSessionManager().getEntityManager(), entityClass, where);

		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityClass> findAll() {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			EasyCriteria<EntityClass> query = EasyCriteriaFactory.createQueryCriteria(sessionManager.getEntityManager(),
					entityClass);
			return query.getResultList();
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("FindAll", entityClass.getSimpleName()), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityClass merge(EntityClass obj) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			sessionManager.beginTransaction();
			obj = sessionManager.getEntityManager().merge(obj);
			sessionManager.commitTransaction();
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Merge", Objects.toString(obj)), ex);
		}

		return obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(EntityClass obj) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			sessionManager.getEntityManager().detach(obj);
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Detach", Objects.toString(obj)), ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refresh(EntityClass obj) {
		try (JPASessionManager sessionManager = factory.getSessionManager()) {
			sessionManager.getEntityManager().refresh(obj);
		} catch (Exception ex) {
			throw new PersistenceException(getDefaultMessage("Refresh", Objects.toString(obj)), ex);
		}
	}
}
