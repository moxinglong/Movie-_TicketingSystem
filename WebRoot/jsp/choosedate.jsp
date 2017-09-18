<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.moxl.movie.nutz.MovieModule"%>
<%@ page import="com.moxl.movie.nutz.PlayModule"%>
<%@ page import="com.moxl.movie.pojo.Movie"%>
<%@ page import="com.moxl.movie.pojo.Play"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String username = (String)session.getAttribute("username");
	String moviename = request.getParameter("moviename");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if(moviename.equals(new String(request.getParameter("moviename").getBytes("iso8859-1"), "iso8859-1"))) { 
		moviename = new String(moviename.getBytes("iso8859-1"),"UTF-8"); 
	} 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
    <head>
        <title>选择时间</title>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Slicebox - 3D Image Slider with Fallback" />
        <meta name="keywords" content="jquery, css3, 3d, webkit, fallback, slider, css3, 3d transforms, slices, rotate, box, automatic" />
		<meta name="author" content="Pedro Botelho for Codrops" />
		<link rel="icon" href="<%=basePath%>/include/images/ico.png" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/slicebox.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/custom.css" />
		<link rel="stylesheet" href="http://dreamsky.github.io/main/blog/common/init.css">
		<script type="text/javascript" src="<%=basePath%>/include/js/jquery-2.1.1.min.js"></script>
		<style>
		.istop{
			height: 60px;
			width: 100%;
			background-color: #192332;
		}
		.m_pic img{
			width: 200px;
			height: 267px;
			margin-left: 15px;
			margin-top: 10px;
		}
		</style> 
		<script type="text/javascript">
			function check(){
				var obj=document.getElementsByName("time");
				var obk;
				var num=0;
				for (i=0;i<obj.length ;i++ ){
					if(obj[i].checked ){
				    	num+=1;
				    }
			    }
			    if(num==0){
			    	alert("必须选择一个时间段");
			    	return false;
			    }
			    if(num>1){
			    	alert("最多选择一个");
			    	return false;
			    }
			}
		</script>
		<script type="text/javascript">
			function ck(e){
				var allBox = document.getElementsByName("time");
				for( var i = 0; i < allBox.length; i++ ){
					if(allBox[i].value == e){
						allBox[i].checked = true;						
					}else{
						allBox[i].checked = false;	
					}
  				}
			}
		</script>
	</head>
	<body>
		<div class="istop">&nbsp; 
			<img src="<%=basePath%>/include/images/biaozhi.png" style="margin-left: 45%">
			<img src="<%=basePath%>/include/images/s.jpg" style="height: 60px;margin-left: 350px;">						
			<!-- 显示用户 -->
			<div style="height:100%;width:200px;z-index: 3000;margin-top: -40px;margin-left: 900px;"><font color="gray" style="font-family: 微软雅黑;size: 20px;">欢迎您，<%=username%></font></div><input id="u_email" value="<%=username%>" type="hidden">
			<div style="height:100%;width:200px;z-index: 3000;margin-top: -60px;margin-left: 1150px;"><a href="#" id="logout"><font color="gray" style="font-family: 微软雅黑;size: 20px;">【退出登录】</font></a></div>
			<a href="shopping.jsp"><img src="<%=basePath%>/include/images/shop.png" style="height: 60px;float: right;margin-top: -83px;"></a>
		</div>
		<div style="width: 700px;height: 400px;margin-top: 20px;background-color: white;margin-left: 27%;margin-top: 80px;border: 1px solid gray;">
  		<!-- 左侧开始 -->
  		<% 		List movies = MovieModule.queryMovieByName(moviename);
  					for(Object obj:movies){
			           	Movie movie = (Movie)obj;
  		%>
  		<div class="left" style="width: 230px;height: 90%;float: left;border: 1px solid gray;margin-left: 50px;margin-top: 20px;">
  			<div class="m_pic"><%=movie.getPic()%></div>
  			<img src="<%=basePath%>/include/img/little/pingfen.png" style="margin-top: 20px;margin-left: 15px;">
  			<p style="margin-top: -30px;margin-left: 27px;"><font color="white" style="font-family: 微软雅黑;font-size: 15px;"><%=movie.getGrad()%></font>
  			<div style="margin-left: 80px;margin-top: -30px;">
	  			<p><font style="font-family: 微软雅黑;font-size: 15px;">类型：<%=movie.getTyp()%></font></p>
	  			<p><font style="font-family: 微软雅黑;font-size: 15px;">时长：<%=movie.getTime()%>分钟</font></p>
  			</div>	
	    </div>
	    <%}%>
	    <!-- 左侧结束-->
	    
	    <!-- 右侧开始 -->
	    <div class="right" style="width: 418px;height: 90%;float: right;margin-top: 20px;">
	    <form action="choose.jsp" id=""test>
	    	<p style="margin-left: 20px;"><font style="font-family: 微软雅黑;font-size: 40px;"><%=moviename%></font></p>
	    	<input type="hidden" name="moviename" value="<%=moviename%>">
	    	<div style="margin-top: 30px;">
		    </div>
		    <div style="width: 100%;height: 50px;background-color: #127BAB;"><p style="margin-left: 37%;margin-top: 30px;"><font color="white" style="font-family: 微软雅黑;font-size: 30px;">时间段</font></p></div>
	    	<div style="margin-top: 50px;">
		    	 <% 
					List plays = PlayModule.queryTimeByName(moviename);
					for(Object obj:plays){
					   Play play = (Play)obj;
				%>
		    	<input id="time" name="time" type="checkbox" value="<%=sdf.format(play.getStarttime())%>-<%=sdf.format(play.getEndtime())%>" onclick="ck(this.value)" style="width: 20px;height: 20px;margin-left: 30px;"><font style="font-family: 微软雅黑;"><%=sdf.format(play.getStarttime())%>-<%=sdf.format(play.getEndtime())%></font>
		    	<br>
		    	<br>
		    	<%} %>
		    </div>
		    <input type="submit" value="确认" onclick="return check();" style="margin-left:120px;margin-top:60px;width: 150px;height: 40px;border-radius:20px;background-color: #127BAB;color:white;cursor: pointer;">
	   	</form>
	    </div>
	    </div>
	    <!-- 右侧结束 -->
	</body>
	<script type="text/javascript">
		$(function(){
		    var u_email = document.getElementById("u_email").value;
		    if(u_email == "null"){
		    	top.location.href = "<%=basePath%>/user/doLogout";
		    }
		});
		
		$(function(){
			$("#logout").click(function() {
		      top.location.href = "<%=basePath%>/user/doLogout";
		   });
		});
	</script>
</html>	