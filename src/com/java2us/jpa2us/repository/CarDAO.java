package com.java2us.jpa2us.repository;

import java.util.List;
import java.util.Objects;

import com.java2us.jpa2us.dao.GenericDAO;
import com.java2us.jpa2us.jpa.JPAFactory;
import com.java2us.jpa2us.model.Car;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * Repository for the Car class. This class is responsible for linking the
 * application to the GenericDAO class, inheriting all its data manipulation
 * functions.
 * 
 * @author otavio.c.ferreira
 *
 */
public class CarDAO extends GenericDAO<Car, Long> {

	private JPAFactory factory;

	/**
	 * Constructor for class CarDAO. It should receive as parameter an
	 * EntityManagers factory so that the GenericDAO class can function
	 * correctly. A reference to this factory must be stored within this class.
	 * 
	 * @param factory
	 *            Reference to the EntityManagers factory.
	 */
	public CarDAO(JPAFactory factory) {
		super(factory, Car.class);
		this.factory = factory;
	}

	/**
	 * An example method for queries created within a specific DAO. This can be
	 * done using the reference to the EntityManagers factory.
	 * 
	 * @param model Model of the car to be searched for.
	 * @param brand Brand of the car to be searched for.
	 * @return A list of cars as a result of the search conducted.
	 */
	public List<Car> findByModelAndBrand(String model, String brand) {
		EasyCriteria<Car> where = EasyCriteriaFactory.createEasyCTO();

		where.andEquals("model", Objects.toString(model));
		where.andEquals("brand", Objects.toString(brand));

		EasyCriteria<Car> query = EasyCriteriaFactory
				.createQueryCriteria(factory.getSessionManager().getEntityManager(), Car.class, where);

		return query.getResultList();
	}
}
