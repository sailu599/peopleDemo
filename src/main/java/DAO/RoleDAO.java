package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataModel.RoleModel;



public class RoleDAO {
	
	
	public RoleModel addRole(RoleModel role) throws ClassNotFoundException
	{
			DataBase db=new DataBase();
			Connection connection=db.getConnection();
			
			  String query="insert into role(role_name,role_level) values (?,?)";
		       try {
				PreparedStatement ps=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, role.getRole_name());
				ps.setInt(2, role.getRole_level());
				ps.execute();
				ResultSet rs=ps.getGeneratedKeys();
				rs.next();
				role.setRole_id(rs.getInt(1));
				role.setStatus(1);
				connection.close();
				return role;
			} catch (SQLException e) {
					e.printStackTrace();
					
			}
		       return null;


	}

	
	public ArrayList<RoleModel> getRole()
	{
		DataBase db;
		ArrayList<RoleModel> roles=new ArrayList<>();
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
			String query="select * from role where status=1";
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet data=ps.executeQuery();
			while(data.next())
			{
				RoleModel role=new RoleModel();
				role.setRole_id(data.getInt("role_Id"));
				role.setRole_level(data.getInt("role_level"));
				role.setRole_name(data.getString("role_name"));
				role.setStatus(data.getInt("status"));
				roles.add(role);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return roles;
		
		
	}
	
	 public RoleModel update(String role_name,int role_id,int role_level)
	 {
		DataBase db;
		try {
			db = new DataBase();
			RoleModel role=new RoleModel();
			Connection connection=db.getConnection();
		    String query="update role set role_name=?,role_level=? where role_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setString(1, role_name);
		    ps.setInt(2, role_level);
		    ps.setInt(3,role_id);
		    ps.execute();
		    role.setRole_id(role_id);
		    role.setRole_level(role_level);
		    role.setRole_name(role_name);
		    return role;

		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	 }
	 
	 public boolean delete(int role_id)
	 {
		DataBase db;
		try {
			db = new DataBase();
			Connection connection=db.getConnection();
		    String query="update role set status=0 where role_id = ?";
		    PreparedStatement ps=connection.prepareStatement(query);
		    ps.setInt(1, role_id);
		    ps.execute();
		    return true;
		   
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	 }

}
