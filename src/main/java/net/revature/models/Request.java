package net.revature.models;
import java.util.Date;

public class Request {
	
	private int request_id;
	private int employee_id;
	private int submitter_id;
	private int event_type_id;
	private int status_id;
	private int cost;
	private String description;
	private String location;
	private Date submitted_at;
	private Date event_date;
	
	
	public Request() {
		setRequest_id(0);
		setEmployee_id(0);
		setEvent_type_id(0);
		setStatus_id(0);
		setEvent_date((new Date()));
		cost = 0;
		description = "";
		location = "";
		setSubmitted_at((new Date()));
		
		
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getSubmitter_id() {
		return submitter_id;
	}
	public void setSubmitter_id(int submitter_id) {
		this.submitter_id = submitter_id;
	}
	public int getEvent_type_id() {
		return event_type_id;
	}
	public void setEvent_type_id(int event_type_id) {
		this.event_type_id = event_type_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public Date getSubmitted_at() {
		return submitted_at;
	}
	public void setSubmitted_at(Date submitted_at) {
		this.submitted_at = submitted_at;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
}
