package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataModel.leaveModel;

public class LeaveDAO {

	public void add(leaveModel leaveDetails)
	{
		try {
			DataBase db=new DataBase();
			Connection connection=db.getConnection();
			String query="INSERT INTO your_table (leaveDescription, empId,leaveDate) VALUES (?, ?, ?)";
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, leaveDetails.getLeaveDiscription());
			ps.setInt(2, leaveDetails.getEmpId());
			ps.setDate(3, leaveDetails.getLeaveDate());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
