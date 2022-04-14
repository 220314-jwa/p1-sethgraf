package net.revature.services;

import net.revature.exceptions.UsernameAlreadyExistsException;

import java.util.List;

import net.revature.data.*;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.RequestAlreadySubmittedException;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.models.Status;

public class UserServiceImpl implements UserService {
	private EmployeeDAO employeeDao = DAOFactory.getEmployeeDAO();
	private RequestDAO requestDao = DAOFactory.getRequestDAO();
	private StatusDAO statusDao = DAOFactory.getStatusDAO();

	@Override
	public Employee logIn(String username, String password) throws IncorrectCredentialsException {
		Employee employeeFromDatabase = employeeDao.getByUsername(username);
		if (employeeFromDatabase != null && employeeFromDatabase.getPassword().equals(password)) {
			return employeeFromDatabase;
		} else {
			throw new IncorrectCredentialsException();
		}
	}

	@Override
	public Status updateRequestStatus(Status statusToUpdate) {
		// TODO Auto-generated method stub
		if (statusDao.getById(statusToUpdate.getStatus_id()) != null) {
			statusDao.update(statusToUpdate);
			statusToUpdate = statusDao.getById(statusToUpdate.getStatus_id());
			return statusToUpdate;
		}
		return null;
	}
	@Override
	public Employee register(Employee newEmployee) throws UsernameAlreadyExistsException {
		int id = employeeDao.create(newEmployee);
		if (id != 0) {
			newEmployee.setEmployee_id(id);
			return newEmployee;
		} else {
			throw new UsernameAlreadyExistsException();
		}
	}
	
	@Override
	public Request submitRequest(Request newRequest) throws RequestAlreadySubmittedException {
		int request_id = requestDao.create(newRequest);
		if(request_id != 0) {
			newRequest.setRequest_id(request_id);
			return newRequest;
		}else {
			throw new RequestAlreadySubmittedException();
		}
		
	}
/*
	@Override
	public Request submitRequest(Request requestToSubmit) throws RequestAlreadySubmittedException {
		// TODO Auto-generated method stub
		requestToSubmit = requestDao.getById(requestToSubmit.getRequest_id());
		if(requestToSubmit.getStatus_id() == (1)){
			throw new RequestAlreadySubmittedException();
		}else {
//			employee = employeeDao.getById(employee.getEmployee_id());
//			if(employee !=null) {
				requestToSubmit.setStatus_id(2);
				try {
					requestDao.update(requestToSubmit);
				}finally {
					System.out.println("request has been submitted.");
				}
			}
		return requestToSubmit;
		}
	*/	
	@Override
	public List<Request> viewRequests() {
		List<Request> requests = requestDao.getAll();
		return requests;
	}
	}

