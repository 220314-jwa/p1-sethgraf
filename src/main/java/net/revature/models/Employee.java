package net.revature.models;

import java.util.Objects;

public class Employee {
	//need to generate hashcode/equals as well as toString() all done within source after highlighting class

	private int employee_id;
	private String first_name;
	private String last_name;
	private int manager_id;
	private int dept_id;
	private String username;
	private String password;
	
	public Employee() {
		setEmployee_id(0);
		setUsername("");
		setPassword("");
		setFirst_name("");
		setLast_name("");
		setManager_id(0);
		setDept_id(0);
		
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", manager_id=" + manager_id + ", dept_id=" + dept_id + ", username=" + username + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dept_id, employee_id, first_name, last_name, manager_id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return dept_id == other.dept_id && employee_id == other.employee_id
				&& Objects.equals(first_name, other.first_name) && Objects.equals(last_name, other.last_name)
				&& manager_id == other.manager_id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

}
	
