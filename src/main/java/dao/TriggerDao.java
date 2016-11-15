package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.Trigger;


public class TriggerDao {
	public ArrayList<Trigger> list(ArrayList<Trigger> triggerList) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from trigger1 ";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   Trigger trigger=new Trigger();
                   trigger.setId(rs.getString("id"));
                   trigger.setType(rs.getString("type"));
                   trigger.setPossibility(rs.getString("possibility"));
                   trigger.setInfluence(rs.getString("influence"));                 
                   triggerList.add(trigger);
         
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return triggerList;

	}
	public void add(Trigger trigger) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "insert into  trigger1 values(?,?,?,?)";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句    
		try {
			pstmt = conn.prepareStatement(sql);//告诉连接，准备使用这个语句  
            pstmt.setString(1, trigger.getId());//往这个语句的第一个问好填入参数 
            pstmt.setString(2, trigger.getType());
            pstmt.setString(3, trigger.getPossibility());
            pstmt.setString(4, trigger.getInfluence());
            pstmt.executeUpdate();//执行查询。如果是执行更新应该用executeUpdate  
			db.closeConn();
        }catch (SQLException e){
            e.printStackTrace();
        }
		

	}

	public void delete(String id) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "delete from trigger1 where id="+id+";";// 预设查询语句		
		try {
			Statement stmt = conn .createStatement();
			stmt.executeUpdate(sql);
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Trigger find(String type) {
		Trigger trigger=new Trigger();
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from trigger1 where type=? ";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			if(rs==null){
				return trigger;
			}
			while (rs.next()) {// 典型ResultSet处理方法
                   
                   trigger.setId(rs.getString("id"));
                   trigger.setType(rs.getString("type"));
                   trigger.setPossibility(rs.getString("possibility"));
                   trigger.setInfluence(rs.getString("influence"));                 
                  
         
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trigger;

	}

}
