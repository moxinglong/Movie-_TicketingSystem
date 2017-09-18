<%@page import="org.beetl.ext.fn.ParseInt"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.moxl.movie.nutz.MovieModule"%>
<%@ page import="com.moxl.movie.nutz.PlayModule"%>
<%@ page import="com.moxl.movie.pojo.Movie"%>
<%@ page import="com.moxl.movie.pojo.Play"%>
<%@ page import="com.moxl.movie.pojo.Seat"%>
<%
	String username = (String)session.getAttribute("username");
	String moviename = request.getParameter("moviename");
	if(moviename.equals(new String(request.getParameter("moviename").getBytes("iso8859-1"), "iso8859-1"))) { 
		moviename = new String(moviename.getBytes("iso8859-1"),"UTF-8"); 
	} 
	String time = request.getParameter("time");
	String starttime = time.substring(0, (time.length()-1)/2);
	String endtime = time.substring((time.length()-1)/2+1, time.length());
	List seats = new ArrayList();
	String sname = "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
    <head>
        <title>选择座位</title>
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
		<script type="text/javascript" src="<%=basePath%>/include/js/choose.js"></script>
		<script>var base="<%=basePath%>";</script>
		<% 
		for(int j=1,n=0;j<=6;j++,n+=10){
		for(int i=1;i<=10;i++){%>
			<style type="text/css">
			/* .squaredThree */
			.squaredThree<%=i+n%> {
			  width: 20px;
			  position: relative;
			}
			
			.squaredThree<%=i+n%> label {
			  width: 20px;
			  height: 20px;
			  cursor: pointer;
			  position: absolute;
			  top: 0;
			  left: 0;
			  background: -moz-linear-gradient(top, #222222 0%, #45484d 100%);
			  background: -webkit-linear-gradient(top, #222222 0%, #45484d 100%);
			  background: linear-gradient(to bottom, #222222 0%, #45484d 100%);
			  -moz-border-radius: 4px;
			  -webkit-border-radius: 4px;
			  border-radius: 4px;
			  -moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.4);
			  -webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.4);
			  box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.4);
			}
			.squaredThree<%=i+n%> label:after {
			  content: '';
			  width: 9px;
			  height: 5px;
			  position: absolute;
			  top: 4px;
			  left: 4px;
			  border: 3px solid #fcfff4;
			  border-top: none;
			  border-right: none;
			  background: transparent;
			  filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
			  opacity: 0;
			  -moz-transform: rotate(-45deg);
			  -ms-transform: rotate(-45deg);
			  -webkit-transform: rotate(-45deg);
			  transform: rotate(-45deg);
			}
			.squaredThree<%=i+n%> label:hover::after {
			  filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=30);
			  opacity: 0.3;
			}
			.squaredThree<%=i+n%> input[type=checkbox] {
			  visibility: hidden;
			}
			.squaredThree<%=i+n%> input[type=checkbox]:checked + label:after {
			  filter: progid:DXImageTransform.Microsoft.Alpha(enabled=false);
			  opacity: 1;
			}
			
			/* end .squaredThree */
			</style>
		<%}}%>
		<style>
		.istop{
			height: 60px;
			width: 100%;
			background-color: #192332;
		}
		body .ondisplay {
		  text-align: center;
		}
		
		body .ondisplay section {
		  width: 20px;
		  height: 20px;
		  background: #555;
		  display: inline-block;
		  position: relative;
		  text-align: center;
		  margin-top: 5px;
		  border: 1px solid gray;
		  -moz-border-radius: 5px;
		  -webkit-border-radius: 5px;
		  border-radius: 5px;
		  -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
		  -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
		  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
		}
		</style>
		<style>
		.istop{
			height: 60px;
			width: 100%;
			background-color: #192332;
		}
		</style>
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
		<div style="width: 1000px;height: 700px;margin-top: 20px;background-color: white;margin-left: 14%;">
  		<!-- 左侧开始 -->
  		<div class="left" style="width: 649px;height: 100%;float: left;">
  			<div align="center" style="height: 100%;width: 570px;">
  				<img src="<%=basePath%>/include/img/little/hengtiao.png" style="margin-top: 30px;margin-left: 20px;">
  				<% 
  					List plays = PlayModule.queryPlayByMovieNameTime(moviename,starttime,endtime);
  					
					for(Object obj:plays){
					   Play play = (Play)obj;
					   sname = play.getRname();
  				%>
  				<p><font style="font-family: 微软雅黑;font-size: 20px;"><%=play.getRname()%>号厅</font></p>
  				（剩余<%=play.getSeatnum() %>个座位）
  				<%
  					}
  				%>
			    <table style="margin-top: 50px;">
			    	<%for(int j=1, n=0;j<=6;j++,n+=10){
			    	 %>
			    	<tr>
			    		<td style="padding-top: 10px;"><font style="font-family: 微软雅黑;font-size: 20px;"><%=j%>排</font></td><td style="padding-left: 90px;"></td>
						<%for(int i=1;i<=10;i++){
							 seats = PlayModule.querySeatByNameTime(sname,starttime,endtime);
							 int[] a = new int[61];
							   	for(Object oj:seats){
							   		Seat seat = (Seat)oj;
							   		a[Integer.parseInt(seat.getSid())] = Integer.parseInt(seat.getSid());
							   	}
							if(a[n+i]==(n+i)){
						%>
			    		<td>
			    			<img src="<%=basePath%>/include/img/isck.png" style="margin-top: 5px;"/>
			    			<div class="ondisplay" style="display: none;">
							  <section title="<%=j%>排<%=i%>座">
							    <!-- .squaredThree -->
							    <div class="squaredThree<%=n+i%>" >
							      <input type="checkbox" value="<%=n+i%>" id="squaredThree<%=n+i%>" name="check"/>
							      <label for="squaredThree<%=n+i%>"></label>
							      <input id="xuanzhong<%=n+i%>" type="hidden" value="<%=j%>排<%=i%>座"/>
							    </div>
							    <!-- end .squaredThree -->
							  </section>
							</div>
			    		</td>
			    		<%}else{
			    		%>
			    		<td>
			    			<div class="ondisplay">
							  <section title="<%=j%>排<%=i%>座">
							    <!-- .squaredThree -->
							    <div class="squaredThree<%=n+i%>" >
							      <input type="checkbox" value="<%=n+i%>" id="squaredThree<%=n+i%>" name="check" onclick="cktest(this.value)"/>
							      <label for="squaredThree<%=n+i%>"></label>
							      <input id="xuanzhong<%=n+i%>" type="hidden" value="<%=j%>排<%=i%>座"/>
							    </div>
							    <!-- end .squaredThree -->
							  </section>
							</div>
			    		</td>			    			
			    		<%}}%>
			    	</tr>
			     <%
			     }%>
			    </table>
			   <p style="margin-top: 40px;margin-left: 120px;">
			   		<img src="<%=basePath%>/include/img/isck.png" style="margin-left: 20px;"/><font style="font-family: 微软雅黑;font-size: 15px;">已选座位</font>
			   		<img src="<%=basePath%>/include/img/ck.png" style="margin-left: 20px;"/><font style="font-family: 微软雅黑;font-size: 15px;">可选座位</font>
			   		<img src="<%=basePath%>/include/img/ck.png" style="margin-left: 20px;"/><img src="<%=basePath%>/include/img/ck.png"/><font style="font-family: 微软雅黑;font-size: 15px;">情侣座位</font>
			   	</p>
		    </div>
	    </div>
	    <!-- 左侧结束-->
	    
	    <!-- 分界线开始-->
	    <img src="<%=basePath%>/include/images/s.jpg" style="width: 1px;height: 100%;opacity:0.2;">
	    <!-- 分界线结束-->
	    
	    <!-- 右侧开始 -->
	    <div class="right" style="width: 340px;height: 100%;float: right;">
	    	<form id="order_form" method="post" onsubmit="return false">
	    	<input id="uemail" name="uemail" value="<%=username%>" type="hidden">
	    	<img src="<%=basePath%>/include/img/little/tick.png" style="margin-top: 40px;margin-left: 20px;">
	    	<div style="width: 275px;height: 170px;margin-top:10px;margin-left: 20px;">
	    		<% 
					List movies = MovieModule.queryMovieByName(moviename);
					for(Object obj:movies){
					   Movie movie = (Movie)obj;
				%>
	    		<div style="width: 60px;height: 170px;margin-top: 20px;">
	    			<%=movie.getPic()%>
	    		</div>
	    		
	    		<div style="height: 100%;width: 150px;float: right;margin-top: -170px;">
	    			<p><font style="font-family: 宋体;font-size: 20px;">《<%=movie.getName()%>》</font></p>
	    			<input id="roomname" name="roomname" type="hidden" value="<%=sname%>"/>
	    			<input id="moviename" name="moviename" type="hidden" value="<%=movie.getName()%>"/>
	    			<input id="mtime" name="mtime" type="hidden" value="<%=movie.getTime()%>"/>
	    			<input id="starttime" name="starttime" type="hidden" value="<%=starttime%>"/>	
	    			<input id="endtime" name="endtime" type="hidden" value="<%=endtime%>"/>		    			
	    			<p style="margin-top: 30px;margin-left: 10px;"><font style="font-family: 微软雅黑;font-size: 10px;">类型：<%=movie.getTyp()%></font></p>
	    		</div>
	    		<%} %>
	    	</div>
	    	<table style="margin-top: 50px;margin-left: 30px;">
	    		<tr>
	    		</tr>
	    		<tr>
	    			<td>场次：<input id="time" name="time" type="text" value="<%=time%>" readonly="readonly" style="width: 250px;height: 30px;border: none;"/></td>
	    		</tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	    		<tr>
	    			<td>座位：<input id="seat1" name="seat" type="text" readonly="readonly" style="width: 45px;height: 30px;margin-top: -5px;border: none;"/>
	    					  <input id="seat2" name="seat" type="text" readonly="readonly" style="width: 45px;height: 30px;margin-top: -5px;border: none;"/>
	    					  <input id="seat3" name="seat" type="text" readonly="readonly" style="width: 45px;height: 30px;margin-top: -5px;border: none;"/>
	    					  <input id="seat4" name="seat" type="text" readonly="readonly" style="width: 45px;height: 30px;margin-top: -5px;border: none;"/>
	    					  
	    					  <input id="sno1" name="sno" type="hidden"/>
	    					  <input id="sno2" name="sno" type="hidden"/>
	    					  <input id="sno3" name="sno" type="hidden"/>
	    					  <input id="sno4" name="sno" type="hidden"/>
	    			</td>
	    		</tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	    		<tr>
	    			<% 
					movies = MovieModule.queryMovieByName(moviename);
					for(Object obj:movies){
					   Movie movie = (Movie)obj;
					%>
	    			<td>票价：<input id="price" type="text" value="<%=movie.getPric()%>" readonly="readonly" style="width: 30px;height: 20px;border: none;"/>元/张</td>
	    			<%} %>
	    		</tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	    		<tr>
	    			<td>票数：<input id="num" name="num" value="0" readonly="readonly" style="border: none;"/></td>
	    		</tr><tr></tr><tr></tr><tr></tr><tr></tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/include/images/s1.jpg" style="width:250px;height: 1px;margin-top: 15px;margin-bottom: 20px;opacity:0.2;"/></td>
	    		</tr>
	    		<tr>
	    			<td>总计：<font color="#F78404" style="font-family: 微软雅黑;font-size: 20px;">￥<input id="allprice" name="allprice" type="text" value="0" readonly="readonly" style="width: 30px;height: 30px;border: none;"/></font></td>
	    		</tr>
	    	</table>
	    		<input id="order_submit" type="submit" value="确认购买" onclick="return check();" style="width: 220px;height: 40px;cursor: pointer;background-color: #FC8D30;color: white;border-radius:20px;margin-left: 30px;margin-top: 20px;"/>
	    </form>
	    </div>    
	    <!-- 右侧结束 -->
    </div>
	</body>
	<script type="text/javascript">
			$(document).ready(function () {
			    $('#order_form input').keydown(function (e) {
			        if (e.keyCode == 13)
			        {
			            checkUserName();//$('#login_submit').click();
			        }
			    });
			    $("#order_submit").click(saveAdd);  
			});
			
			function saveAdd()//登录前，校验用户信息
			{    
			      var uemail = $('#uemail').val();
			      var rname = $('#roomname').val();
			      var obj = document.getElementsByName("seat");
			      var obk = document.getElementsByName("sno");
				  var seat = new Array();
				  var sno = new Array();
				  for(var i = 0; i < obj.length; i++) {
		              seat[i] = obj[i].value;
		     	  }
		     	  for(var j = 0; j < obk.length; j++) {
		              sno[j] = obk[j].value;
		     	  }		
			      var mname = $('#moviename').val();
			      var mtime = $('#mtime').val();
			      var allprice = $('#allprice').val();
			      var starttime = $('#starttime').val();
			      var endtime = $('#endtime').val();	
			      var num = $('#num').val();
	      
			      $.ajax({
			            url : base+"order/saveAdd",
			            data :{"uemail":uemail,"rname":rname,"seat":seat.toString(),"mname":mname,"mtime":mtime,"allprice":allprice,"starttime":starttime,"endtime":endtime,"sno":sno.toString(),"num":num},
			            type:"POST",
			            success : function (res) {
			                  if (1 == 1) {
			                  		alert("购买成功!");
			                        window.location.href=base+res;
			                  }else{
			                  		alert(res.msg);
			                  }
			                  return false;
			            },
			            error : function(res) {alert("系统错误！");      }
			      });
			}
		
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