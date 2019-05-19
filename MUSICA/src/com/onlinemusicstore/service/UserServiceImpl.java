package com.onlinemusicstore.service;

import com.onlinemusicstore.model.User;
import com.onlinemusicstore.conn.DBConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserServiceImpl implements IUserService{
	
public void addUser(User user) {
		
		String addUserQuery = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
		//String addUserAccountQuery = "INSERT INTO useraccount VALUES(?,?,?)";
		
		
		try {
			// add data to user table
			PreparedStatement ps = DBConnection.getDBconnection().prepareStatement(addUserQuery);
			
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getCountry());
			ps.setString(6, user.getUserName());
			ps.setString(7, user.getEmail());
			ps.setInt(8, user.getMobileNumber());
			ps.setString(9, user.getPassword());
			ps.setString(10, user.getType());
			
			ps.executeUpdate();
			
		
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}


	public User updateUser(User user) {
		
		String updateUserQuery = "UPDATE user "
				+ "SET firstName = ?, lastName = ?, gender = ?, country = ?, email = ?, mobileNumber = ? "
				+ "WHERE userID = ?";
		
		try {
			PreparedStatement ps = DBConnection.getDBconnection().prepareStatement(updateUserQuery);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getCountry());
			ps.setString(5, user.getUserName());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getMobileNumber());
			ps.setString(8, user.getType());
			ps.setString(9, user.getUserID());
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	
	public User loginUser(User user) {
		
	
		String uID = null;
		
		String loginQuery1 = "SELECT * FROM user WHERE userName = ? AND password = ?";
		
		
				PreparedStatement ps;
				try {
					ps = DBConnection.getDBconnection().prepareStatement(loginQuery1);
			
			
					ps.setString(1, user.getUserName());
					ps.setString(2, user.getPassword());
			
					ResultSet resultSet = ps.executeQuery(); 
					
					if(resultSet.next()) {
						
						uID = resultSet.getString(1);
						
						user.setUserID(uID);
						
						
						user.setType(resultSet.getString("type"));
						
							
						user.setValid(true);
							
						
						
					}
					
					else {
						user.setValid(false);
					}
				
			
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return user;

	}
	
	
	// retrieve user from DB
	public ArrayList<User> getUser(String userID){
		
		ArrayList<User> userList = new ArrayList<User>();
		
		String getUserQuery = "SELECT * FROM user WHERE userID = ?";
		
		try {
			
			PreparedStatement ps = DBConnection.getDBconnection().prepareStatement(getUserQuery);
			
			ps.setString(1, userID);
			
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				
				User user = new User();
				user.setUserID(resultSet.getString(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setGender(resultSet.getString(4));
				user.setCountry(resultSet.getString(5));
				user.setUserName(resultSet.getString(6));
				user.setEmail(resultSet.getString(7));
				user.setMobileNumber(resultSet.getInt(8));
				user.setType(resultSet.getString(9));
				userList.add(user);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}

	// get user IDs of users who are already registered
	public ArrayList<String> getUserIDs(){
	
		ArrayList<String> arrayList = new ArrayList<String>(); 
		
		String findIDQuery = "SELECT userID FROM user";
		
		PreparedStatement ps;
		try {
			ps = DBConnection.getDBconnection().prepareStatement(findIDQuery);
			
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(1));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	
	public void removeUser(User user) {
		
		String removeUserQuery = "DELETE FROM user WHERE userID = ? and firstName = ?";	
		
		try {
			
			PreparedStatement ps = DBConnection.getDBconnection().prepareStatement(removeUserQuery);
			
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getFirstName());
			
			ps.executeUpdate();
					
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}


	@Override
	public String getpassword(String userID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatepassword(String userID, String password) {
		// TODO Auto-generated method stub
		
	}







	

}
