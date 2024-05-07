package DAO.employeeDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DataBase;
import dataModel.RoleModel;
import dataModel.employeeDataModel;
import dataModel.employeeDetails.Employee_leaveModel;

public class employee_department {
	public int add(int employee_id,int department_id,int role_id) throws ClassNotFoundException
	{
		DataBase db;
		int employee_department_id=0;
		
		  String query="insert into employee_department(employee_id,department_id,role_id) values (?,?,?)";
	       try {
	    	 db=new DataBase();
	    	Connection connection=db.getConnection();
			PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,employee_id);
			ps.setInt(2, department_id);
			ps.setInt(3, role_id);
			ps.execute();
			ResultSet rs=ps.getGeneratedKeys();
			Employee_leaveModel leave=new Employee_leaveModel();
			if(rs.next()) {
				
			 employee_department_id=	rs.getInt(1);
			return employee_department_id;
			    
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
				
		}
	       return employee_department_id;

	}
	
	public ArrayList<employeeDataModel> get(int department_id)
	{
		ArrayList<employeeDataModel>  employees=new ArrayList<>();
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
			String query=" select employee.employee_name,employee.employee_id,employee.email,role.role_name ,employee_department.department_id from employee \r\n"
					+ " join employee_department\r\n"
					+ " on employee_department.employee_id=employee.employee_id \r\n"
					+ "  join employee_role \r\n"
					+ " on employee_role.employee_id=employee.employee_id\r\n"
					+ " left join role on role.role_id=employee_role.role_id \r\n"
					+ " where role.status=1 and employee.status>=0 and employee_department.department_id=?";
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, department_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				employeeDataModel employee=new employeeDataModel();
				employee.setEmployee_name(rs.getString("employee_name"));
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setEmail(rs.getString("email"));
				RoleModel role=new RoleModel();
				role.setRole_name(rs.getString("role_name"));
				employee.setRole(role);
				employees.add(employee);
			}
			return employees;
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
			    String query="update employee_department set status=0 where id = ?";
			    PreparedStatement ps=connection.prepareStatement(query);
			    ps.setInt(1, id);
			    ps.execute();
			    return true;
			   
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(e.getMessage());
			}
			
			return false;
	}

	
		



}
