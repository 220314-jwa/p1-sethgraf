package net.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import net.revature.data.DAOFactory;
import net.revature.models.Employee;

public class EmployeeDAOTest {
	private static net.revature.data.EmployeeDAO EmployeeDAO = DAOFactory.getEmployeeDAO();
	private static Employee testEmp = new Employee();
	int id = testEmp.getEmployee_id();
	@Test
	public void getById() {
		int employee = EmployeeDAO.getById(id);
		assertEquals(testEmp, employee);
	}
	@Test
	public void getIdNum() {
		int employeeIdNum = EmployeeDAO.getById(id);
		assertNull(employeeIdNum);
	}
}

/*@Test
	public void testUpdate() {
		int id = 0;
		Employee employee = EmployeeDAO.getById(id);
		employee.setFirst_name("");
		EmployeeDAO.update(employee);
		Employee employee2 = EmployeeDAO.getById(id);
		System.out.println(employee2);
		assertEquals("", employee.getFirst_name());
*/
	
	
