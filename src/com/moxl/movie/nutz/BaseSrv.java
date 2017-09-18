package com.moxl.movie.nutz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.moxl.movie.pojo.EasyUITree;



public class BaseSrv {
	/*
	 *    获取Sha256Hash加密密码
	 */
      public static String lrwCode(String password,String salt){
            if(salt==""){
                  /*RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            salt = rng.nextBytes().toBase64();*/
                  salt="abcdefghijklmnopqrstuvwx";
            }
            return new Sha256Hash(password, salt, 1024).toBase64();
      }
		/*
		 * 取登录时的IP
		 */
		public static String getIpAddr(HttpServletRequest request) {
	    	String ip="";
	    	ip = request.getHeader("X-Forwarded-For");
			if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
				int index = ip.indexOf(',');
				if (index != -1) {return ip.substring(0, index);}
				else {return ip;}
			}
	    	ip = request.getHeader("X-Real-IP");
			if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {return ip;}
			ip = request.getRemoteAddr();
			if(ip.startsWith("0:")) return "127.0.0.1";//本机ip
			else return ip;
		}
	    /**
	     * 根据查询条件分页,返回封装好的 Easyui.datagrid JSON
	     *
	     * @param dao
	     * @param obj
	     * @param curPage
	     * @param pageSize
	     * @param cnd
	     * @return
	     */
	    public static <T> String listPageJson(Dao dao, Class<T> obj, int curPage,
	                                   int pageSize, Condition cnd) {
	        Map<String, Object> jsonobj = new HashMap<String, Object>();
	        Pager pager = dao.createPager(curPage, pageSize);
	        List<T> list = dao.query(obj, cnd, pager);
	        pager.setRecordCount(dao.count(obj, cnd));//记录数需手动设置
	        jsonobj.put("total", pager.getRecordCount());
	        jsonobj.put("rows", list);
	        return Json.toJson(jsonobj);
	    }

	    /**
	     * 根据查询条件分页,返回封装好的 Easyui.datagrid JSON
	     *
	     * @param dao
	     * @param obj
	     * @param cnd
	     * @param pager
	     * @return
	     */
	    public static <T> String listPageJson(Dao dao, Class<T> obj, Condition cnd, Pager pager) {
	        Map<String, Object> jsonobj = new HashMap<String, Object>();
	        List<T> list = dao.query(obj, cnd, pager);
	        pager.setRecordCount(dao.count(obj, cnd));//记录数需手动设置
	        jsonobj.put("total", pager.getRecordCount());
	        jsonobj.put("rows", list);
	        return Json.toJson(jsonobj);
	    }
	    
    	protected static <T> String dgData(Dao dao,Class<T> obj,HttpServletRequest req){
    		try{
    			req.setCharacterEncoding("UTF-8");
    			String deleted = req.getParameter("deleted");
    			String inserted = req.getParameter("inserted");
    			String updated = req.getParameter("updated");
    			if(deleted != null){
    			    List<T> listDeleted = Json.fromJsonAsList(obj, deleted);
    			    for(int i=0;i<listDeleted.size();i++)dao.delete(listDeleted.get(i));
    			}

    			if(inserted != null){
    			    List<T> listInserted = Json.fromJsonAsList(obj, inserted);
    			    for(int i=0;i<listInserted.size();i++)dao.insert(listInserted.get(i));
    			}

    			if(updated != null){
    			    List<T> listUpdated = Json.fromJsonAsList(obj, updated);
    			    for(int i=0;i<listUpdated.size();i++)dao.update(listUpdated.get(i));
    			}
    			return "true";
    		}catch(Exception e){
    			e.printStackTrace();
    			return "false";
    		}
    	}
		    	
		    	
    	/*
	     * 数据封装成Easyui.tree json
	     * 树形数据：id和Text是相同字符串
	     */
	    	@At
	    	@Ok("raw")
	    	public static String strtree2(List<String> strlist,HttpServletRequest req, HttpSession session) {
	    	    List<EasyUITree> eList = new ArrayList<EasyUITree>();
	    	    if(strlist.size() != 0){
	    	    	EasyUITree e = new EasyUITree();
    	            e.setId("全部");
    	            e.setText("全部");
    	            List<EasyUITree> eList2 = new ArrayList<EasyUITree>();
	    	    	for (int i = 0; i < strlist.size(); i++) {
	    		            EasyUITree e1 = new EasyUITree();
	    		            e1.setId(strlist.get(i));
	    		            e1.setText(strlist.get(i));
	    		            e1.setState("open");
	    		            eList2.add(e1);
	    	            }
	    	            e.setChildren(eList2);
	    	            e.setState("closed");
	    	            eList.add(e);
	    	    }
	    	    return Json.toJson(eList);
	    	}
}
