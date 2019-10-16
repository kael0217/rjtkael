package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.java.dto.User;

public class UserDAO {
	//create a new User Object
	public static User CreateUser(String firstname, String lastname, String password, String gender, String skillSet, String city) {
		User newUser = new User(firstname, lastname, password, gender, skillSet, city);
		return newUser;
	}
	
	public static boolean RegisterUser(User user) {		
		if (user != null && user.getFirstname()!= null && user.getLastname()!= null 
				&& user.getPassword()!= null && user.getGender()!= null && user.getSkillSet()!=null && user.getCity()!=null) {
			Connection conn1 = null;
			boolean checked=false;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn1 = DriverManager.getConnection("jdbc:oracle:thin:@kaelPC.local:1521:kaeldb","SYSTEM","Manager");
				PreparedStatement pStatement = conn1.prepareStatement("insert into registeredusers values(?,?,?,?,?,?)");
				pStatement.setString(1, user.getFirstname());
				pStatement.setString(2, user.getLastname());
				pStatement.setString(3, user.getPassword());
				pStatement.setString(4, user.getGender());
				pStatement.setString(5, user.getSkillSet());
				pStatement.setString(6, user.getCity());
				pStatement.execute();
				pStatement.close();
				checked = true;
			} catch (SQLException e) {
				if(!checked)
					System.out.println("Insert record fail!");
				e.printStackTrace();
				return false;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			conn1.close()
			return true;
		}else {
			return false;
		}
	}
	
}