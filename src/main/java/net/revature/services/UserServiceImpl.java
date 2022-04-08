package net.revature.services;

import java.util.List;

import net.revature.data.*;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UnavailableException;
import net.revature.exceptions.UsernameAlreadyExistsException;
import net.revature.models.Employee;

public class UserServiceImpl implements UserService{
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
	
	/*@Override
	public Person getEmployeeById(int id) {
		return employeeDao.getById(id);
	}
	
	@Override
	public Employee updateEmployee(Employee employeeToUpdate) {
		if (employeeDao.getById(employeeToUpdate.getId()) != null) {
			employeeDao.update(userToUpdate);
			employeeToUpdate = employeeDao.getById(employeeToUpdate.getId());
			return employeeToUpdate;
		}
		return null;
	}
	// TODO Auto-generated method stub
	*/@Override
	public Employee register(Employee newEmployee) throws UsernameAlreadyExistsException {
		// TODO Auto-generated method stub
	/*	int newId = employeeDao = new EmployeeDAOImpl();
		if (newId > 0) {
		newEmployee.setId(newId);
		return newEmployee;
	}else if (newId == -1) {
		throw new UsernameAlreadyExistsException();
	}*/
		return null;
	}

	public List<net.revature.models.Request> viewAvailableRequests() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<net.revature.models.Request> searchRequestsByDesciption(String description) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Employee Request(Employee employee, net.revature.models.Request requestStatusId)
			throws UnavailableException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public net.revature.models.Request getRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}
	public RequestDAO getRequestDao() {
		return requestDao;
	}
	public void setRequestDao(RequestDAO requestDao) {
		this.requestDao = requestDao;
	}
	public StatusDAO getStatusDao() {
		return statusDao;
	}
	public void setStatusDao(StatusDAO statusDao) {
		this.statusDao = statusDao;
	}


}
