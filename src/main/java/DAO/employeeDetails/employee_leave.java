package DAO.employeeDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DataBase;
import dataModel.employeeDetails.Employee_leaveModel;

public class employee_leave {

	
	public Employee_leaveModel add(int employee_id,int leave_id) throws ClassNotFoundException
	{
		DataBase db;
		System.out.println("coming");
		  String query="insert into employee_leave(employee_id,leave_id) values (?,?)";
	       try {
	    	 db=new DataBase();
	    	Connection connection=db.getConnection();
			PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,employee_id);
			ps.setInt(2, leave_id);
			ps.execute();
			ResultSet rs=ps.getGeneratedKeys();
			Employee_leaveModel leave=new Employee_leaveModel();
			if(rs.next()) {
			    return leave;
			    
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
				
		}
	       return null;

	}
	
	public ArrayList<Employee_leaveModel> get(int employee_id)
	{
		ArrayList<Employee_leaveModel>  leaves=new ArrayList<>();
		DataBase db;
		try {
			
			db = new DataBase();
			Connection connection=db.getConnection();
			String query="select provided_leave.leave_type,provided_leave.days,employee_leave.used_days\r\n"
					+ "from provided_leave\r\n"
					+ "join employee_leave on employee_leave.leave_id=provided_leave.leave_id\r\n"
					+ "where provided_leave.status=1 and employee_leave.employee_id="+employee_id;
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Employee_leaveModel leave=new Employee_leaveModel();
				leave.setDays(rs.getInt("days"));
				leave.setUsed_days(rs.getInt("used_days"));
				leave.setLeave_type(rs.getString("leave_type"));
				leaves.add(leave);
			}
			return leaves;
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return null;
	
	}
	
	
	public boolean delete(int id)
	{
			DataBase db;
			try {
				db = new DataBase();
				Connection connection=db.getConnection();
			    String query="update employee_leave set status=0 where available_leave_id = ?";
			    PreparedStatement ps=connection.prepareStatement(query);
			    ps.setInt(1, id);
			    ps.execute();
			    return true;
			   
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(e.getMessage());
			}
			
			return false;
	}

	public boolean update(int available_days,int employee_leave_id) {
	
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="update employee_leave set available_days=available_days+? where available_leave_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setInt(1, available_days);
		    ps.setInt(2, employee_leave_id);
		    ps.execute();
		    return true;
		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;

	}
	
}
