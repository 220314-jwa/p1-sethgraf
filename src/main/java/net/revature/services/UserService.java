package net.revature.services;

import java.util.List;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UnavailableException;
import net.revature.exceptions.UsernameAlreadyExistsException;
import net.revature.models.Employee;
import net.revature.models.Request;


public interface UserService {	
public Employee logIn(String username, String password) throws IncorrectCredentialsException;


	/**
	 * creates a new user. if the username is available, 
	 * returns the new user with their database-generated ID. 
	 * otherwise, throws a UsernameAlreadyExistsException.
	 * 
	 * @param newUser
	 * @return User with newly generated ID
	 */
	public Employee register(Employee newEmployee) throws UsernameAlreadyExistsException;
	
	/**
	 * 
	 * @return all available pets
	 */
	public List<Request> viewAvailableRequests();
	
	/**
	 * 
	 * @param species
	 * @return all available pets with the specified species
	 */
	public List<Request> searchRequestsByDesciption(String description);
	
	/**
	 * sets the pet to be adopted by the user. if the pet
	 * is not available, throws AlreadyAdoptedException.
	 * 
	 * @param user
	 * @param petToAdopt
	 * @return user with their newly adopted pet
	 */
	public Employee Request(Employee employee, Request requestStatusId) throws UnavailableException;
	
	/**
	 * 
	 * @param id
	 * @return the pet with the specified ID
	 */
	public Request getRequestById(int id);
	
	//add statuses from ERD Pending Manager Approval, Manager-Approved, Pending Dept Head Approval, Approved, Rejected
}

