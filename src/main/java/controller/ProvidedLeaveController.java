package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import DAO.ProvidedLeaveDAO;
import dataModel.providedLeaveModel;

public class ProvidedLeaveController {
	
	public void getProvidedLeave()
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		
		ProvidedLeaveDAO dao=new ProvidedLeaveDAO();
		
		ArrayList<providedLeaveModel> leave=dao.get();
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
	
	public void addProvidedLeave() throws IOException
	{
		  System.out.println("hiiii");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String leaveName=null;
		int available_days=0;
	     try {
		leaveName=request.getParameter("leave_type");
		available_days=Integer.parseInt(request.getParameter("availabel_days"));
	     }
	     catch(Exception e) {
	    	 System.out.println(e);
	     }
		ProvidedLeaveDAO dao=new ProvidedLeaveDAO();
		providedLeaveModel leave=dao.add(leaveName,available_days);
		if(leave!=null)
		{
			response.setStatus(HttpServletResponse.SC_OK);
	         
			 Gson gson=new Gson();
			 String json=gson.toJson(leave);
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
	
	
	public void updateProvidedLeave()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String leaveName=request.getParameter("leave_type");
		int leave_id=Integer.parseInt(request.getParameter("leave_id"));
		int available_days=Integer.parseInt(request.getParameter("available_days"));
		ProvidedLeaveDAO dao=new ProvidedLeaveDAO();
	    providedLeaveModel leave=dao.update(leaveName,leave_id,available_days);
		if(leave!=null)
		{
			 response.setStatus(HttpServletResponse.SC_OK);
			 Gson gson=new Gson();
			 String json=gson.toJson(leave);
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
	
	public void deleteProvidedLeave()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		int leave_id=Integer.parseInt(request.getParameter("leave_id"));
		ProvidedLeaveDAO dao=new ProvidedLeaveDAO();
		if(dao.delete(leave_id))
		{
			response.setStatus(HttpServletResponse.SC_OK);	
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
		
	}
	
	


}
