package net.revature.data;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import net.revature.models.Employee;
import net.revature.services.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
	// private static ConnectionFactory connectionFactory =
	// ConnectionFactory.getConnectionFactory();

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		List<Employee> employees = new LinkedList<>();
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * employee from employee left join ______ on employee.id=_________;";
			// what to fill in the blanks
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployee_id(resultSet.getInt("id"));
				employee.setFirst_name(resultSet.getString("first_name"));
				employee.setLast_name(resultSet.getString("last_name"));
				employee.setManager_id(resultSet.getInt("manager_id"));
				employee.setDept_id(resultSet.getInt("dept_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public void update(Employee updatedObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "update employee set employee_id=?, first_name=?, last_name=?, manager_id=?, "
					+ "dept_id=?, username=?, passowrd=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, updatedObj.getEmployee_id());
			preparedStatement.setString(2, updatedObj.getFirst_name());
			preparedStatement.setString(3, updatedObj.getLast_name());
			preparedStatement.setInt(4, updatedObj.getManager_id());
			preparedStatement.setInt(5, updatedObj.getDept_id());
			preparedStatement.setString(6, updatedObj.getUsername());
			preparedStatement.setString(7, updatedObj.getPassword());

			connection.setAutoCommit(false);
			int rowsUpdated = preparedStatement.executeUpdate();

			if (rowsUpdated <= 1) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Employee objToDelete) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		String sql = "delete from request where id = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getEmployee_id());
			int count = preparedStatement.executeUpdate();
			if (count != 1) {
				System.out.println("Something went wrong with deletion!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// ask if i need to add in 
	/*
	 * @Override public void delete(Pet objToDelete) {
	 * 
	 * }
	 * 
	 * @Override public List<Request> getByStatus(String status) { return null; }
	 * 
	 * @Override public List<Request> getById (Employee Id) { return null; } }
	 */
	private static Employee parseResultSet(ResultSet resultSet) {

		Employee employee = new Employee();

		try {
			employee.setEmployee_id(resultSet.getInt(1));
			employee.setFirst_name(resultSet.getString(2));
			employee.setLast_name(resultSet.getString(3));
			employee.setManager_id(resultSet.getInt(4));
			employee.setDept_id(resultSet.getInt(5));
			employee.setUsername(resultSet.getString(6));
			employee.setPassword(resultSet.getString(7));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
 	public Employee getById(int id) {
		// TODO Auto-generated method stub
		Employee employee = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from employee join employee";
			// come back and ask question refer to line 61-62 UserPostgres.java
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee.setEmployee_id(id);
				employee.setFirst_name(resultSet.getString("first_name"));
				employee.setLast_name(resultSet.getString("last_name"));
				employee.setManager_id(resultSet.getInt("manager_id"));
				employee.setDept_id(resultSet.getInt("dept_id"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));

				// RequestDAO requestDao = DAOFactory.getRequestDAO();
				// employee.setRequest_id(requestDao.getById(employee));
				// double check this with sierra to determine what to fill in
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int create(Employee newObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "insert into employee (employee_id, first_name, last_name, manager_id, dept_id, username, password)"
					+ "values(?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, newObj.getEmployee_id());
			preparedStatement.setString(2, newObj.getFirst_name());
			preparedStatement.setString(3, newObj.getLast_name());
			preparedStatement.setInt(4, newObj.getManager_id());
			preparedStatement.setInt(5, newObj.getDept_id());
			preparedStatement.setString(6, newObj.getUsername());
			preparedStatement.setString(7, newObj.getPassword());

			connection.setAutoCommit(false);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				newObj.setEmployee_id(resultSet.getInt(1));
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newObj.getEmployee_id();
	}

	@Override
	public Employee getByUsername(String username) {
		Employee employee = null;
		try (Connection connection = ConnectionFactory.getConnection()) {
			String sql = "Select * from employee where username = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				 employee = EmployeeDAOImpl.parseResultSet(resultSet);
			} else {
				System.out.println("something went wrong getting username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}

