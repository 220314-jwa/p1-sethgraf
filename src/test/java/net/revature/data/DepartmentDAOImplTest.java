package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import net.revature.models.Department;

public class DepartmentDAOImplTest {
	private static DepartmentDAO departmentDao = DAOFactory.getDepartmentDAO();
	private static Department testDepartment = new Department();
	private static Department testNewDepartment = new Department();
	
	
	@BeforeAll
	public static void setUp() {
		// this is the base test pet used for most tests
		testDepartment.setDept_name("test");
		
		// this is the pet to test create and delete
		Random rand = new Random();
		testNewDepartment.setDept_name("test_" + rand.nextLong());
		// TODO create pet in DB with name "test"
		// and set the pet's ID to the returned value
		testDepartment.setDept_id(departmentDao.create(testDepartment));
	}
	
	@AfterAll
	public static void cleanUp() throws SQLException {
		// TODO remove pets in DB with name containing "test"
		departmentDao.delete(testDepartment);
	}
	@Test
	public void testGetAll() {
		assertNotNull(departmentDao.getAll());
	}
	@Test
	void testGetById() {
		int id = testDepartment.getDept_id();
		
		Department department = departmentDao.getById(id);
		
		assertEquals(testDepartment, department);
	}

}
