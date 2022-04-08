package net.revature.data;

import net.revature.models.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {
	
	public Employee getByUsername(String username);

}
