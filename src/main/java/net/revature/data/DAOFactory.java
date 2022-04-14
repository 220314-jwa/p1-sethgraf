package net.revature.data;

	public class DAOFactory {
	    // initialize our pet dao to be null
	    // keep static instances of our DAOs
	    // save memory, etc.
	    private static RequestDAO requestDAO = null;
	    private static EmployeeDAO employeeDAO = null;
	    private static StatusDAO statusDAO = null;
	    private static EventTypeDAO eventtypeDAO = null;
	    private static DepartmentDAO departmentDAO = null;
		
		public static RequestDAO getRequestDAO() {
			if(requestDAO==null) {
			requestDAO = new RequestDAOImpl();
		}
		return requestDAO;
	}
		public static EmployeeDAO getEmployeeDAO() {
			if(employeeDAO==null) {
				employeeDAO = new EmployeeDAOImpl();
			}
			return employeeDAO;
		}
		public static StatusDAO getStatusDAO() {
			if(statusDAO==null) {
				statusDAO = new StatusDAOImpl();
			}
			return statusDAO;
		}
		public static EventTypeDAO getEventTypeDAO() {
			if(eventtypeDAO==null) {
				eventtypeDAO = new EventTypeDAOImpl();
			}
			return eventtypeDAO;
		}
		public static DepartmentDAO getDepartmentDAO() {
			if(departmentDAO==null) {
				departmentDAO = new DepartmentDAOImpl();
			}
			return departmentDAO;
		}
	}
	    // make our constructor private, so it can't be accessed publicly
	   // private DAOFactory() { }

//	   public static RequestDAO getRequestDAO() {
//	        // make sure we're not recreating the dao if it already exists:
//	        if (requestDAO == null) {
//	            requestDAO = new RequestDAOImpl();
//	        }
//	        return requestDAO;
//	    }
//	    
//	    public static EmployeeDAO getEmployeeDAO() {
//	    	if (employeeDAO == null) {
//	    		employeeDAO = new EmployeeDAOImpl();
//	    	}
//	    	return employeeDAO;
//	    }
