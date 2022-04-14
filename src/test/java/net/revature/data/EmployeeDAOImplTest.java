package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import net.revature.models.Employee;

public class EmployeeDAOImplTest {
	private static EmployeeDAO employeeDao = DAOFactory.getEmployeeDAO();
	private static Employee testEmployee = new Employee();
	private static Employee testNewEmployee = new Employee();

	@BeforeAll
	public static void setUp() {
		// this is the base test user used for most tests
		testEmployee.setUsername("Seth");

		// this is the user to test create and delete
		Random rand = new Random();
		testNewEmployee.setUsername("Seth" + rand.nextLong());

		// TODO create user in DB with username "test"
		// and set the user's ID to the returned value
		testEmployee.setEmployee_id(employeeDao.create(testEmployee));
	}

	@AfterAll
	public static void cleanUp() {
		employeeDao.delete(testEmployee);
	}

	@Test
	public void testGetByIdDoesNotExist() {
		Employee employee = employeeDao.getById(0);
		assertNull(employee);
	}

	@Test
	public void testGetByIdDoesExist() {
		int id = testEmployee.getEmployee_id();

		Employee employee = employeeDao.getById(id);

		assertEquals(testEmployee, employee);
	}

	@Test
	public void testUpdateEmployeeExists() {
		assertDoesNotThrow(() -> {
			employeeDao.update(testEmployee);
		});
	}

	@Test
	public void testUpdateEmployeeDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			employeeDao.update(new Employee());
		});
	}

	@Test
	public void testGetAll() {
		assertNotNull(employeeDao.getAll());
	}

	@Test
	public void testDeleteEmployeeExists() {
		assertDoesNotThrow(() -> {
			employeeDao.delete(testNewEmployee);
		});
	}

	@Test
	public void testDeleteEmployeeDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			employeeDao.delete(new Employee());
		});
	}

	@Test
	public void testCreateEmployeeSuccessfully() {
		int id = employeeDao.create(testNewEmployee);
		testNewEmployee.setEmployee_id(id);

		assertNotEquals(0, id);
	}

	@Test
	public void testCreateEmployeeDuplicateUsername() {
		int id = employeeDao.create(testEmployee);

		assertEquals(0, id);
	}
}
