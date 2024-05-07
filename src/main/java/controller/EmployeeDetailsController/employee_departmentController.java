package controller.EmployeeDetailsController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import DAO.employeeDetails.employee_department;
import dataModel.employeeDataModel;

public class employee_departmentController {
	
	
	public void get()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		int department_id=Integer.parseInt(request.getParameter("department_id"));
		ArrayList<employeeDataModel> data=new ArrayList<>();
		  employee_department dao=new employee_department();
		 try 
		 {
			 data=dao.get(department_id);
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
		
	     
         int employee_id=Integer.parseInt(request.getParameter("employee_id"));
         int department_id=Integer.parseInt(request.getParameter("department_id"));	
         int role_id=Integer.parseInt(request.getParameter("role_id"));	

	  
	   employee_department dao=new employee_department();
	   try
	   {
		    int id=dao.add(employee_id, department_id,role_id);
		    if(id!=0)
		   {
		         HashMap<String,Integer> data=new HashMap<>();
		         
			     response.setStatus(HttpServletResponse.SC_OK);
			     Gson gson=new Gson();
				 String json=gson.toJson(data);
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
	
	public void delete()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int employee_leave_id=Integer.parseInt(request.getParameter("employee_department_id"));
		employee_department dao=new employee_department();
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