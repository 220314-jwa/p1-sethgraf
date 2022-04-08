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
			return requestDAO;
		}
		public static void setRequestDAO(RequestDAO requestDAO) {
			DAOFactory.requestDAO = requestDAO;
		}
		public static EmployeeDAO getEmployeeDAO() {
			return employeeDAO;
		}
		public static void setEmployeeDAO(EmployeeDAO employeeDAO) {
			DAOFactory.employeeDAO = employeeDAO;
		}
		public static StatusDAO getStatusDAO() {
			return statusDAO;
		}
		public static void setStatusDAO(StatusDAO statusDAO) {
			DAOFactory.statusDAO = statusDAO;
		}
		public static EventTypeDAO getEventtypeDAO() {
			return eventtypeDAO;
		}
		public static void setEventtypeDAO(EventTypeDAO eventtypeDAO) {
			DAOFactory.eventtypeDAO = eventtypeDAO;
		}
		public static DepartmentDAO getDepartmentDAO() {
			return departmentDAO;
		}
		public static void setDepartmentDAO(DepartmentDAO departmentDAO) {
			DAOFactory.departmentDAO = departmentDAO;
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
