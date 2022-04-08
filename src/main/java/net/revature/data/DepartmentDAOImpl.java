package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.revature.models.Department;
import net.revature.models.Employee;
import net.revature.services.ConnectionFactory;

public class DepartmentDAOImpl implements DepartmentDAO{
	private static ConnectionFactory connectionFactory = ConnectionFactory.getConnectionFactory();

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		List<Department> departments = new ArrayList<Department>();
		String sql = "SELECT * FROM department";
		
		try (Connection connection = connectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Department department = parseResultSet(resultSet);
				departments.add(department);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}
	private Department parseResultSet(ResultSet resultSet) throws SQLException{
		Department department = new Department();
		department.setDept_id(resultSet.getInt(1));
		department.setDept_name(resultSet.getString(2));
		department.setDept_head_id(resultSet.getInt(3));
		
		return department;
	}

	@Override
	public void update(Department updatedObj) {
		// TODO Auto-generated method stub
	
	Connection connection = connectionFactory.getConnection();
	String sql = "update department dept_id = ?, dept_name = ?, dept_head_id = ?";
	
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setInt(1, updatedObj.getDept_id());
		preparedStatement.setString(2, updatedObj.getDept_name());
		preparedStatement.setInt(3, updatedObj.getDept_head_id());
		
		int status_id = (updatedObj.getDept_name().equals("Available")) ? 1 : 2;
		connection.setAutoCommit(false);
		
		int count = preparedStatement.executeUpdate();
		if (count !=1) {
			System.out.println("Oops something went wrong with the update.");
			connection.rollback();
		}else connection.commit();
	}catch(SQLException e) {
		e.printStackTrace();
		try {
			connection.rollback();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}finally {
		try {
			connection.close();
		}catch (SQLException e) {
		e.printStackTrace();
		
	}
	}
	}
	@Override
	public void delete(Department objToDelete) {
		// TODO Auto-generated method stub
		Connection connection = connectionFactory.getConnection();
		
		String sql = "delete from department where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getDept_id());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			if (count !=1) {
				System.out.println("Something went wrong with deletion.");
				connection.rollback();
			}else connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Department getById(int id) {
		// TODO Auto-generated method stub
	Department department = null;
	String sql = "SELECT * FROM department WHERE id = ?";
	
	try(Connection connection = connectionFactory.getConnection()){
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			department = parseResultSet(resultSet);
			
		}else {
			System.out.println("Something went wrong when obtaining the department.");
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return department;
	}

	@Override
	public int create(Department newObj) {
		// TODO Auto-generated method stub
		Connection connection = connectionFactory.getConnection();
		
		String sql = "insert into department(dept_id, dept_name, dept_head_id)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, newObj.getDept_id());
			preparedStatement.setString(2, newObj.getDept_name());
			preparedStatement.setInt(3, newObj.getDept_head_id());
			
			int dept_id;
			if (newObj.getDept_name().equals("Available")) {
				dept_id = 1;
			}
			else {
				dept_id = 2;
			}
			preparedStatement.setInt(1, dept_id);
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if (count >0) {
			System.out.println("Department added.");
			}
			else {System.out.println("Something went wrong when adding.");
			connection.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newObj.getDept_id();}
}

