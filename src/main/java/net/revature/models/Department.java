package net.revature.models;

import java.util.Date;
import java.util.Objects;

public class Department { 
	//need to generate hashcode/equals as well as toString() all done within source after highlighting class

	private int dept_id;
	private String dept_name;
	private int dept_head_id;
	
	public Department() {
		setDept_id(0);
		setDept_name("");
		setDept_head_id(0);}
	
	public int getDept_id(){
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getDept_head_id() {
		return dept_head_id;
	}
	public void setDept_head_id(int dept_head_id) {
		this.dept_head_id = dept_head_id;
	}
	
	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name + ", dept_head_id=" + dept_head_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dept_head_id, dept_id, dept_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return dept_head_id == other.dept_head_id && dept_id == other.dept_id
				&& Objects.equals(dept_name, other.dept_name);
	}
}
