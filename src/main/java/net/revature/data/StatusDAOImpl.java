package net.revature.data;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.revature.models.Status;
import net.revature.services.ConnectionFactory;

public class StatusDAOImpl implements StatusDAO {
	private Connection connection;
//	Connection connection;
	public StatusDAOImpl(){
		 connection = ConnectionFactory.getConnection();
	}
	//private static ConnectionFactory connectionFactory = connectionFactory.getConnection();

	@Override
	public List<Status> getAll() {
		// TODO Auto-generated method stub
		List<Status> statuses = new ArrayList<Status>();
		
		String sql = "SELECT * FROM status";
		try (Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Status status = parseResultSet(resultSet);
				statuses.add(status);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return statuses;
	}
	
	private Status parseResultSet(ResultSet resultSet) throws SQLException {
		Status status = new Status();
		
		status.setStatus_id(resultSet.getInt(1));
		status.setStatus_name(resultSet.getString(2));
		
		//String statusName = (status_id == 1) ? "Good" : "Bad";
		//status.setStatus(status);
		return status;
		
	}

	@Override
	public void update(Status updatedObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		
		String sql = "update status status_id = ?, status_name = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, updatedObj.getStatus_id());
			preparedStatement.setString(2, updatedObj.getStatus_name());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			if(count !=1) {
				System.out.println("Something went wrong with the update.");
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
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(Status objToDelete) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		
		String sql = "delete from status where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getStatus_id());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			if (count!= 1) {
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
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
	}

	@Override
	public Status getById(int id) {
		// TODO Auto-generated method stub
		Status status = null;
		
		String sql = "SELECT * FROM status WHERE id = ?";
		
		try (Connection connection = ConnectionFactory.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				status = parseResultSet(resultSet);
			}else {
				System.out.println("Something went wrong when getting status.");
			}
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return status;
	}

	@Override
	public int create(Status newObj) {
		// TODO Auto-generated method stub
		String sql = "insert into status (status_id, status_name) (default, ?,?)";
		try {		
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, newObj.getStatus_id());
			preparedStatement.setString(2, newObj.getStatus_name());
			
			  int status_id;
	            if (newObj.getStatus_name().equals("available")) {
	                status_id = 1;
			}
	            else {
	            	status_id = 2;
	            }
		preparedStatement.setInt(1, status_id);
		
		int count = preparedStatement.executeUpdate();
		
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		
		if (count > 0) {
			System.out.println("Status Updated.");
			
			resultSet.next();
			int id = resultSet.getInt(1);
			return id;
		}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}


	}
	
