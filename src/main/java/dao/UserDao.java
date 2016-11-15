package dao;

import java.sql.*;
import java.util.ArrayList;


import vo.User;

public class UserDao {
	public void add(User user) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "insert into user values(?,?,?,?)";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句    
		try {
			pstmt = conn.prepareStatement(sql);//告诉连接，准备使用这个语句  
            pstmt.setString(1, user.getId());//往这个语句的第一个问好填入参数 
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getOccupation());
            pstmt.executeUpdate();//执行查询。如果是执行更新应该用executeUpdate  
			db.closeConn();
        }catch (SQLException e){
            e.printStackTrace();
        }
		

	}

	public void delete(String id) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "delete from user where id="+id+";";// 预设查询语句		
		try {
			Statement stmt = conn .createStatement();
			stmt.executeUpdate(sql);
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<User> list(ArrayList<User> userList) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from user ";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   User user=new User();
                   user.setId(rs.getString("id"));
                   user.setUsername(rs.getString("username"));
                   user.setPassword(rs.getString("password"));
                   user.setOccupation(rs.getString("occupation"));
                   userList.add(user);
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;

	}

	public boolean checkUser(User user) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from user where username=?";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		String password = "";
		String occupation = "";
          
		try {
			pstmt = conn.prepareStatement(sql);//告诉连接，准备使用这个语句  
            pstmt.setString(1, user.getUsername());//往这个语句的第一个问好填入参数  
            ResultSet rs = pstmt.executeQuery();//执行查询。如果是执行更新应该用executeUpdate  
            while(rs.next()) {//典型ResultSet处理方法  
				password = rs.getString("password");// 此时rs指向的是结果集中的一行，取列名为password的值
				occupation = rs.getString("occupation");
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
              System.out.println(password+","+occupation);
		if (password != null && password.equals(user.getPassword())) {
			if (occupation != null && occupation.equals(user.getOccupation())) {
				return true;
			}
		}
		return false;

	}

}
