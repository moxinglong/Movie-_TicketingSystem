package com.moxl.movie.nutz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.moxl.movie.pojo.Cmenu;
import com.moxl.movie.pojo.EasyUITree;
import com.moxl.movie.pojo.Loginlog;
import com.moxl.movie.pojo.User;
import com.moxl.movie.utils.DBUtil;

@IocBean
@At("/user")
@Filters({@By(type=LoginFilter.class)})
public class UserModule extends BaseSrv{   //实现对用户信息的增删改查
      @Inject
      protected Dao dao;
      
   //用户添加
      @At
      @Ok("beetl:WEB-INF/web/useradd.html")
      public void goAdd(HttpSession session,HttpServletRequest req){
      }
      
      //查询用户是否已存在
      @At
	  @Ok("raw")
      public String queryByEmail(@Param("email")String email,HttpSession session,HttpServletRequest req){

    	  User user = dao.fetch(User.class, email);    	  
	      if (user == null ) {	          
	          return "true";
	      }	      
	      return user.getEmail();      
      }
      
    //执行更新语句
  	public static int ExecuteUpdate(String sql){
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
      
      /* isUserNameExist */
      public static boolean queryByName(String email){
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
      
      @At
      @Ok("raw")
      public String saveAdd(@Param("..")User user,HttpSession session,HttpServletRequest req){
            try{
                  String pwd=user.getPwd();
                  pwd=lrwCode(pwd,"");
                  user.setPwd(pwd);
                  dao.insert(user);
            }catch(Exception e){
                  e.printStackTrace();
                  return "添加失败";
            }          
            return "true";
      }
      
    //用户删除
      @At
      @Ok("raw")//删除指定email的单个用户
      public String doDel1(@Param("email")String email,HttpSession session,HttpServletRequest req){
            User user=(User)(session.getAttribute("me"));
            if(user.getEmail().equals(email))return "false";
            if(dao.delete(User.class,email)>0)return "true";
            return "false";
      }
      @At
      @Ok("raw")//删除指定email的多个用户
      public String doDelN(@Param("emails")String emails,HttpSession session,HttpServletRequest req){
            //User user=(User)(session.getAttribute("me"));
            String[] s=emails.split(",");
            if(dao.clear(User.class, Cnd.where("email", "in", s))>0)return "true";
            return "false";
      }
      
    //用户修改
      @At
      @Ok("beetl:WEB-INF/web/useredit.html")
      public void goEdit(@Param("email")String email,HttpSession session,HttpServletRequest req){
         //Cnd cnd=Cnd.where("uid","=", uid);
         User user=dao.fetch(User.class,email);
         req.setAttribute("user", user);
      }
      @At
      @Ok("raw")
      public String saveEdit(@Param("..")User user,HttpSession session,HttpServletRequest req){
            try{
                  String pwd=user.getPwd();
                  if(pwd.length()>0){//用户输入了新密码
                        pwd=lrwCode(pwd,"");
                        user.setPwd(pwd);
                  }else user.setPwd(null);//保留原密码
                  if(dao.updateIgnoreNull(user)==1)return "true";
                  else return "修改失败";
            }catch(Exception e){
                  e.printStackTrace();
                  return  "修改失败";
            }
      }
      
    //用户查询
  
	  //查询单个用户--登录
	    @At
	    @Ok("json")
	    @Filters()
	    public Object doLogin(@Param("rememberMe")String rememberMe, @Param("uid")String email, @Param("pwd") String pwd,
	            HttpSession session,HttpServletRequest req,HttpServletResponse res){
	      NutMap re = new NutMap();
	      User user = dao.fetch(User.class, email);
	      
	      if (user == null ) {
	          re.put("ok", false);
	          re.put("msg", "考号不存在");
	          return re;
	      }
	      String p =lrwCode(pwd, "");
	      if (!p.equals(user.getPwd())) {
	          re.put("ok", false);
	          re.put("msg", "密码不正确");
	          return re;
	      }
	      rememberMe(rememberMe, email, pwd, req, res);
	      
	      ////登录成功后...记录登录日志	      
	      String role=user.getRole();
	      
	      Loginlog log = new Loginlog();
	      log.setEmail(user.getEmail());
	      log.setLoginip(getIpAddr(req));
	      log.setLogintime(new Timestamp(new Date().getTime()));
	      log.setBver("");
	      dao.insert(log);
	      
	      session.setAttribute("me", user);
	      ////登录成功后...不同角色将请求跳转到不同页面
	      if(role.equals("5")) {
	          re.put("ok", true);
	          re.put("msg", "user/goIndex");
	          return re;
	      }
	      else if(role.equals("1")) {
	          re.put("ok", true);
	          re.put("msg", "user/goAdmin");
	          return re;
	      }
	      else {
	          re.put("ok", false);
	          re.put("msg", "系统错误！");
	          return re;
	      }
	    }
	    
	    //用户注册
	    @At
	    @Ok("raw")
	    public String doRegist(@Param("email")String email,@Param("xm")String xm,@Param("pwd")String pwd,@Param("sex")String sex,HttpSession session,HttpServletRequest req){
	    		User user = new User();
	    		user.setEmail(email);
	    		user.setXm(xm);
	    		String p;
	    		p = lrwCode(pwd,"");
                user.setPwd(p);
                user.setSex(sex);
                user.setRole("5");
                dao.insert(user);    	
                return "true";
	    }
	    
	    @At
	    @Ok("raw")//分页查询指定用户或所有用户
	    private void rememberMe(String rememberMe, String account, String password, HttpServletRequest request,
				HttpServletResponse response) {
			if ("true".equals(rememberMe)) {
				//
				Cookie cookie = new Cookie("COOKIE_ACCOUNT", account);
				cookie.setPath("/");
				cookie.setMaxAge(365 * 24 * 3600);
				response.addCookie(cookie);

				//
				cookie = new Cookie("COOKIE_PASSWORD",password);
				cookie.setPath("/");
				cookie.setMaxAge(365 * 24 * 3600);
				response.addCookie(cookie);
			} else {
				//
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie c : cookies) {
						if ("COOKIE_ACCOUNT".equals(c.getName()) || "COOKIE_PASSWORD".equals(c.getName())) {
							c.setMaxAge(0);
							c.setPath("/");
							response.addCookie(c);
						}
					}
				}
			}
		}
	    
