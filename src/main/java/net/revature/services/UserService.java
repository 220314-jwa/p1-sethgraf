package net.revature.services;

import net.revature.exceptions.UsernameAlreadyExistsException;

import java.util.List;

import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.RequestAlreadySubmittedException;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.models.Status;



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
	public Request submitRequest(Request requestToSubmit) throws RequestAlreadySubmittedException;
	/**
	 * 
	 * @return all available pets
	 */
	public List<Request> viewRequests();
	/**
	 * @param request_id
	 * @return request with the request_id
	 */
	public Status updateRequestStatus(Status statusToUpdate);
	// add statuses from ERD Pending Manager Approval, Approved, Rejected
	
	public Employee register(Employee newEmployee) throws UsernameAlreadyExistsException;
	
}

