package controller.EmployeeDetailsController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import DAO.employeeDetails.employee_leave;
import dataModel.employeeDetails.Employee_leaveModel;

public class employee_leaveController {
	
	
	public void get()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		int employee_id=Integer.parseInt(request.getParameter("employee_id"));
		ArrayList<Employee_leaveModel> data=new ArrayList<>();
		  employee_leave dao=new employee_leave();
		 try 
		 {
			 data=dao.get(employee_id);
			 if(data==null)
			 {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 }
			 else 
			 {
			 Gson gson=new Gson();
			 String json=gson.toJson(data);
			 response.getWriter().write(json);
			 }
		 }
		 catch(Exception e)
		 {
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		 }
		
	}
	
	
	public void add()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		System.out.println("hiiii");
	     Employee_leaveModel leave=new Employee_leaveModel();
         int employee_id=Integer.parseInt(request.getParameter("employee_id"));
         int leave_id=Integer.parseInt(request.getParameter("leave_id"));
         int available_days=Integer.parseInt(request.getParameter("available_days"));	   
	   employee_leave dao=new employee_leave();
	   try
	   {
		    leave=dao.add(employee_id, leave_id);
		    if(leave!=null)
		   {
			     response.setStatus(HttpServletResponse.SC_OK);
			     Gson gson=new Gson();
				 String json=gson.toJson(leave);
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
	
	
	public void update()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		int available_days=Integer.parseInt(request.getParameter("available_days"));
		int employee_leave_id=Integer.parseInt(request.getParameter("employee_leave_id"));
		employee_leave dao=new employee_leave();
		if(dao.update(available_days,employee_leave_id))
		{
			 response.setStatus(HttpServletResponse.SC_OK);
	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}
	
	public void delete()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int employee_leave_id=Integer.parseInt(request.getParameter("employee_leave_id"));
		employee_leave dao=new employee_leave();
		if(dao.delete(employee_leave_id))
		{
			response.setStatus(HttpServletResponse.SC_OK);	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}


}
