package net.revature.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import net.revature.models.Request;
import net.revature.services.ConnectionFactory;
import java.util.Date;

public class RequestDAOImpl implements RequestDAO {
	Connection connection;
	
	public RequestDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public List<Request> getAll() {
		// TODO Auto-generated method stub
		List<Request> requests = new ArrayList<Request>();
		
		String sql = "SELECT * FROM request";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Request request = parseResultSet(resultSet);
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return requests;
	}
	
	private Request parseResultSet(ResultSet resultSet) throws SQLException {
		Request request = new Request();
		
		request.setRequest_id(resultSet.getInt(1));
		request.setSubmitter_id(resultSet.getInt(2));
		request.setEvent_type_id(resultSet.getInt(3));
		request.setStatus_id(resultSet.getInt(4));
		request.setEvent_date(resultSet.getDate(5));
		request.setCost(resultSet.getInt(6));
		request.setDescription(resultSet.getString(7));
		request.setLocation(resultSet.getString(8));
		request.setSubmitted_at(resultSet.getDate(9));
		int status_id = resultSet.getInt(1);
	
		String status = (status_id == 1) ? "Available" : "Pending";
		
		request.setStatus_id(status_id);
		return request;
	}

	@Override
	public void update(Request updatedObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		String sql ="update request set name = ?, request_id = ?, submitter_id = ?, event_type_id = ?, status_id = ?, event_date = ?,"
				+ "cost = ?, description = ?, location = ?, submitted_at = ?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, updatedObj.getRequest_id());
				preparedStatement.setInt(2, updatedObj.getSubmitter_id());
				preparedStatement.setInt(3, updatedObj.getEvent_type_id());
				preparedStatement.setInt(4, updatedObj.getStatus_id());
				preparedStatement.setTimestamp(5, new Timestamp(updatedObj.getEvent_date().getTime()) );
				preparedStatement.setInt(6, updatedObj.getCost());
				preparedStatement.setString(7, updatedObj.getDescription());
				preparedStatement.setString(8, updatedObj.getLocation());
				preparedStatement.setTimestamp(9, new Timestamp(updatedObj.getSubmitted_at().getTime()));
				
				int status_id = (updatedObj.getDescription() == "Available") ? 1 : 2;
				
				connection.setAutoCommit(false);
				//return a count of how many records were updated
				int count = preparedStatement.executeUpdate();
				if(count !=1) {
					System.out.println("Update Pending.");
					connection.rollback();
				} else connection.commit();
			
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
			}
			}
			}

	@Override
	public void delete(Request objToDelete) {
		// TODO Auto-generated method stub
		
		String sql = "Delete from result where id = ?;";
		try (Connection connection = ConnectionFactory.getConnection()) { 
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getRequest_id());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			if (count !=1) {
				System.out.println("Something went wrong with deletion.");
				connection.rollback();
			} else connection.commit();
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
	public Request getById(int id) {
		// TODO Auto-generated method stub
		Request request = null;
				
				String sql = "SELECT * FROM Request WHERE id = ?";
				try (Connection connection = ConnectionFactory.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
			    if (resultSet.next()) {
			    
			    request = parseResultSet(resultSet);
			    
			    } else {
			    	System.out.println("Something went wrong.");
			    } 
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
		return request;
	}


	@Override
	public int create(Request newObj) {
		 String sql = "insert into request (request_id, submitter_id,event_type_id, status_id, event_date, "
		 		+ "cost, description,location, submitted_at)";
		 
		 try {
		       PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
		       preparedStatement.setInt(1, newObj.getRequest_id());
		        preparedStatement.setInt(2, newObj.getSubmitter_id());
		        preparedStatement.setInt(3, newObj.getEvent_type_id());
		        preparedStatement.setInt(4, newObj.getStatus_id());
		        preparedStatement.setTimestamp(5, new Timestamp (newObj.getEvent_date().getTime()));
		        preparedStatement.setInt(6, newObj.getCost());
		        preparedStatement.setString(7, newObj.getDescription());
		        preparedStatement.setString(8, newObj.getLocation());
		        preparedStatement.setTimestamp(9, new Timestamp (newObj.getSubmitted_at().getTime()));
	        
	       int count = preparedStatement.executeUpdate();
	       
	       ResultSet resultSet = preparedStatement.getGeneratedKeys();
	       
	       if (count>0) {
	    	   System.out.println("Request Completed.");
	    	   
	    	   resultSet.next();
	    	   
	    	   int id = resultSet.getInt(1);
	    	   newObj.setRequest_id(id);
	    	   connection.commit(); //commit the changes to the database
	       }
	       else {
	    	   System.out.println("Request Failed.");
	    	   connection.rollback(); //rollback the changes
	       }
		 } catch (SQLException e) {
			 //prints out what went wrong
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
		 return newObj.getRequest_id();
	}
	
	public void genericCreate(String name) {
		final String SQL = "insert into generic_table Values(default, ? )";
		 try(
				 Connection connection = ConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(SQL)) 
		 {
			 statement.setString(1, name);

			 statement.execute();
			 
		 } catch (SQLException e) {
			 //prints out what went wrong
			 e.printStackTrace();
		
	}
	}
}
	
//add list request by status and list of by owner is optional

