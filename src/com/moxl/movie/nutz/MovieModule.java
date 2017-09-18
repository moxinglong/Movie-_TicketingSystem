package com.moxl.movie.nutz;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.moxl.movie.pojo.Movie;
import com.moxl.movie.utils.DBUtil;

@IocBean
@At("/movie")
@Filters({@By(type=LoginFilter.class)})
public class MovieModule extends BaseSrv{
	@Inject
    protected Dao dao;
	
	 //添加电影
	  @At
	  @Ok("beetl:WEB-INF/web/movieadd.html")
	  public void goAdd(HttpSession session,HttpServletRequest req){
	  }
	  
	//查询电影是否已存在
      @At
	  @Ok("raw")
      public String queryByName(@Param("name")String name,HttpSession session,HttpServletRequest req){
    	  Movie movie = dao.fetch(Movie.class, name);    	  
	      if (movie == null ) {	          
	          return "true";
	      }	      
	      return movie.getName();      
      }
      
      //查询放映时间
      @At
	  @Ok("raw")
      public String queryTime(@Param("name")String name,HttpSession session,HttpServletRequest req){
    	  Movie movie = dao.fetch(Movie.class, name);    	  
	      if (movie == null ) {	          
	          return "true";
	      }	      
	      return movie.getTime();      
      }
	  
      //根据名字查询电影
      public static List queryMovieByName(String mname){
  		List list = new ArrayList();
  		Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
		try {			
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
		  		rs = stmt.executeQuery("SELECT * from movie where name='"+mname+"'");
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
      
      //查询所有电影
      public static List QueryAllMovie(){
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
      
      //按照类型查询
      public static List queryByType(String type){
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
      
	   @At
	   @Ok("raw")
	   public String saveAdd(@Param("..")Movie movie,HttpSession session,HttpServletRequest req){
	      try{
	        dao.insert(movie);
	      }catch(Exception e){
	        e.printStackTrace();
	        return "添加失败";
	      }    
	      return "true";
	   }
	   
	   //删除指定id的单条电影
	   @At
	   @Ok("raw")
	   public String doDel1(@Param("name")String name,HttpSession session,HttpServletRequest req){
	      if(dao.delete(Movie.class,name)>0)return "true";
	      return "false";
	   }
	   @At
	   @Ok("raw")//删除指定id的多条电影
	   public String doDelN(@Param("names")String names,HttpSession session,HttpServletRequest req){
	      String[] s = names.split(",");
	      if(dao.clear(Movie.class, Cnd.where("id", "in", s))>0)return "true";
	      return "false";
	   }
	   
	   //改
	   @At
	   @Ok("beetl:WEB-INF/web/movieedit.html")
	   public void goEdit(@Param("name")String name,HttpSession session,HttpServletRequest req){
	      Movie movie=dao.fetch(Movie.class,name);
	      req.setAttribute("movie", movie);
	   }
	   
	   @At
	   @Ok("raw")
	   public String saveEdit(@Param("..")Movie movie,HttpSession session,HttpServletRequest req){
	      try{

	        if(dao.updateIgnoreNull(movie)==1)return "true";
	        else return "修改失败";
	      }catch(Exception e){
	        e.printStackTrace();
	        return  "修改失败";
	      }
	   }

	  @At
	  @Ok("beetl:WEB-INF/web/movielist.html")//跳转到新闻信息列表
	  public void goList(@Param("page") int curPage, @Param("rows") int pageSize,HttpSession session,HttpServletRequest req){
	  
	  }

	    @At
	    @Ok("raw")//分页查询指定新闻或所有新闻
	    public String listMovie(@Param("page") int curPage, @Param("rows") int pageSize,
	                       @Param("s_name") String s_name, HttpSession session) {
	        Criteria cri = Cnd.cri(); 
	        if (!Strings.isBlank(s_name)) {
	            cri.where().andLike("name", s_name);
	        }
	        else cri.where().andEquals("1", 1);
	        cri.getOrderBy().desc("name");
	        return listPageJson(dao, Movie.class, curPage,pageSize, cri);
	    }
}
