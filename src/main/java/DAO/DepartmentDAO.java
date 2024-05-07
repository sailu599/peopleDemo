package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataModel.DepartmentModel;

public class DepartmentDAO {
	
	
	public  DepartmentModel  add(String departmentName)
	{
		DataBase db;
		DepartmentModel department=new DepartmentModel();
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="insert into department(department_name) values (?)";
		    PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		    ps.setString(1, departmentName);
		    ps.execute();
	        ResultSet rs=ps.getGeneratedKeys();
		    	if(rs.next())
		    	{
		    	    department.setDepartment_id(rs.getInt(1));
		    		department.setDepartment_name(departmentName);
		    		return department;
		    	}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		 
	}
	
	public ArrayList<DepartmentModel>get()
	{
		DataBase db;
		ArrayList<DepartmentModel> departments=new ArrayList();
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="select * from department where status=1";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		    	DepartmentModel department=new DepartmentModel();
		    	department.setDepartment_id(rs.getInt(1));
		    	System.out.println(rs.getString("department_name"));
	    		department.setDepartment_name(rs.getString("department_name"));
	    		System.out.println(department.getDepartment_name());
	    		departments.add(department);
		    }
		    return departments;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	 public DepartmentModel update(String departmentName,int department_id)
	 {
		DataBase db;
		try {
			db = new DataBase();
			DepartmentModel department=new DepartmentModel();
			Connection connection=db.getConnection();
		    String query="update department set department_name=? where department_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setString(1, departmentName);
		    ps.setInt(2, department_id);
		    department.setDepartment_id(department_id);
		    department.setDepartment_name(departmentName);
		    return department;

		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	 }
	 
	 public boolean delete(int department_id)
	 {
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="update department set status=0 where department_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setInt(1, department_id);
		    ps.execute();
		    return true;
		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	 }
}


