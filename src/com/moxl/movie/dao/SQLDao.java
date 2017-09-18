package com.moxl.movie.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import com.moxl.movie.pojo.Movie;
import com.moxl.movie.pojo.User;
import com.moxl.movie.utils.DBUtil;



public class SQLDao {

	//执行更新语句
	public int ExecuteUpdate(String sql){
		Connection conn = null;
  		Statement stmt = null;
  		int rs = 0;
		try {
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
				rs = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		      if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 		
			      if(conn!= null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
		
		return rs;
	}
	
	// 查询所有电影
	public List QueryAllMovie(){
		List list = new ArrayList();
		Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
		try {
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * from movie");
				while (rs.next()) {
					String name = rs.getString("name");
					String time = rs.getString("time");
					String typ = rs.getString("typ");
					String degr = rs.getString("degr");
					String pic = rs.getString("pic");
					String des = rs.getString("des");
					String grad = rs.getString("grad");
					String pric = rs.getString("pric");
					Movie m = new Movie(name, time, typ, degr, pic, des, grad, pric);
					list.add(m);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		      if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 		
			      if(conn!= null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}		

		return list;
	}
	
	public  List queryByType(String type){
		List types = new ArrayList();
		Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
  		String sql = "SELECT * FROM movie WHERE typ LIKE '%"+type+"%';";
		try {
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String name = rs.getString("name");
					String time = rs.getString("time");
					String typ = rs.getString("typ");
					String degr = rs.getString("degr");
					String pic = rs.getString("pic");
					String des = rs.getString("des");
					String grad = rs.getString("grad");
					String pric = rs.getString("pric");
					Movie m = new Movie(name, time, typ, degr, pic, des, grad, pric);
					types.add(m);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		      if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 		
			      if(conn!= null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}
					
		return types;
	}
	
	public  boolean queryByName(String email){
		Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
  		String sql = "select * from user where email='"+email+"'";
		try {
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					return true;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		      if(stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 		
			      if(conn!= null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}

		return false;
	}

}
