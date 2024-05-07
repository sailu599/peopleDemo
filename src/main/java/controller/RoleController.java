package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import DAO.DepartmentDAO;
import DAO.RoleDAO;
import dataModel.DepartmentModel;
import dataModel.RoleModel;

public class RoleController {
	
	public void getRole()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		ArrayList<RoleModel> data=new ArrayList<>();
		 RoleDAO dao=new RoleDAO();
		 try 
		 {
			 data=dao.getRole();
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
	
	
	public void addRole()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
	    RoleModel role=new RoleModel();
	   role.setRole_name(request.getParameter("role_name"));
	   role.setRole_level(Integer.parseInt(request.getParameter("role_level")));
	   
	  
	   RoleDAO dao=new RoleDAO();
	   try
	   {
		    role=dao.addRole(role);
		if(role!=null)
		   {
			     response.setStatus(HttpServletResponse.SC_OK);
			     Gson gson=new Gson();
				 String json=gson.toJson(role);
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
	
	
	public void updateRole()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String role_name=request.getParameter("role_name");
		int role_id=Integer.parseInt(request.getParameter("role_id"));
		int role_level=Integer.parseInt(request.getParameter("role_level"));
		RoleDAO dao=new RoleDAO();
		RoleModel role=dao.update(role_name,role_id,role_level);
		if(role!=null)
		{
			 response.setStatus(HttpServletResponse.SC_OK);
			 Gson gson=new Gson();
			 String json=gson.toJson(role);
			 try {
				response.getWriter().write(json);
			 } 
			 catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}
	
	public void deleteRole()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int role_id=Integer.parseInt(request.getParameter("role_id"));
		RoleDAO dao=new RoleDAO();
		if(dao.delete(role_id))
		{
			response.setStatus(HttpServletResponse.SC_OK);	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}


	

}
