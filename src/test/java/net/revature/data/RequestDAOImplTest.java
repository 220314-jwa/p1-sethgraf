package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import net.revature.models.Request;

class RequestDAOImplTest {
	private static RequestDAO requestDao = DAOFactory.getRequestDAO();
	private static Request testRequest = new Request();
	private static Request testNewRequest = new Request();

	@BeforeAll
	public static void setUp() {
		testRequest.setSubmitter_id(123);

		Random rand = new Random();
		testNewRequest.setRequest_id(123 + rand.nextInt());
		testRequest.setRequest_id(requestDao.create(testRequest));
	}

	@AfterAll
	public static void cleanUp() throws SQLException {
		requestDao.delete(testRequest);
	}

	@Test
	public void getByIdExists() {
		int id = testRequest.getRequest_id();

		Request request = requestDao.getById(id);

		assertEquals(testRequest, request);
	}

	@Test
	public void getByIdDoesNotExist() {
		Request request = requestDao.getById(0);
		assertNull(request);
	}

	@Test
	public void testUpdateRequestExists() {
		assertDoesNotThrow(() -> {
			requestDao.update(testRequest);
		});
	}

	@Test
	public void testRequestDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			requestDao.update(new Request());
		});
	}

	@Test
	public void testDeleteRequestExists() {
		assertDoesNotThrow(() -> {
			requestDao.delete(testNewRequest);
		});

	}

	@Test
	public void testDeleteDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			requestDao.delete(new Request());
		});
	}

	@Test
	public void testCreateRequestSuccessfully() {
		int id = requestDao.create(testNewRequest);
		testNewRequest.setRequest_id(id);

		assertNotEquals(0, id);
	}

	@Test
	public void testGetAll() {
		assertNotNull(requestDao.getAll());
	}

}
