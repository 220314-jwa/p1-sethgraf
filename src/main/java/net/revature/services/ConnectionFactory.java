package net.revature.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// a factory is responsible for generating an object and returning it
// singleton is a design pattern where we only create one instance of a class, saves memory so we're not re-instantiating it every time
public class ConnectionFactory {
    // private - only this class can directly access
    // static - it belongs to the class, rather than a specific instance (singleton design pattern)
    private static Connection connection = null;
/*private static Properties properties;

	private ConnectionFactory() {
		InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			properties = new Properties();
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionFactory getConnectionFactory() {
		if (connectionFactory==null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
    // return our connection to the database:
    public static Connection getConnection() {
    	Connection connection = null;
//    	String url = properties.getProperty("url");
//        String username = properties.getProperty("username");
//        String password = properties.getProperty("password");
*/public static Connection getConnection() {
	if(connection == null) {
    	String url = "jdbc:postgresql://database-1.cbv7gfdov7ji.us-east-1.rds.amazonaws.com:5432/postgres";
    	String username = "postgres";
    	String password = "Kgsg7072!";
        // try connecting to the database:
        try {
            // get connection
       
        	connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            // if something goes wrong, view the stack trace
            e.printStackTrace();
        }
    }
	return connection;
}
}
