package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.UserVo;

public class UserDao {
	private Connection connection;
	
	public UserDao(String driverClass, String url, String username, String password) {		
		//Driver Class Loading
		try {
			Class.forName(driverClass);
			
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectionCLose() {
		try {
			if(connection != null) connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public UserVo getUser(String userId) {
		PreparedStatement pStmt = null;
		UserVo userVo = null;
		
		String sql = "select * from users where userid = ?";
		try {
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, userId);
			
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				userVo = new UserVo(
						rs.getInt("id"), 
						rs.getString("userId"), 
						rs.getString("name"), 
						rs.getString("gender"), 
						rs.getString("city"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(pStmt != null) pStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return userVo;
	}
	
	public List<UserVo> getUserList() {
		PreparedStatement pStmt = null;
		List<UserVo> userList = new ArrayList<>();
		
		String sql = "select * from users order by id";
		try {
			pStmt = connection.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				UserVo userVo = new UserVo(
						rs.getInt("id"), 
						rs.getString("userId"), 
						rs.getString("name"), 
						rs.getString("gender"), 
						rs.getString("city"));
				userList.add(userVo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null) pStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}
	
	
	
}
