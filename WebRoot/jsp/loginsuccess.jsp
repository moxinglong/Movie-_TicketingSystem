<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.moxl.movie.nutz.MovieModule"%>
<%@ page import="com.moxl.movie.pojo.Movie"%>
<%@ page import="com.moxl.movie.nutz.PlayModule"%>
<%
	String username = (String)session.getAttribute("username");
	boolean flag = false;
	request.setCharacterEncoding("UTF-8"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
    <head>
        <title>电影院</title>
		<meta name="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Slicebox - 3D Image Slider with Fallback" />
        <meta name="keywords" content="jquery, css3, 3d, webkit, fallback, slider, css3, 3d transforms, slices, rotate, box, automatic" />
		<meta name="author" content="Pedro Botelho for Codrops" />
		<link rel="icon" href="<%=basePath%>/include/images/ico.png" type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/slicebox.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/include/css/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>include/css/themes/default/easyui.css">
	    <link rel="stylesheet" type="text/css" href="<%=basePath%>include/css/themes/icon.css">
		<script type="text/javascript" src="<%=basePath%>/include/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>include/js/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>include/js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/include/js/modernizr.custom.46884.js"></script>
		<script type="text/javascript" src="<%=basePath%>/include/js/index2.js"></script>
		<script>var base="<%=basePath%>";</script>
		<style type="text/css">
		*, *:after, *:before { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; }
		.clearfix:after{visibility:hidden;display:block;font-size:0;content:" ";clear:both;height:0}
		.clearfix{*zoom:1}
		.fl{float:left}
		.fr{float:right}
		.fl,.fr{_display:inline}
		.top-banner {
			position:absolute;
			z-index: 999;
			left:0;
			top:0;
			height:40px;
			line-height:40px;
			padding:0 30px;
			width:100%;
			font-size: 13px;
			background-color: rgba(255, 255, 255, 0.15);
			color: #fff;
			/*text-shadow: 1px 1px 3px #333;*/
			/*box-shadow: 0 1px 0 #999;*/
		}
		.top-banner a {
			color: #fff;
			text-decoration: none;
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
			<div style="width: 100%;height: 500px;">
			<script type="text/javascript" src="<%=basePath%>/include/js/jquery.min.js"></script>
			<!-- 图片轮播层开始 -->
				<div class="win">
					<div class="box">
						<div style="left:0"><a href="javascript:move()"><img src="<%=basePath%>/include/images/1.jpg"/></a></div>
						<div><a href="javascript:move()"><img src="<%=basePath%>/include/images/2.jpg"/></a></div>
						<div><a href="javascript:move()"><img src="<%=basePath%>/include/images/3.jpg"/></a></div>
						<div><a href="javascript:move()"><img src="<%=basePath%>/include/images/4.jpg"/></a></div>
						<div><a href="javascript:move()"><img src="<%=basePath%>/include/images/5.jpg"/></a></div>
					</div>
					<div class="title">
						<a href="javascript:;" style="color:#fff">1</a>
						<a href="javascript:;">2</a>
						<a href="javascript:;">3</a>
						<a href="javascript:;">4</a>
						<a href="javascript:;">5</a>
						<div class="float"></div>
					</div>
					<div class="leftB">&lt;</div>
					<div class="rightB">&gt;</div>					
				</div>
			<!-- 图片轮播层结束 -->
			</div>
			<div class="istop"></div>
		<div class="footer-banner" style="width:728px; margin:30px auto"></div>
		<div class="div1"
			style="width: 1000px;height:1500px;background-color: #F2F2F2;margin-left: 185px;">
			<img src="<%=basePath%>/include/img/little/1.png" style="height: 80px;">
			<div class="div2" style="margin-top: -70px;margin-left: 30px;">
				<%
					List movies = MovieModule.QueryAllMovie();
				%>
				<font size="5px">正在热映</font><span><font size="10px"><%=movies.size()%></font></span><font
					size="5px">部</font> <a href="javascript:openmovieType(1);" id="a1"
					onclick="changecolor(1)" style="margin-left: 50px;"><font
					size="3px">全部</font><span> |</span></a> <a
					href="javascript:openmovieType(2);" id="a2"
					onclick="changecolor(2)" style="color:blue;"><font size="3px">3D</font><span>
						|</span></a> <a href="javascript:openmovieType(3);" id="a3"
					onclick="changecolor(3)" style="color:blue;"><font size="3px">爱情</font><span>
						|</span></a> <a href="javascript:openmovieType(4);" id="a4"
					onclick="changecolor(4)" style="color:blue;"><font size="3px">剧情</font><span>
						|</span></a> <a href="javascript:openmovieType(5);" id="a5"
					onclick="changecolor(5)" style="color:blue;"><font size="3px">奇幻</font><span>
						|</span></a> <a href="javascript:openmovieType(6);" id="a6"
					onclick="changecolor(6)" style="color:blue;"><font size="3px">冒险</font><span>
						|</span></a> <a href="javascript:openmovieType(7);" id="a7"
					onclick="changecolor(7)" style="color:blue;"><font size="3px">动作</font><span>
						|</span></a> <a href="javascript:openmovieType(8);" id="a8"
					onclick="changecolor(8);" style="color:blue;"><font size="3px">动画</font></a>
			</div>
			<img src="<%=basePath%>/include/images/s1.jpg"
				style="width: 1000px;height: 1px;margin-top: 14px;">
			<div id="t1">
				<div class="left"
					style="width: 310px;height: 1000px; margin-top:10px; float: left;">
					<img src="<%=basePath%>/include/images/movies/nidemingzi.jpg">
					<p style="margin-left: 70px;margin-top: 20px;">
						<font size="5px;">你的名字</font>
					</p>
					<p style="margin-left: 70px;margin-top: 5px;">106分钟 - 动画 / 剧情</p>
					<img src="<%=basePath%>/include/images/movies/douhao.png"
						style="padding-top: 10px;margin-left: 10px;">
					<p style="margin-top: -20px;margin-left: 40px;">
						<font size="4px;" color="#679C21">穿越时空的遇见</font>
					</p>

					<img src="<%=basePath%>/include/images/movies/changcheng.png" style="margin-top: 50px;">
					<p style="margin-left: 70px;margin-top: 20px;">
						<font size="5px;">长城</font>
					</p>
					<p style="margin-left: 70px;margin-top: 5px;">104分钟 - 奇幻 / 冒险</p>
					<img src="<%=basePath%>/include/images/movies/douhao.png"
						style="padding-top: 10px;margin-left: 10px;">
					<p style="margin-top: -20px;margin-left: 40px;">
						<font size="4px;" color="#679C21">来自张艺谋的视觉饕餮盛宴</font>
					</p>
				</div>
				
				<div class="right"
					style="width:650px;height:600px; margin-left: 340px;">
					<%
						for (Object obj : movies) {
													Movie movie = (Movie) obj;
					%>
					<div class="right-left"
						style="width: 310px;height: 180px;margin-left:10px;margin-top:10px; float: left;">
						<a><%=movie.getPic()%></a> <img
							src="<%=basePath%>/include/images/movies/grade.png"
							style="margin-left: -27px;margin-bottom: -8px;">
						<p style="padding-left:109px;margin-top: -12px; ">
							<font color="white"><%=movie.getGrad()%></font>
						</p>
						<div class="right-right"
							style="width: 170px;height: 170px;margin-left: 125px;margin-top:-170px ;">
							<a class="title"><p
									style="margin-left: 10px;padding-top: 5px;">
									<font size="4px;"><%=movie.getName()%></font>
								</p></a>
							<p style="margin-left: 10px;padding-top: 5px;"><%=movie.getTime()%>分钟
								<font size="4px;">/<%=movie.getTyp()%></font>
							</p>
							<p style="margin-left: 10px;padding-top: 5px;">
								影院上映<%=movie.getDegr()%>场</p>
							<img src="<%=basePath%>/include/images/movies/douhao.png"
								style="padding-top: 10px;margin-left: 10px;">
							<p style="margin-top: -20px;margin-left: 40px;">
								<font color="#679C21">
									<%
										if (movie.getDes() != null) {
									%><%=movie.getDes()%>
									<%
										}
									%>
								</font>
							</p>
							<%
								flag = PlayModule.queryMovieByName(movie.getName());
								if(flag){
							%>
								<a class="xuangou" href="choosedate.jsp?moviename=<%=movie.getName()%>"><img src="<%=basePath%>/include/images/movies/xuangou.png" style="padding-top: 20px;margin-left: 10px;"></a>
		           			<%}else{%>
		           				<a class="xuangou" href="javascript:NotAction();"><img src="<%=basePath%>/include/images/movies/xuangou.png" style="padding-top: 20px;margin-left: 10px;"></a>
		           			<%} %>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<%
				String[] str = new String[]{"3D","爱情","剧情","奇幻","冒险","动作","动画"};
				for(int s = 0;s < str.length; s++){
			%>
					<!-- 所有类型 -->
					<div id="t<%=s+2%>" style="display: none;">
						<%
							String type = str[s];
											List types = MovieModule.queryByType(type);
											for (Object obj : types) {
												Movie movie = (Movie) obj;
						%>
						<div class="right-left"
							style="width: 330px;height: 180px;margin-top:30px; float: left;">
							<a href="#"><%=movie.getPic()%></a> <img
								src="<%=basePath%>/include/images/movies/grade.png"
								style="margin-left: -27px;margin-bottom: -8px;">
							<p style="padding-left:109px;margin-top: -12px; ">
								<font color="white"><%=movie.getGrad()%></font>
							</p>
							<div class="right-right"
								style="width: 200px;height: 170px;margin-left: 125px;margin-top:-170px ;">
								<a class="title" href="#"><p
										style="margin-left: 10px;padding-top: 5px;">
										<font size="4px;"><%=movie.getName()%></font>
									</p></a>
								<p style="margin-left: 10px;padding-top: 5px;"><%=movie.getTime()%>分钟
									<font size="4px;">/<%=movie.getTyp()%></font>
								</p>
								<p style="margin-left: 10px;padding-top: 5px;">
									影院上映<%=movie.getDegr()%>场</p>
								<img src="<%=basePath%>/include/images/movies/douhao.png"
									style="padding-top: 10px;margin-left: 10px;">
								<p style="margin-top: -20px;margin-left: 40px;">
									<font color="#679C21">
										<%
											if (movie.getDes() != null) {
										%><%=movie.getDes()%>
										<%
											}
										%>
									</font>
								</p>
								<%
								flag = PlayModule.queryMovieByName(movie.getName());
									if(flag){
								%>
									<a class="xuangou" href="/jsp/choosedate.jsp?moviename=<%=movie.getName()%>"><img src="<%=basePath%>/include/images/movies/xuangou.png" style="padding-top: 20px;margin-left: 10px;"></a>
			           			<%}else{%>
			           				<a class="xuangou" href="javascript:NotAction();"><img src="<%=basePath%>/include/images/movies/xuangou.png" style="padding-top: 20px;margin-left: 10px;"></a>
			           			<%} %>
							</div>
						</div>
						<%
							}
						%>
					</div>
					<!-- /所有类型 -->
			<%
				}
			 %>
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