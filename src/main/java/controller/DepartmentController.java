package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import DAO.DepartmentDAO;
import dataModel.DepartmentModel;

public class DepartmentController {
	
	public void getDepartment()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		
		DepartmentDAO dao=new DepartmentDAO();
		
		ArrayList<DepartmentModel> leave=dao.get();
		if(leave==null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else
		{
			     response.setStatus(HttpServletResponse.SC_OK);
				 Gson gson=new Gson();
				 String json=gson.toJson(leave);
				 try {
					response.getWriter().write(json);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	
	public void addDepartment()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String departmentName=request.getParameter("department_name");
		DepartmentDAO dao=new DepartmentDAO();
		DepartmentModel department=dao.add(departmentName);
		if(department!=null)
		{
			 response.setStatus(HttpServletResponse.SC_OK);
			 Gson gson=new Gson();
			 String json=gson.toJson(department);
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
	
	
	public void updateDepartment()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String departmentName=request.getParameter("departmentName");
		int department_id=Integer.parseInt(request.getParameter("department_id"));
		DepartmentDAO dao=new DepartmentDAO();
		DepartmentModel department=dao.update(departmentName,department_id);
		if(department!=null)
		{
			 response.setStatus(HttpServletResponse.SC_OK);
			 Gson gson=new Gson();
			 String json=gson.toJson(department);
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
	
	public void deleteDepartment()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int department_id=Integer.parseInt(request.getParameter("department_id"));
		DepartmentDAO dao=new DepartmentDAO();
		if(dao.delete(department_id))
		{
			response.setStatus(HttpServletResponse.SC_OK);	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}


}
