package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import net.revature.models.Status;

class StatusDAOImplTest {
	private static StatusDAO statusDao = DAOFactory.getStatusDAO();
	private static Status testStatus = new Status();
	private static Status testNewStatus = new Status();
	
	
	@BeforeAll
	public static void setUp() {
		// this is the base test pet used for most tests
		testStatus.setStatus_id(123);
		
		// this is the pet to test create and delete
		Random rand = new Random();
		testNewStatus.setStatus_name("test_" + rand.nextLong());
		// TODO create pet in DB with name "test"
		// and set the pet's ID to the returned value
		testStatus.setStatus_id(statusDao.create(testStatus));
	}
	
	@AfterAll
	public static void cleanUp() throws SQLException {
		// TODO remove pets in DB with name containing "test"
		statusDao.delete(testStatus);
	}
	@Test
	public void testGetAll() {
		assertNotNull(statusDao.getAll());
	}
	@Test
	void testGetById() {
		int id = testStatus.getStatus_id();
		
		Status status = statusDao.getById(id);
		
		assertEquals(testStatus, status);
	}

}

