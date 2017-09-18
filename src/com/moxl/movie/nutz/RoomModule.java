package com.moxl.movie.nutz;

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

import com.moxl.movie.pojo.Room;

@IocBean
@At("/room")
@Filters({@By(type=LoginFilter.class)})
public class RoomModule {

	@Inject
    protected Dao dao;
	
	//添加放映室
	@At
	@Ok("beetl:WEB-INF/web/roomadd.html")
	public void goAdd(HttpSession session,HttpServletRequest req){
	}
	  
	//查询放映室是否已存在
    @At
	@Ok("raw")
    public String queryByName(@Param("name")String name,HttpSession session,HttpServletRequest req){
  	  Room room = dao.fetch(Room.class, name);    	  
	      if (room == null ) {	          
	          return "true";
	      }	      
	      return room.getName();      
    }
    
    //查询放映室座位数
    @At
	@Ok("raw")
    public String querySeatNum(@Param("name")String name,HttpSession session,HttpServletRequest req){
  	  Room room = dao.fetch(Room.class, name);    	  
	      if (room == null ) {	          
	          return "true";
	      }	      
	      return room.getSeatnum();      
    }
	
    @At
   @Ok("raw")
   public String saveAdd(@Param("..")Room room,HttpSession session,HttpServletRequest req){
      try{
        dao.insert(room);
      }catch(Exception e){
        e.printStackTrace();
        return "添加失败";
      }    
      return "true";
   }
   
   //删除指定id的单条放映室
   @At
   @Ok("raw")
   public String doDel1(@Param("name")String name,HttpSession session,HttpServletRequest req){
      if(dao.delete(Room.class,name)>0)return "true";
      return "false";
   }
   
   //删除指定id的多条放映室
   @At
   @Ok("raw")
   public String doDelN(@Param("names")String names,HttpSession session,HttpServletRequest req){
      String[] s = names.split(",");
      if(dao.clear(Room.class, Cnd.where("name", "in", s))>0)return "true";
      return "false";
   }
   
   //改
   @At
   @Ok("beetl:WEB-INF/web/roomedit.html")
   public void goEdit(@Param("name")String name,HttpSession session,HttpServletRequest req){
      Room room=dao.fetch(Room.class,name);
      req.setAttribute("room", room);
   }
   
   @At
   @Ok("raw")
   public String saveEdit(@Param("..")Room room,HttpSession session,HttpServletRequest req){
      try{

        if(dao.updateIgnoreNull(room)==1)return "true";
        else return "修改失败";
      }catch(Exception e){
        e.printStackTrace();
        return  "修改失败";
      }
   }

	
	@At
	@Ok("beetl:WEB-INF/web/roomlist.html")//跳转到放映室列表
	public void goList(@Param("page") int curPage, @Param("rows") int pageSize,HttpSession session,HttpServletRequest req){
		
	}

    @At
    @Ok("raw")//分页查询指定放映室
    public String listRoom(@Param("page") int curPage, @Param("rows") int pageSize,
                       @Param("s_name") String s_name, HttpSession session) {
        Criteria cri = Cnd.cri(); 
        if (!Strings.isBlank(s_name)) {
            cri.where().andLike("name", s_name);
        }
        else cri.where().andEquals("1", 1);
        cri.getOrderBy().asc("name");
        return BaseSrv.listPageJson(dao, Room.class, curPage,pageSize, cri);
    }
}
