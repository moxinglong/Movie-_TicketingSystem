<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String username = (String)session.getAttribute("username");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
 <%@ page import="com.moxl.movie.nutz.OrderModule,com.moxl.movie.pojo.Myorder,java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>购物车</title>
        <link rel="icon" href="<%=basePath%>/include/images/ico.png" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/shopping.css"/>
        <script type="text/javascript" src="<%=basePath%>/include/js/jquery-2.1.1.min.js"></script>
<style type="text/css">
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
			<div style="height:100%;width:200px;z-index: 3000;margin-top: -60px;margin-left: 20px;"><a href="loginsuccess.jsp"><font color="gray" style="font-family: 微软雅黑;size: 20px;">返回首页</font></a></div>
			<img src="<%=basePath%>/include/images/shop.png" style="height: 60px;float: right;margin-top: -83px;">
		</div>	
    <img src="<%=basePath%>/include/img//background/gouwuche.png" style="width: 100%;">
   <div
		style="width: 100%;height: 650px;background-image: url('<%=basePath%>/include/img//background/gouwuche2.png');">
		<div id="wrapper" style="padding-top: 100px;">
			<div id="content">
				<div id="cart-product">
					<table cellspacing="2px" border="1px" style="font-size: 20px;">
							<tr>
								<th width="1%">用户Email</th>
								<th width="10%">电影名</th>
								<th width="10%">放映室</th>
								<th width="25%">座位号</th>
								<th width="10%">放映时长</th>
								<th width="1%">总价</th>
								<th width="30%">放映时间段</th>
								<th width="25%">下单时间</th>
							</tr>
							<%
								List orders = OrderModule.queryOrderByUemail(username);
									for(Object obj:orders){
										Myorder order = (Myorder)obj;
							 %>
							<tr>
								<td width="10px"><%=order.getUemail() %></td>
								<td><%=order.getMname() %></td>
								<td><%=order.getRname() %>号放映室</td>
								<td><%=order.getSeat() %></td>
								<td><%=order.getMtime() %>分钟</td>
								<td class="subtotal">￥<%=order.getAllprice() %><span>.00</span>
								</td>
								<td width="80px"><%=sdf.format(order.getStarttime())%>-<%=sdf.format(order.getEndtime())%></td>
								<td width="80px"><%=sdf.format(order.getOtime()) %></td>
							</tr>
							<%} %>
					</table>
					
				</div>
			</div>
		</div>
	</div>
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