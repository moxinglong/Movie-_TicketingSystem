package com.moxl.movie.nutz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.moxl.movie.pojo.Myorder;
import com.moxl.movie.utils.DBUtil;

@IocBean
@At("/order")
@Filters({@By(type=LoginFilter.class)})
public class OrderModule {
	
	@Inject
    protected Dao dao;

	 //查询所有的属于某个用户的所有订单
	 @At
	 @Ok("raw")
	 public static List queryOrderByUemail(String email){ 
    	List list = new ArrayList();
    	Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
		try {
				conn = DBUtil.GetConnection();
				stmt = conn.createStatement();
		  		rs = stmt.executeQuery("SELECT * from myorder where uemail='"+email+"'");
		  		while (rs.next()) {
		  			String id    = rs.getString("id");
		  			String uemail = rs.getString("uemail");
		  			String rname = rs.getString("rname");
		  			String seat = rs.getString("seat");
		  			String mname = rs.getString("mname");
		  			String mtime = rs.getString("mtime");
		  			String allprice = rs.getString("allprice");
		  			Timestamp starttime = rs.getTimestamp("starttime");
		  			Timestamp endtime   = rs.getTimestamp("endtime");
		  			Timestamp otime   = rs.getTimestamp("otime");
		  			
		  			Myorder order = new Myorder(id, uemail, rname, seat, mname, mtime, allprice,starttime,endtime, otime);
		  			list.add(order);
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
	
	//保存订单
	@At
	@Ok("raw")
    public String saveAdd(@Param("uemail") String uemail,
    					@Param("rname") String rname,
    					@Param("seat") String[] seat,
    					@Param("mname") String mname,
    					@Param("mtime") String mtime,
    					@Param("allprice") String allprice,
    					@Param("starttime") String starttime,
    					@Param("endtime") String endtime,
    					@Param("sno") String[] sno,
    					@Param("num") String num,
    					HttpSession session,HttpServletRequest req){

		Connection conn = null;
  		Statement stmt = null;
  		int rs = 0;
  		StringBuffer sb = new StringBuffer();
  		//生成系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());
		
		try {
				conn = DBUtil.GetConnection();
				//获取座位表
	
				for(int i = 0;i < seat.length; i++){
					sb.append(seat[i]);
					sb.append(" ");
				}
				
				//将放映表的剩余座位数更新
				String sql = "update play set seatnum=seatnum-"+num+" where rname='"+rname+"' and mname='"+mname+"' and starttime='"+starttime+"' and endtime='"+endtime+"'";
				stmt = conn.createStatement();
				rs = stmt.executeUpdate(sql);
						
				//插入座位号
				for(int i = 0;i < sno.length; i++){
					String sqb = "insert into seat values('"+rname+"','"+sno[i]+"','"+starttime+"','"+endtime+"')";
					rs = stmt.executeUpdate(sqb);
				}	
				
				//生成订单
				String sqg = "insert into myorder(uemail,rname,seat,mname,mtime,allprice,starttime,endtime,otime) values('"+uemail+"','"+rname+"','"+sb.toString()+"','"+mname+"','"+mtime+"','"+allprice+"','"+starttime+"','"+endtime+"','"+date+"')";
				rs = stmt.executeUpdate(sqg);
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
		
        return "/jsp/shopping.jsp";
    }
	
	//删除指定id的单条订单
    @At
    @Ok("raw")
    public String doDel1(@Param("id")String id,HttpSession session,HttpServletRequest req){
      Myorder order = dao.fetch(Myorder.class, id);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Connection conn = null;
	  Statement stmt = null;
	  int rs = 0,as = 0;
		try {
				  conn = DBUtil.GetConnection();
				  stmt = conn.createStatement();
			      String[] str = order.getSeat().split(" ");
			      for(int i = 0;i < str.length; i++){
						
						String[] scr = str[i].split("排");
						//多少排
						int p = Integer.parseInt(scr[0].toString());
						
						String[] sdr = scr[1].split("座");
						//多少座
						int z = Integer.parseInt(sdr[0].toString());
						
						int seat = (p-1)*10 + z;
						
						String sql = "DELETE FROM seat WHERE rname = '"+order.getRname()+"' and sid = '"+seat+"' and starttime = '"+sdf.format(order.getStarttime())+"' and endtime = '"+sdf.format(order.getEndtime())+"'";
						
						String add = "UPDATE play SET seatnum = (seatnum+1) where rname='"+order.getRname()+"' and mname='"+order.getMname()+"' and starttime='"+sdf.format(order.getStarttime())+"' and endtime='"+sdf.format(order.getEndtime())+"'";
						
						rs = stmt.executeUpdate(sql);
						
						as = stmt.executeUpdate(add);
						
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
		
      if(dao.delete(Myorder.class,id)>0)return "true";
      return "false";
    }
	
	//请求打开后台管理的订单信息列表
    @At
    @Ok("beetl:WEB-INF/web/orderlist.html")
    public void goList(HttpSession session,HttpServletRequest req){

    }

    //分页查询订单
  	@At
  	@Ok("raw")
  	public String listOrder(@Param("page") int curPage, @Param("rows") int pageSize,@Param("s_name") String s_name, HttpSession session) {	
  	Criteria cri = Cnd.cri(); 	
  	if (!Strings.isBlank(s_name)) {	
  		cri.where().andLike("uemail", s_name).orLike("mname", s_name);	
  	}else
  		cri.where().andEquals("1", 1);	
  	cri.getOrderBy().asc("otime");	
  	return BaseSrv.listPageJson(dao, Myorder.class, curPage,pageSize, cri);	
  	} 
}