	//查
      @At
      @Ok("beetl:WEB-INF/web/userlist.html")//跳转到用户信息列表
      public void goList(HttpSession session,HttpServletRequest req){
      }
      
    @At
    @Ok("raw")//分页查询指定用户或所有用户
    public String listUser(@Param("page") int curPage, @Param("rows") int pageSize,
                       @Param("s_name") String s_name, HttpSession session) {
        Criteria cri = Cnd.cri(); 
        if (!Strings.isBlank(s_name)) {
            cri.where().andLike("email", s_name).orLike("xm", s_name);
        }
        else cri.where().andEquals("1", 1);
        cri.getOrderBy().asc("uid");
        return listPageJson(dao, User.class, curPage,pageSize, cri);
    }
      
    //普通用户登录成功，跳转到电影页面
    @At
    @Ok(">>:/jsp/loginsuccess.jsp")
      public void goIndex(HttpSession session,HttpServletRequest req){
            User loginuser=(User)session.getAttribute("me");
            session.setAttribute("username", loginuser.getEmail());
      }
      
      @At
      @Ok("beetl:WEB-INF/web/admin.html")//管理员登录成功，跳转到管理信息页面
      public void goAdmin(HttpSession session,HttpServletRequest req){
            User loginuser=(User)session.getAttribute("me");
            session.setAttribute("user", loginuser.getEmail());
      }
      @At
      @Ok(">>:/")//登出系统
      public void doLogout(HttpSession session) {
          session.invalidate();
      }
    
      ///查看登录日志
      
      @At
      @Ok("beetl:WEB-INF/web/loglist.html")
      public void goLog() {         
      }
      
      @At
      @Ok("raw")//分页查询所有登录日志
      public String listLog(@Param("page") int curPage,@Param("rows") int pageSize) {  	  	
    	    Criteria cnd = Cnd.cri(); 
            cnd.getOrderBy().asc("id");
            return listPageJson(dao, Loginlog.class, curPage, pageSize, cnd);
      }
      
      
      //数据封装成json
      @At
      @Ok("raw")
      public String menutree(HttpServletRequest req, HttpSession session) {
        User user = (User) session.getAttribute("me");
        String role=user.getRole();
        //父级菜单
          List<Cmenu> menulist=dao.query(Cmenu.class, Cnd.where("id","like","__").asc("id"));//id=01,02,03
          List<EasyUITree> eList = new ArrayList<EasyUITree>();
          if(menulist.size() != 0){
            for (int i = 0; i < menulist.size(); i++) {
                        Cmenu t = menulist.get(i);
                        if(!t.getPermission().contains(role))continue;//无权限,不显示相应菜单
                  EasyUITree e = new EasyUITree();
                  e.setId(t.getId());
                  e.setText(t.getName());
                  List<EasyUITree> eList2 = new ArrayList<EasyUITree>();
                  List<Cmenu> menu2 = dao.query(Cmenu.class, Cnd.where("id", "like", t.getId() + "__").asc("id")  );
                  for (int j = 0; j < menu2.size(); j++) {//二级菜单
                  Cmenu t2 = menu2.get(j);
                  if(!t2.getPermission().contains(role))continue;
                  Map<String,Object> attributes = new HashMap<String, Object>();
                        attributes.put("url", t2.getUrl());
                        attributes.put("role", t2.getPermission());
                        EasyUITree e1 = new EasyUITree();
                        e1.setAttributes(attributes);
                        e1.setId(t2.getId());
                        e1.setText(t2.getName());
                        e1.setState("open");
                        eList2.add(e1);
                  }
                  e.setChildren(eList2);
                  e.setState("closed");
                  eList.add(e);
              }
          }
          return Json.toJson(eList);
      }
}
