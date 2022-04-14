package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.revature.models.EventType;
import net.revature.services.ConnectionFactory;

public class EventTypeDAOImpl implements EventTypeDAO {
	//private static ConnectionFactory connectionFactory = ConnectionFactory.getConnectionFactory();

	@Override
	public List<EventType> getAll() {
		// TODO Auto-generated method stub
		 List<EventType> eventtypes = new ArrayList<EventType>();

	        String sql = "SELECT * FROM eventtype";
	        try (Connection connection = ConnectionFactory.getConnection()){
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            // get the result from our query:
	            ResultSet resultSet = preparedStatement.executeQuery();
	            // because the resultSet has multiple pets in it, we don't just want an if-statement. We want a loop:
	            while(resultSet.next()) {
	                EventType eventtype = parseResultSet(resultSet);
	                eventtypes.add(eventtype);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return eventtypes;
	    }

	    // given a result Set, parse it out and then return the pet:
	    private EventType parseResultSet(ResultSet resultSet) throws SQLException {
	        EventType eventtype = new EventType();
	        // do something with the return value:
	        eventtype.setEvent_type_id(1);
	        eventtype.setEvent_type_name(null);
	        // TODO: get the status from the status db:

	        // ternary operator:
	        //               check this cond   if true       if false
	       // String status = (eventtype.getEvent_type_id() == 1) ? "Good" : "Bad";
	        // exact same thing as this conditional:
//	        if (status_id == 1) {
//	            status = "Available";
//	        }
//	        else {
//	            status = "Adopted";
//	        }
	      // eventtype.setEvent_type_name(eventtype);
	        return eventtype;

	}

	@Override
	public void update(EventType updatedObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
    	// we create the template for the SQL string:
    	String sql = "update eventtype set event_type_id = ?, event_type_name = ?";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setInt(1,updatedObj.getEvent_type_id());
        	preparedStatement.setString(2,updatedObj.getEvent_type_name());
        	// TODO: Check the status database for the real value:
        	int event_type_id = (updatedObj.getEvent_type_name().equals("Available")) ? 1 : 2;	
        	connection.setAutoCommit(false);
        	// return a count of how many records were updated
        	int count = preparedStatement.executeUpdate();
        	if(count != 1) {
        		System.out.println("Oops! Something went wrong with the update!");
        		connection.rollback();
        	} else connection.commit();
        	
    		
    	} catch(SQLException e) {
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
	public void delete(EventType objToDelete) {
		// TODO Auto-generated method stub
	Connection connection = ConnectionFactory.getConnection();
    	
    	String sql = "delete from eventtype where id = ?;";
    	try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getEvent_type_id());
    		preparedStatement.setString(2, objToDelete.getEvent_type_name());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			System.out.println("Something went wrong with the deletion!");
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
	public int create(EventType newObj) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		
		String sql = "insert into event_type_id, event_type_name(default, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, newObj.getEvent_type_id());
			preparedStatement.setString(2, newObj.getEvent_type_name());
			
			int event_type_id;
			if (newObj.getEvent_type_name().equals("Available")) {
				event_type_id = 1;
			}
			else {
				event_type_id = 2;
			}
	preparedStatement.setInt(1, event_type_id);
	connection.setAutoCommit(false);
	int count = preparedStatement.executeUpdate();
	
	ResultSet resultSet = preparedStatement.getGeneratedKeys();
	   if (count > 0) {
           System.out.println("Event Type ID Added!");
           // return the generated id:
           // before we call resultSet.next(), it's basically pointing to nothing useful
           // but moving that pointer allows us to get the information that we want
           resultSet.next();
           int id = resultSet.getInt(1);
           newObj.setEvent_type_id(event_type_id);
           connection.commit(); // commit the changes to the DB
       }
       // if 0 rows are affected, something went wrong:
       else {
           System.out.println("Something went wrong when trying to add pet!");
           connection.rollback(); // rollback the changes
       }
   } catch (SQLException e){
       // print out what went wrong:
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
   
   return newObj.getEvent_type_id();
}

	@Override
	public EventType getById(int id) {
		// TODO Auto-generated method stub
	       EventType eventtype = null;
	        // placeholder for our final sql string
	        // ? placeholder for our id:
	        // * means all fields
	        // but we specify an id so we only get one single pet:
	        String sql = "SELECT * FROM event_type WHERE id = ?";

	        try (Connection connection = ConnectionFactory.getConnection()) {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            // if result set doesn't point to a next value, that means something went wrong
	            if(resultSet.next()) {
	                eventtype = parseResultSet(resultSet);
	                // now, we've created a pet Java object based on the info from our table
	            } else {
	                System.out.println("Something went wrong when querying the Event Type!");
	                // return null in this case
	            }
	        } catch (SQLException e){
	            e.printStackTrace();
	        }

	        return eventtype;

	}

}
