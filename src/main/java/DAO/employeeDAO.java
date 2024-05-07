package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dataModel.RoleModel;
import dataModel.employeeDataModel;

public class employeeDAO {

	public employeeDataModel addEmployee(employeeDataModel employee) throws ClassNotFoundException {
	
		DataBase db=new DataBase();
		Connection connection=db.getConnection();
		
		  String query="insert into employee(employee_name,email,password) values (?,?,?)";
	       try {
			PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, employee.getEmployee_name());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			ps.execute();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next())
			{
				employee.setEmployee_id(rs.getInt(1));
				query="insert into employee_role(employee_id,role_id) values(?,?)";
				ps=connection.prepareStatement(query);
				ps.setInt(1, employee.getEmployee_id());
				ps.setInt(2,employee.getRole().getRole_id());
				ps.execute();
				
			}
			connection.close();
			return employee;
		} catch (SQLException e) {
				e.printStackTrace();
				
		}
	       return null;


		

	}
	

	public ArrayList<employeeDataModel> getEmployee()
	{
		ArrayList<employeeDataModel>  employees=new ArrayList<>();
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
			String query="select department.department_name,employee.employee_name,employee.employee_id,employee_role.role_id,employee.email,role.role_name from employee \r\n"
					+ " left join employee_department\r\n"
					+ " on employee_department.employee_id=employee.employee_id \r\n"
					+ " left join department on department.department_id=employee_department.department_id\r\n"
					+ " left join employee_role \r\n"
					+ " on employee_role.employee_id=employee.employee_id\r\n"
					+ " left join role on role.role_id=employee_role.role_id \r\n"
					+ " where role.status=1 and (employee.status=1 or employee.status=0)";
			PreparedStatement ps=connection.prepareStatement(query);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				employeeDataModel employee=new employeeDataModel();
				employee.setEmployee_name(rs.getString("employee_name"));
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setDepartment_name(rs.getString("department_name"));
				employee.setEmail(rs.getString("email"));
				RoleModel role=new RoleModel();
				role.setRole_name(rs.getString("role_name"));
				role.setRole_id(rs.getInt("role_id"));;
				employee.setRole(role);
				employees.add(employee);
			}
			return employees;
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return null;
	
	}


	public boolean deleteEmployee(int empId) {
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
			
			String query="update employee set status=-1 where employee_id="+empId;
			System.out.println(query);
			PreparedStatement ps=connection.prepareStatement(query);
			if(ps.executeUpdate()>0)
				return true;
			else 
				return false;
			
		} catch (ClassNotFoundException | SQLException e) {
	
			e.printStackTrace();
			System.out.print(e);
		}
		
		return false;
	}


	public boolean updateEmployee(employeeDataModel employee) throws ClassNotFoundException, SQLException {
	
		 

		DataBase db=new DataBase();
		Connection connection=db.getConnection();
		
		  String query="update employee set employee_name=?,email=? where employee_id=?";
	       
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, employee.getEmployee_name());
			ps.setString(2, employee.getEmail());
			ps.setInt(3, employee.getEmployee_id());
		    try {
		    	ps.executeUpdate();
				 return true;
		    }
		    catch(Exception e){
		    	System.out.println(e.getMessage());
		    	return false;
		    }
     
	}


	public HashMap<String,Integer> authenticate(String userName, String password) {
		
		
		 String query=" select employee.employee_id,employee_role.role_id ,employee.status from employee \r\n"
		 		+ "    join employee_role \r\n"
		 		+ "    on employee.employee_id=employee_role.employee_id \r\n"
		 		+ "    where employee.employee_name=? and employee.password=? and (employee.status=1 or employee.status=0)";
		 try {
			 
			 DataBase db=new DataBase();
			 Connection	connection=db.getConnection();
			 
			 
	        PreparedStatement ps=connection.prepareStatement(query);
	        ps.setString(1, userName);
	        ps.setString(2, password);
	        
	        System.out.println(ps);
	        
	        ResultSet result=ps.executeQuery();
	        
	        HashMap<String,Integer> userModel=new  HashMap<>();
	        
	      
				if(result.next())
				{
					userModel.put("userId",result.getInt("employee_id"));
					userModel.put("role",result.getInt("role_id"));
					userModel.put("status",result.getInt("status"));
					return userModel;
				}
			}
		   catch (SQLException | ClassNotFoundException e)
	        {
				System.out.println(e.getMessage());
			}
	    	System.out.println("falseee");
			return null;
	}


	public boolean changePassword(int id, int status,String password) {
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
			String query;
			if(status==0)
				
			  query="update employee set status=1,password=? where employee_id=?";
			else
				 query="update employee set password=? where employee_id=?";
			
			System.out.println(query);
			
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,password);
			ps.setInt(2, id);
			System.out.println(ps);
			if(ps.executeUpdate()>0)
				return true;
			else 
				return false;
			
		} catch (ClassNotFoundException | SQLException e) {
	
			e.printStackTrace();
			System.out.print(e);
		}
		
		return false;
	}


	public ArrayList<employeeDataModel>  getDirectReportings(int employee_id) throws ClassNotFoundException {
		
		ArrayList<employeeDataModel>  employees=new ArrayList<>();
		DataBase db = new DataBase();
		Connection connection=db.getConnection();
	     String query="SELECT role.role_level ,department.department_id\r\n"
	     		+ "FROM employee \r\n"
	     		+ "JOIN employee_role \r\n"
	     		+ "ON employee.employee_id = employee_role.employee_id \r\n"
	     		+ "JOIN role on employee_role.role_id=role.role_id\r\n"
	     		+ "join employee_department \r\n"
	     		+ "on employee.employee_id =employee_department.employee_id \r\n"
	     		+ "join department\r\n"
	     		+ "on employee_department.department_id=department.department_id\r\n"
	     		+ "WHERE employee.employee_id="+employee_id;
	    try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				int role_level=rs.getInt("role_level");
				int department_id=rs.getInt("department_id");
				query="select employee.employee_name,employee.email,employee.employee_id,department.department_name,role.role_name\r\n"
						+ " from employee\r\n"
						+ " join employee_department on employee_department.employee_id=employee.employee_id\r\n"
						+ " join department on department.department_id=employee_department.department_id\r\n"
						+ "join employee_role on employee_role.employee_id=employee.employee_id\r\n"
						+ "join role on role.role_id=employee_role.role_id where employee.status>=0 and role.role_level>? and department.department_id=?";
				  ps=connection.prepareStatement(query);
				ps.setInt(1, role_level);
				ps.setInt(2, department_id);
				rs=ps.executeQuery();
				while(rs.next()) {
					
					employeeDataModel employee=new employeeDataModel();
					employee.setEmployee_name(rs.getString("employee_name"));
					employee.setEmployee_id(rs.getInt("employee_id"));
					employee.setDepartment_name(rs.getString("department_name"));
					employee.setEmail(rs.getString("email"));
					RoleModel role1=new RoleModel();
					role1.setRole_name(rs.getString("role_name"));
					employee.setRole(role1);
					employees.add(employee);
				  
				}
				return employees;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return employees;
		
	}
	
		

}
