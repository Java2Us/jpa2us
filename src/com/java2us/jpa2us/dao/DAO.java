package com.java2us.jpa2us.dao;

import java.util.List;

/**
 * This Interface performs the contract with the functions of the
 * {@link GenericDAO} Class.
 * 
 * @author Otavio Ferreira
 *
 * @param <EntityClass>
 *            Enter the name of the @Entity class that will be managed.
 *            <p>
 *            Example (EntityClass with value <b>Car</b>): <br />
 *            <span style="margin-left: 5%;"><i>@Entity public class <b>Car</b>
 *            {...}</i></span>
 *            </p>
 * 
 * @param <EntityPK>
 *            Enter the type of the annotated attribute with @Id within the
 *            Entity.
 *            <p>
 *            Example (EntityPK with value <b>Long</b>):<br />
 *            <span style="margin-left: 5%;"><i>@Id private <b>Long</b>
 *            id;</i></span>
 *            </p>
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DAO<EntityClass, EntityPK> {

	/**
	 * Method to search, in the Database, an object with a specific identifier.
	 * 
	 * @param id
	 *            Instance of the class annotated with @Id within Entity.
	 * @return The searched object in the managed state.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public EntityClass find(EntityPK id);

	/**
	 * Method to search, in the Database, an object with a specific field, with
	 * a specific value.
	 * 
	 * @param attr
	 *            Name of the attribute to be compared in the search.
	 * @param val
	 *            Value of the attribute to be compared in the search.
	 * @return A list of objects that meet the comparison requirements.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public List<EntityClass> findByAttribute(String attr, Object val);

	/**
	 * Method to search, in the Database, an object with a specific field, with
	 * a contained value.
	 * 
	 * @param attr
	 *            Name of the attribute to be compared in the search.
	 * @param val
	 *            Value of the attribute to be compared in the search.
	 * @return A list of objects that meet the comparison requirements.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public List<EntityClass> findByAttributeLike(String attr, Object val);

	/**
	 * Method to search, in the Database, all objects of the entity.
	 * 
	 * @return A list of all objects of the entity.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public List<EntityClass> findAll();

	/**
	 * Persist the object entered in the Database.
	 * 
	 * @param obj
	 *            Object to be persisted in the managed state.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void persist(EntityClass obj);

	/**
	 * Updates the changed attributes in the application inside the bank.
	 * 
	 * @param obj
	 *            Object to be merged.
	 * @return The updated object in the managed state.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public EntityClass merge(EntityClass obj);

	/**
	 * Detaches the object informed, placing it in the detached state.
	 * 
	 * @param obj
	 *            Object to be detached.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void detach(EntityClass obj);

	/**
	 * Removes the object informed, placing it in the transient state.
	 * 
	 * @param obj
	 *            Object to be removed.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void remove(EntityClass obj);

	/**
	 * Updates the changed information in the Database into the application.
	 * Placing the same in the managed state.
	 * 
	 * @param obj
	 *            Object to be refreshed.
	 * 
	 * @author Otavio Ferreira
	 * 
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public void refresh(EntityClass obj);
}
