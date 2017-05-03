package com.java2us.jpa2us.test;

import java.util.List;

import com.java2us.jpa2us.jpa.JPAFactory;
import com.java2us.jpa2us.model.Car;
import com.java2us.jpa2us.repository.CarDAO;

public class CarTest {

	public static JPAFactory factory;

	public static void main(String[] args) {

		try {
			factory = new JPAFactory("default");

			CarDAO carDAO = new CarDAO(factory);
			
			Car carExample = new Car();
			
			carExample.setBrand("Volvo");
			carExample.setModel("xpto");
			carExample.setManufactureYear(2017);
			carExample.setModelYear(2017);
			carExample.setComments("Car to realize tests in jpa2us.");
			
			// Persist the car
			carDAO.persist(carExample);
			
			// Find the car
			Car carExampleFind = carDAO.find(carExample.getId());
			System.out.println("Model: " + carExampleFind.getModel());
			
			// Update the car
			carExampleFind.setModel("jpa2us");
			carDAO.merge(carExampleFind);
			
			// Find by attr
			List<Car> cars = carDAO.findByAttribute("brand", "Volvo");
			Car carByAttr = cars.get(0);
			System.out.println("Comments: " + carByAttr.getComments());
			
			// Find by attr like
			cars = carDAO.findByAttributeLike("comments", "realize");
			Car carByAttrLike = cars.get(0);
			System.out.println("Brand: " + carByAttrLike.getBrand());
			
			//Remove the car
			carDAO.remove(carByAttrLike);
		} catch (Exception ex) {
			System.out.println("*** Exceptions info: " + ex.getMessage());
			System.out.println("*** Cause: " + ex.getCause());
		} finally {
			factory.closeEntityFactory();
		}
	}
}
