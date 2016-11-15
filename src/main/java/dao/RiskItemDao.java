package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.RiskItem;
import vo.Trigger;


public class RiskItemDao {
	public ArrayList<RiskItem> list(ArrayList<RiskItem> riskItemList) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from riskItem where number=1";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   RiskItem riskItem=new RiskItem();
                   riskItem.setId(rs.getString("id"));
                   riskItem.setRiskname(rs.getString("riskname"));
                   riskItem.setType(rs.getString("type"));
                   riskItem.setPossibility(rs.getString("possibility"));
                   riskItem.setInfluence(rs.getString("influence"));
                   riskItem.setStatus(rs.getString("status"));
                   riskItem.setCommit(rs.getString("commit"));
                   riskItem.setFollow(rs.getString("follow"));
                   riskItem.setSolution(rs.getString("solution"));
                   riskItem.setNumber(Integer.parseInt(rs.getString("number"))); 
                   riskItemList.add(riskItem);
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return riskItemList;

	}
	
	public ArrayList<RiskItem> find(ArrayList<RiskItem> riskItemList,String riskname) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from riskItem where riskname=?";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			pstmt.setString(1, riskname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   RiskItem riskItem=new RiskItem();
                   riskItem.setId(rs.getString("id"));
                   riskItem.setRiskname(rs.getString("riskname"));
                   riskItem.setType(rs.getString("type"));
                   riskItem.setPossibility(rs.getString("possibility"));
                   riskItem.setInfluence(rs.getString("influence"));
                   riskItem.setStatus(rs.getString("status"));
                   riskItem.setCommit(rs.getString("commit"));
                   riskItem.setFollow(rs.getString("follow"));
                   riskItem.setSolution(rs.getString("solution"));
                   riskItem.setNumber(Integer.parseInt(rs.getString("number"))); 
                   riskItemList.add(riskItem);
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return riskItemList;

	}
	
	public void add(RiskItem riskItem) {
		riskItem=this.getCorrectStatus(riskItem);
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "insert into riskItem values(?,?,?,?,?,?,?,?,?,?)";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句    
		try {
			pstmt = conn.prepareStatement(sql);//告诉连接，准备使用这个语句  
            pstmt.setString(1, riskItem.getId());//往这个语句的第一个问好填入参数 
            pstmt.setString(2, riskItem.getRiskname());
            pstmt.setString(3, riskItem.getType());
            pstmt.setString(4, riskItem.getPossibility());
            pstmt.setString(5, riskItem.getInfluence());
            pstmt.setString(6, riskItem.getStatus());
            pstmt.setString(7, riskItem.getCommit());
            pstmt.setString(8, riskItem.getFollow());
            pstmt.setString(9, riskItem.getSolution());
            pstmt.setInt(10, riskItem.getNumber());
            pstmt.executeUpdate();//执行查询。如果是执行更新应该用executeUpdate  
			db.closeConn();
        }catch (SQLException e){
            e.printStackTrace();
        }		
	}
	
	public void update(RiskItem riskItem){
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql1 ="update riskItem set id=200+id where riskname=?" ;// 预设查询语句
		String sql2 ="update riskItem set number=1+number where riskname=?" ;
		java.sql.PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql1);//告诉连接，准备使用这个语句  
            pstmt.setString(1, riskItem.getRiskname());//往这个语句的第一个问好填入参数            
            pstmt.executeUpdate();//执行查询。如果是执行更新应该用executeUpdate 
            pstmt = conn.prepareStatement(sql2);//告诉连接，准备使用这个语句  
            pstmt.setString(1, riskItem.getRiskname());//往这个语句的第一个问好填入参数            
            pstmt.executeUpdate();//执行查询。如果是执行更新应该用executeUpdate 
			db.closeConn();
        }catch (SQLException e){
            e.printStackTrace();
        }	
		
		this.add(riskItem);
		
	}
	
	public ArrayList<RiskItem> listUndo(ArrayList<RiskItem> riskItemList) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from riskItem where status=? and number=1";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			pstmt.setString(1, "问题");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   RiskItem riskItem=new RiskItem();
                   riskItem.setId(rs.getString("id"));
                   riskItem.setRiskname(rs.getString("riskname"));
                   riskItem.setType(rs.getString("type"));
                   riskItem.setPossibility(rs.getString("possibility"));
                   riskItem.setInfluence(rs.getString("influence"));
                   riskItem.setStatus(rs.getString("status"));
                   riskItem.setCommit(rs.getString("commit"));
                   riskItem.setFollow(rs.getString("follow"));
                   riskItem.setSolution(rs.getString("solution"));
                   riskItem.setNumber(Integer.parseInt(rs.getString("number"))); 
                   riskItemList.add(riskItem);
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return riskItemList;

	}
	
	public ArrayList<RiskItem> listDone(ArrayList<RiskItem> riskItemList) {
		MysqlConn db = new MysqlConn();// 实例化数据库连接类
		java.sql.Connection conn = db.getConn();// 获取链接
		String sql = "select * from riskItem where status=? ";// 预设查询语句
		java.sql.PreparedStatement pstmt = null;// 预查询语句
		try {
			pstmt = conn.prepareStatement(sql);// 告诉连接，准备使用这个语句
			pstmt.setString(1, "已解决问题");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 典型ResultSet处理方法
                   RiskItem riskItem=new RiskItem();
                   riskItem.setId(rs.getString("id"));
                   riskItem.setRiskname(rs.getString("riskname"));
                   riskItem.setType(rs.getString("type"));
                   riskItem.setPossibility(rs.getString("possibility"));
                   riskItem.setInfluence(rs.getString("influence"));
                   riskItem.setStatus(rs.getString("status"));
                   riskItem.setCommit(rs.getString("commit"));
                   riskItem.setFollow(rs.getString("follow"));
                   riskItem.setSolution(rs.getString("solution"));
                   riskItem.setNumber(Integer.parseInt(rs.getString("number"))); 
                   riskItemList.add(riskItem);
			}
			db.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return riskItemList;

	}
	
	public RiskItem getCorrectStatus(RiskItem riskItem){
		TriggerDao triggerDao=new TriggerDao();
		Trigger trigger=triggerDao.find(riskItem.getType());
		
		if(trigger.getPossibility().equals("")||trigger.getInfluence().equals("")){
			return riskItem;
		}
		if(trigger.getPossibility().equals(riskItem.getPossibility())){
			riskItem.setStatus("问题");
			return riskItem;
		}
		if(trigger.getInfluence().equals(riskItem.getInfluence())){
			riskItem.setStatus("问题");
			return riskItem;
		}
		if(trigger.getPossibility().equals("低")){
			riskItem.setStatus("问题");
			return riskItem;
		}
		if(trigger.getInfluence().equals("低")){
			riskItem.setStatus("问题");
			return riskItem;
		}
		if(riskItem.getPossibility().equals("高")){
			riskItem.setStatus("问题");
			return riskItem;
		}
		if(riskItem.getInfluence().equals("高")){
			riskItem.setStatus("问题");
			return riskItem;
		}
		return riskItem;
	}
	
	
}
