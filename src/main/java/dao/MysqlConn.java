package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class MysqlConn {
    final String DBDRIVER = "com.mysql.jdbc.Driver";   
    final String DBURL = "jdbc:mysql://192.168.43.96:3306/riskData?characterEncoding=utf8&useSSL=true"; 
    final String DBUSER = "root";  
    final String DBPWD = "sonar";  
    java.sql.Connection conn = null;//数据库连接  
  
    public MysqlConn() {  
        try {  
            Class.forName(DBDRIVER);// load the Connecting class  
            try {
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}             
        }catch (ClassNotFoundException e){
            System.out.println("connection is failed");
            e.printStackTrace();  
        }  
    }  
    //another version to initialize with new parameters but not defautl parameters  
    public MysqlConn(String dbUrl, String dbUser, String dbPwd) {  
        try {  
            Class.forName(DBDRIVER);  
            try {
				conn = DriverManager.getConnection(dbUrl, dbUrl, dbPwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }catch (ClassNotFoundException  e) {  
        	System.out.println("connection is failed");
            e.printStackTrace();  
        }  
    }  
      
    public java.sql.Connection getConn() {  
        return conn;  
    }  
    public void setConn(Connection conn) {  
        this.conn = conn;  
    }  
    public void closeConn(){  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
