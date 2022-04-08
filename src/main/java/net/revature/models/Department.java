package net.revature.models;

import java.util.Date;

public class Department { 
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
	

}
