package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import DAO.employeeDAO;
import DAO.employeeDetails.employee_leave;
import dataModel.RoleModel;
import dataModel.employeeDataModel;
public class EmployeeController  {

	public void authenticate() throws IOException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String userName=request.getParameter("userName");	
		String password=request.getParameter("password");
		
		employeeDAO employee=new employeeDAO();
		
		HashMap<String,Integer> user=employee.authenticate(userName,password);
		if(user!=null)
		{
			 Gson gson = new Gson();
		     String json = gson.toJson(user);
			 response.getWriter().write(json);
			 
			 response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	
	}
	public void addEmployee() 
	{  
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
	   employeeDataModel employee=new employeeDataModel();
	   
	   employee.setEmployee_name(request.getParameter("employee_name"));
	   employee.setEmail(request.getParameter("mail"));
	   employee.setPassword(request.getParameter("password"));
	   
	   RoleModel role=new RoleModel();
	   
	   role.setRole_id(Integer.parseInt(request.getParameter("role_id")));
	   role.setRole_name((request.getParameter("role_name")));
	   
	   employee.setRole(role);
	   

	   employeeDAO dao=new employeeDAO();
	   try
	   {
		    employee=dao.addEmployee(employee);
		   
		if(employee!=null)
		   {
			
			     int i=1;
			     employee_leave leave_dao=new employee_leave();
			     String employee_leave="employee_leave"+i;
			     String id="leave_id"+i;
			     String leave_id=request.getParameter(id);
			     String leave_days=request.getParameter(employee_leave);
			      while(leave_id!=null) {
			    	  System.out.println("going");
			    	  leave_dao.add(employee.getEmployee_id(),Integer.parseInt(leave_id));
			    	  i++;
			    	   employee_leave="employee_leave"+i;
			    	   id="leave_id"+i;
					   leave_id=request.getParameter(id);
					   leave_days=request.getParameter(employee_leave);
			      }
			     response.setStatus(HttpServletResponse.SC_OK);
				 Gson gson=new Gson();
				 String json=gson.toJson(employee);
				 response.getWriter().write(json);
		    }
		else 
		   {
			   response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		   }
	  } 
	   catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
	  }
	}
	
	public void getEmployee()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		ArrayList<employeeDataModel> data=new ArrayList<>();
		 employeeDAO dao=new employeeDAO();
		 try 
		 {
			 data=dao.getEmployee();
			 if(data==null)
			 {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 }
			 else 
			 {
			 Gson gson=new Gson();
			 String json=gson.toJson(data);
			 response.getWriter().write(json);
     		 response.setStatus(HttpServletResponse.SC_OK);
			 }
		 }
		 catch(Exception e)
		 {
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		 }
		
	}
	
	
	public void deleteEmployee() throws IOException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
 
          System.out.println("abcdddd");		 
		int employee_Id=Integer.parseInt(request.getParameter("employee_id"));
		 System.out.println("xyz");
		 employeeDAO dao=new employeeDAO();
		 if(dao.deleteEmployee(employee_Id))
		 {
			 response.setStatus(HttpServletResponse.SC_OK);
		 }
		 else
		 {
			 response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		 }
		
	}
	
	public void updateEmployee()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
	   employeeDataModel employee=new employeeDataModel();
	   employee.setEmployee_name(request.getParameter("employee_name"));
	   employee.setEmail(request.getParameter("email"));
	   employee.setEmployee_id(Integer.parseInt(request.getParameter("employee_id")));
	  
	   employeeDAO dao=new employeeDAO();
	   try 
	   {
		   if(dao.updateEmployee(employee))
		   {
			     Gson gson=new Gson();
				 String json=gson.toJson(employee);
				 response.getWriter().write(json);
	     		 response.setStatus(HttpServletResponse.SC_OK);
			}
		   else 
		   {
			   response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		   }
	  } 
	   catch (ClassNotFoundException | IOException | SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	   
	  

  }
	
	
	public void changePassword() throws SQLException
	{
		System.out.println("change password");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int id=Integer.parseInt(request.getParameter("employee_id"));
		int status=Integer.parseInt(request.getParameter("status"));
		String password=request.getParameter("password");
		  employeeDAO dao=new employeeDAO();
		   try 
		   {
			   if(dao.changePassword(id,status,password))
			   {
				     response.setStatus(HttpServletResponse.SC_OK);
				     response.getWriter().write("success");
				}
			   else 
			   {
				   response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			   }
		  } 
		   catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public void getDirectReporting() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		int employee_id=Integer.parseInt(request.getParameter("employee_id"));
		
		employeeDAO dao=new employeeDAO();
		try {
			  ArrayList<employeeDataModel> employee=dao.getDirectReportings(employee_id);
			  Gson gson=new Gson();
			  response.getWriter().write(gson.toJson(employee));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
