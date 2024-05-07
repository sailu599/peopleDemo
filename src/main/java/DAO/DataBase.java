package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	  private Connection connection;
	  public Connection getConnection() {
		return connection;
	}
	public DataBase() throws ClassNotFoundException
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
	      try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","sailu");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	  }
	  
	  
	   
}
