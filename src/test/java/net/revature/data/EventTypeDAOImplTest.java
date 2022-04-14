package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import net.revature.models.EventType;

class EventTypeDAOImplTest {
	private static EventTypeDAO eventtypeDao = DAOFactory.getEventTypeDAO();
	private static EventType testEventType = new EventType();
	private static EventType testNewEventtype = new EventType();
	
	@BeforeAll
	public static void setUp() {
		// this is the base test pet used for most tests
		testEventType.setEvent_type_name("test");
		
		// this is the pet to test create and delete
		Random rand = new Random();
		testNewEventtype.setEvent_type_name("test_" + rand.nextLong());
		// TODO create pet in DB with name "test"
		// and set the pet's ID to the returned value
		testEventType.setEvent_type_id(eventtypeDao.create(testEventType));
	}
	
	@AfterAll
	public static void cleanUp() throws SQLException {
		// TODO remove pets in DB with name containing "test"
		eventtypeDao.delete(testEventType);
	}
	@Test
	public void testGetAll() {
		assertNotNull(eventtypeDao.getAll());
	}
	@Test
	void testGetById() {
		int id = testEventType.getEvent_type_id();
		
		EventType eventtype = eventtypeDao.getById(id);
		
		assertEquals(testEventType, eventtype);
	}

}