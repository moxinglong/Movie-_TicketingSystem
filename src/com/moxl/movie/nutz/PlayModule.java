package com.moxl.movie.nutz;

import java.sql.Connection;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.moxl.movie.pojo.Movie;
import com.moxl.movie.pojo.Play;
import com.moxl.movie.pojo.Seat;
import com.moxl.movie.utils.DBUtil;

@IocBean
@At("/play")
@Filters({@By(type=LoginFilter.class)})
public class PlayModule {
	
	@Inject
    protected Dao dao;
	
	//请求跳转到新增放映的页面
    @At
    @Ok("beetl:WEB-INF/web/playadd.html")
    public void goAdd(HttpSession session,HttpServletRequest req){

    }
	
    @At
    @Ok("raw")
    public String saveAdd(@Param("..")Play play,HttpSession session,HttpServletRequest req){
       try{
         dao.insert(play);
       }catch(Exception e){
         e.printStackTrace();
         return "添加失败";
       }    
       return "true";
    }
    
	//请求打开后台管理的放映信息列表
    @At
    @Ok("beetl:WEB-INF/web/playlist.html")
    public void goList(HttpSession session,HttpServletRequest req){

    }
	
    //查询所有放映室
    @At
    @Ok("raw")
    public String getRoom(HttpServletRequest req, HttpSession session){
      List<String> array=new ArrayList<String>();
      Sql sql = Sqls.create("select name from room");
      sql.setCallback(new SqlCallback(){
        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
             List<String> array0=new ArrayList<String>();
            while (rs.next()){
                   array0.add(rs.getString("name"));
            }
            return array0;
        }
    });

    dao.execute(sql);

    array=sql.getList(String.class);

    return BaseSrv.strtree2(array,req,session);

    }
    
  //查询所有电影
    @At
    @Ok("raw")
    public String getMovie(HttpServletRequest req, HttpSession session){
      List<String> array=new ArrayList<String>();
      Sql sql = Sqls.create("select name from movie");
      sql.setCallback(new SqlCallback(){
        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
             List<String> array0=new ArrayList<String>();
            while (rs.next()){
                   array0.add(rs.getString("name"));
            }
            return array0;
        }
    });

    dao.execute(sql);

    array=sql.getList(String.class);

    return BaseSrv.strtree2(array,req,session);

    }
    
    //查询时间段是否已被占用
    @At
    @Ok("raw")
    public String queryTime(@Param("rname")String rname,@Param("starttime")String starttime,@Param("endtime")String endtime,HttpSession session,HttpServletRequest req){     
	  
		  String sql = "select * from play where (rname='"+rname+"') and ('"+starttime+"' between starttime and endtime or '"+endtime+"' between starttime and endtime)";
		  Connection conn = null;
	  	  Statement stmt = null;
	  	  ResultSet rs = null;
		  try {
				  conn = DBUtil.GetConnection();
				  stmt = conn.createStatement();
		  		  rs = stmt.executeQuery(sql);
		  		  while (rs.next()) {
		 	  		return "false";
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
	  	   	  
		  return"true";
    }
    
    //查询电影是否存在(play表)
    @At
    @Ok("raw")
    public static boolean queryMovieByName(String name){
    	Connection conn = null;
	  	Statement stmt = null;
	  	ResultSet rs = null;
  		try {
  				conn = DBUtil.GetConnection();
  				stmt = conn.createStatement();
  		  		rs = stmt.executeQuery("SELECT * from play where mname='"+name+"'");
  		  		while (rs.next()) {
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
    
    //查询电影信息(play表)
    @At
    @Ok("raw")
    public static List queryTimeByName(String name){     
    	List list = new ArrayList();
    	Connection conn = null;
	  	Statement stmt = null;
	  	ResultSet rs = null;
  		try {
  				conn = DBUtil.GetConnection();
  				stmt = conn.createStatement();
  		  		rs = stmt.executeQuery("SELECT * from play where mname='"+name+"'");
  		  		while (rs.next()) {
  		  			String id    = rs.getString("id");
  		  			String rname = rs.getString("rname");
  		  			String mname = rs.getString("mname");
  		  			String mtime = rs.getString("mtime");
  		  			String seatnum = rs.getString("seatnum");
  		  			Timestamp starttime = rs.getTimestamp("starttime");
  		  			Timestamp endtime   = rs.getTimestamp("endtime");
  		  			int cz = rs.getInt("cz");
  		  			
  		  			Play p = new Play(id, rname, mname, mtime, seatnum,starttime,endtime, cz);
  		  			list.add(p);
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
    
    //查询电影信息(play表)
    @At
    @Ok("raw")
    public static List queryPlayByMovieNameTime(String name,String s_time,String e_time){ 
    	List list = new ArrayList();
    	Connection conn = null;
	  	Statement stmt = null;
	  	ResultSet rs = null;
  		try {
  				conn = DBUtil.GetConnection();
  				stmt = conn.createStatement();
  		  		rs = stmt.executeQuery("SELECT * from play where mname='"+name+"' and starttime='"+s_time+"' and endtime='"+e_time+"'");
  		  		while (rs.next()) {
  		  			String id    = rs.getString("id");
  		  			String rname = rs.getString("rname");
  		  			String mname = rs.getString("mname");
  		  			String mtime = rs.getString("mtime");
  		  			String seatnum = rs.getString("seatnum");
  		  			Timestamp starttime = rs.getTimestamp("starttime");
  		  			Timestamp endtime   = rs.getTimestamp("endtime");
  		  			int cz = rs.getInt("cz");
  		  			
  		  			Play p = new Play(id, rname, mname, mtime, seatnum,starttime,endtime, cz);
  		  			list.add(p);
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
    
    //查询已被预定的座位(seat表)
    @At
    @Ok("raw")
    public static List querySeatByNameTime(String name,String s_time,String e_time){     
    	List list = new ArrayList();
    	Connection conn = null;
	  	Statement stmt = null;
	  	ResultSet rs = null;
  		try {
  				conn = DBUtil.GetConnection();
  				stmt = conn.createStatement();
  		  		rs = stmt.executeQuery("SELECT * from seat where rname='"+name+"' and starttime='"+s_time+"' and endtime='"+e_time+"'");
  		  		while (rs.next()) {
  		  			String rname = rs.getString("rname");
  		  			String sid = rs.getString("sid");
  		  			Timestamp starttime = rs.getTimestamp("starttime");
  		  			Timestamp endtime   = rs.getTimestamp("endtime");
  		  			
  		  			Seat s = new Seat(rname, sid, starttime,endtime);
  		  			list.add(s);
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
    
    //删除指定id的单条放映
    @At
    @Ok("raw")
    public String doDel1(@Param("id")String id,HttpSession session,HttpServletRequest req){
      if(dao.delete(Play.class,id)>0)return "true";
      return "false";
    }
    
	//分页查询放映
	@At
	@Ok("raw")
	public String listPlay(@Param("page") int curPage, @Param("rows") int pageSize,@Param("s_name") String s_name, HttpSession session) {	
	Criteria cri = Cnd.cri(); 	
	if (!Strings.isBlank(s_name)) {	
		cri.where().andLike("rname", s_name).orLike("mname", s_name);	
	}else
		cri.where().andEquals("1", 1);	
	cri.getOrderBy().asc("starttime");	
	return BaseSrv.listPageJson(dao, Play.class, curPage,pageSize, cri);	
	} 

}
