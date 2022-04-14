package net.revature.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import net.revature.exceptions.UsernameAlreadyExistsException;
import net.revature.data.DAOFactory;
import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.data.StatusDAO;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.RequestAlreadySubmittedException;
import net.revature.models.Employee;
import net.revature.models.Request;
  
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
// says that we want Mockito to create a mock version of this object
@Mock
private EmployeeDAO employeeDao;
@Mock
private RequestDAO requestDao;
@Mock
private StatusDAO statusDao;

// we need a field for the class that we're testing
@InjectMocks // this is where Mockito needs to inject the mocks
private UserService userService = new UserServiceImpl();

@Test
public void testLogInSuccessfully() throws IncorrectCredentialsException {
	// setup (arguments, expected result, etc.)
	String username = "snicholes";
	String password = "pass";
	
	// mocking: we need to mock userDao.getByUsername(username)
	// we're expecting a user with matching username & password
	Employee mockEmployee = new Employee();
	mockEmployee.setUsername(username);
	mockEmployee.setPassword(password);
	when(employeeDao.getByUsername(username)).thenReturn(mockEmployee);
	
	// call the method we're testing
	Employee actualEmployee = userService.logIn(username, password);
	
	// assertion
	assertEquals(mockEmployee, actualEmployee);
}

@Test
public void testLogInWrongUsername() {
	String username = "abc123";
	String password = "1234567890";
	
	// we need to mock userDao.getByUsername(username)
	when(employeeDao.getByUsername(username)).thenReturn(null);
	
	assertThrows(IncorrectCredentialsException.class, () -> {
		// put the code that we're expecting to throw the exception
		userService.logIn(username, password);
	});
}
@Test
public void testLogInWrongPassword() {
	String username = "snicholes";
	String password = "1234567890";
	
	Employee mockEmployee = new Employee();
	mockEmployee.setUsername(username);
	mockEmployee.setPassword("fake_password");
	when(employeeDao.getByUsername(username)).thenReturn(mockEmployee);
	
	assertThrows(IncorrectCredentialsException.class, () -> {
		// put the code that we're expecting to throw the exception
		userService.logIn(username, password);
	});
}

@Test
public void testRegisterUsernameTaken() {
	Employee newEmployee = new Employee();
	newEmployee.setUsername("snicholes");
	
	assertThrows(UsernameAlreadyExistsException.class, () -> {
		userService.register(newEmployee);
	});
}

@Test
public void testViewRequests() {
	assertNotNull(requestDao.getAll());

	}
@Test
public void testSubmitRequestSuccessfully() throws Exception {

	Request testRequest = new Request();
	when(requestDao.create(testRequest)).thenReturn(1);
	
	Request result = userService.submitRequest(testRequest);
	assertNotEquals(0, result.getRequest_id());
}

}
