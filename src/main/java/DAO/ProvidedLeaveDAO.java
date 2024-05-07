package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.employeeDetails.employee_leave;
import dataModel.providedLeaveModel;

public class ProvidedLeaveDAO {
	
	public providedLeaveModel  add(String providedLeave,int available_days)
	{
		providedLeaveModel leave=new providedLeaveModel();
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="insert into provided_leave(leave_type,days) values (?,?)";
		    PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		    ps.setString(1, providedLeave);
		    ps.setInt(2, available_days);
		    ps.execute();
	        ResultSet rs=ps.getGeneratedKeys();
		    	if(rs.next())
		    	{
		    		leave.setLeave_id(rs.getInt(1));
		    		leave.setAvailable_days(available_days);
		    		leave.setLeave_type(providedLeave);
		    		
		    	query="select employee_id from employee where status=1";
		    	ps=connection.prepareStatement(query);
		    	 rs=ps.executeQuery();
		    	 employee_leave dao=new employee_leave();
		    	 while(rs.next())
		    	 {
		    		 dao.add(rs.getInt(1),leave.getLeave_id());
		    	 }
		    	 return leave;
		    	}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		 
	}
	
	public ArrayList<providedLeaveModel> get()
	{
		DataBase db;
        System.out.println("coming");
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="select * from provided_leave where status=1";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ResultSet rs=ps.executeQuery();
		    ArrayList<providedLeaveModel> leaves=new ArrayList<>();
		    
		    while(rs.next())
		    {
		    	providedLeaveModel leave=new providedLeaveModel();
		    	leave.setLeave_id(rs.getInt("leave_id"));
		    	leave.setLeave_type(rs.getString("leave_type"));
		    	leave.setAvailable_days(rs.getInt("days"));
		    	leaves.add(leave);
		    }
		    return leaves;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	 public providedLeaveModel update(String leave_status,int leave_id,int availabel_days)
	 {
		DataBase db;
		try {
			db = new DataBase();
		    providedLeaveModel leave=new providedLeaveModel();
			Connection connection=db.getConnection();
		    String query="update provided_leave set leave_type=? ,days = days+? where leave_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    
		    ps.setString(1, leave_status);
		    ps.setInt(2, availabel_days);
		    ps.setInt(3, leave_id);
		    System.out.println(ps);
		    ps.execute();
		    return leave;

		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	 }
	 
	 public boolean delete(int leave_id)
	 {
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="update provided_leave set status=0 where leave_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setInt(1, leave_id);
		    ps.execute();
		    return true;
		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	 }

}
