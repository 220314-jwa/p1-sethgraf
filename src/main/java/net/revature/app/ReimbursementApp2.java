package net.revature.app;

import java.util.Map;
import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import net.revature.data.DAOFactory;
import net.revature.data.DepartmentDAO;
import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.models.Department;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.services.UserService;
import net.revature.services.UserServiceImpl;
/*import net.revature.data.DAOFactory;
import net.revature.data.EmployeeDAO;
import net.revature.models.Employee;
*/
public class ReimbursementApp2 {
	private static UserService userService = new UserServiceImpl();
public static void main(String[] args) {
Javalin app = Javalin.create((config) ->{
config.enableCorsForAllOrigins();});
app.start(5432);
//works
app.post("/department", ctx ->{
	Department department = ctx.bodyAsClass(net.revature.models.Department.class);
	System.out.println(department);
	DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
	departmentDAO.create(department);
	System.out.println(department);
});
//works
//app.get("/form", )
app.post("/request", ctx ->{
    Request request = ctx.bodyAsClass(Request.class);
    RequestDAO requestDAO = DAOFactory.getRequestDAO();
    requestDAO.create(request);
    System.out.println(request);
    ctx.result("the request is working");
});
//works
app.get("/employee", ctx ->{
Employee employee = new Employee();
ctx.json(employee);
});
//does not work
app.get("/department_id/{id}", ctx ->{
int dept_id = Integer.parseInt(ctx.pathParam("id"));
DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
Department department = departmentDAO.getById(dept_id);
ctx.json(department);
});
//works
app.post("/employee", ctx ->{
    Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
    EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
    int employee_id = employeeDAO.create(employee);
    ctx.result("the employee id is:" + employee_id);
});
app.post("/login", ctx -> {
	Map<String, String> credentials = ctx.bodyAsClass(Map.class);
	String username = credentials.get("username");
	System.out.println(username);
	String password = credentials.get("password");
	System.out.println(password);

	Employee employee = userService.logIn(username, password);

	if (employee != null) {
		ctx.json(employee);
	} else {
		ctx.status(HttpCode.UNAUTHORIZED);
	}
});


}
}

		
		
		
		
		