package net.revature.app;

import io.javalin.Javalin;
import net.revature.data.DAOFactory;
import net.revature.data.EmployeeDAO;
import net.revature.models.Employee;

public class ReimbursementApp2 {
public static void main(String[] args) {
Javalin app = Javalin.create();
app.start(8088);
app.post("/request", ctx -> {
	//getting the user id with employee id
	Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
	EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	
	int employee_id = employeeDAO.create(employee);
	
	ctx.result("the user id is " + employee_id);
});
//Getting all the users:both authors and editors
app.get("/employee", ctx->{
	int id = Integer.parseInt(ctx.pathParam("id"));
	EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	int resultEmployee= employeeDAO.getById(id);
	ctx.json(resultEmployee); 
	
});

//update users
app.put("/employee", ctx->{
	Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
	EmployeeDAO employeeDAO= DAOFactory.getEmployeeDAO();
	employeeDAO.update(employee);
	
	
});
}
}

		
		
		
		
		