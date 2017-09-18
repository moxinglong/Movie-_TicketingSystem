<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%@ page import="com.moxl.movie.nutz.MovieModule"%>
<%@ page import="com.moxl.movie.pojo.Movie"%>
<%
	String applicantAccount = "";
	String applicantPassword = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie cookie:cookies){
	if("COOKIE_ACCOUNT".equals(cookie.getName())){
		applicantAccount = cookie.getValue();
	}
	if("COOKIE_PASSWORD".equals(cookie.getName())){
		applicantPassword = cookie.getValue();
	}
		}
	}
%>
<%
	String username = (String)session.getAttribute("username");
	boolean flag = false;
	request.setCharacterEncoding("UTF-8"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
	<title>电影院</title>
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
	<a name="title"></a>
	<!-- 悬浮回到顶部 -->
	<div style="position:fixed; top:0;z-index:2;margin-top: 300px;">
		<a href="#title"><img src="<%=basePath%>/include/img/back.png"/></a>
	</div>
	<div class="istop">
		&nbsp; <img src="<%=basePath%>/include/images/biaozhi.png" style="margin-left: 45%">
		<img src="<%=basePath%>/include/images/s.jpg" style="height: 60px;margin-left: 350px;">
		<!-- 登录 -->
		<a id="login" class="login" href="javascript:openLogin();"><img
			src="<%=basePath%>/include/images/login.png" style="height: 60px;"></a>
	
		<!-- 注册 -->
		<a id="regist" class="regist" href="javascript:openRegist();"><img
			src="<%=basePath%>/include/images/regist.png" style="height: 60px;"></a> <a
			href="javascript:openShopLogin();"><img src="<%=basePath%>/include/images/shop.png"
			style="height: 60px;"></a>
	</div>
	<div class="container">

		<!-- 登录层 -->
		<div id="lc1" class="lc1"
			style="width: 280px;height: 280px;left:75.4%;margin-top:-14px;display:none; POSITION:absolute; text-align:center;z-index: 2000;font-size: 25px;">
			<div>
				<img src="<%=basePath%>/include/images/arrow.png" />
			</div>

			<div class="lc">
				<div style="POSITION:absolute;margin-left:260px;margin-top: -20px;">
					<a href="javascript:openLogin();"><img
						src="<%=basePath%>/include/img/little/cancel.png" /></a>
				</div>
				<form id="login_form" method="post" onsubmit="return false">

					<p class="btn-2">为爱电影的人</p>
					<input name="vcode" id="vcode" type="hidden" />
					<table class="btn-3" cellspacing="5" cellpadding="5">
						<tr>
							<td><input name="account" id="account" type="text"
								value="<%=applicantAccount%>" placeholder="&nbsp;&nbsp;账号" style="font-family: 微软雅黑;font-size: 15px;"></td>
						</tr>
						<tr>
							<td><input name="password" id="password" type="password"
								value="<%=applicantPassword%>" placeholder="&nbsp;&nbsp;密码" style="font-family: 微软雅黑;font-size: 15px;"></td>
						</tr>

						<tr>
							<td><input name="code" id="code" type="text"
								placeholder="&nbsp;&nbsp;验证码" onblur="check()"
								onfocus="delData()" style="font-family: 微软雅黑;font-size: 15px;"><span id="Acc"><img
									src="<%=basePath%>/include/img/little/nothing.png" /></span></td>
							<td><img src="ValidateCodeServlet" id="validateCode"
								title="单击换一换" onclick="changeValidateCode()"
								style="cursor: pointer;margin-left: -68px;"></td>
						</tr>
						<tr>
							<td><input checked="checked" name="rememberMe"
								id="rememberMe" type="checkbox" value="true"
								style="margin-left: 40px;"> <label for="RemeberPassword"
								style="color: gray">记住密码</label></td>
						</tr>
						<tr>
							<td><input id="login_submit" type="submit" value="登&nbsp;录">
							</td>
						</tr>
					</table>
					<br>
				</form>
			</div>
		</div>
		<!-- /登录层 -->

		<!-- 注册层 -->
		<div id="rc1" class="rc1"
			style="width: 1000px;height: 500px;margin-left:180px;background-color: white;display:none; POSITION:absolute; text-align:center;z-index: 2000;">
			<div style="POSITION:absolute;margin-left:980px;margin-top: -20px;">
				<a href="javascript:openRegist();"><img
					src="<%=basePath%>/include/img/little/cancel.png" /></a>
			</div>
			<!-- 注册框 开始-->
			<div class="rc1-left" style="width: 499px;height: 100%;float: left">
				<form action="doRegist" id="test" onsubmit="return checkRegist();">
					<h3 style="margin-top: 20px;font-family: 微软雅黑">
						<font size="5px" color="black">新会员注册</font>
					</h3>
					<table cellspacing="5" cellpadding="5"
						style="margin-top: 10px;margin-left: 100px;">
						<tr>
							<td><input id="email" name="email" type="text"
								placeholder="&nbsp;&nbsp;常用邮箱" onblur="isUserNameExist()"
								style="font-family:微软雅黑;height: 50px;width: 300px;font-size: 10px;" /><span
								id="Remail"></span></td>
						</tr>
						
						<tr>
							<td><input id="vregistcode" type="hidden" /><input
								id="result" name="result" type="hidden" /> <input id="registcode" type="text"
								placeholder="&nbsp;&nbsp;邮箱验证码"
								style="font-family:微软雅黑;height: 50px;width: 300px;margin-top: 10px;" />
								<input type="button" value="获取验证码" onclick="Regist(this);"
								style="font-family:微软雅黑;height: 35px;width: 90px;margin-left: -100px;" /><span
								id="Rregistcode" style="margin-left: 7px;"></span></td>
						</tr>
						<tr>
							<td><input id="xm" name="xm" type="text" placeholder="&nbsp;&nbsp;昵称"
								style="font-family:微软雅黑;height: 50px;width: 300px;font-size: 10px;margin-top: 10px;" />
								<span id="s_xm" style="margin-left: 7px;"></span>
							</td>
						</tr>
						<tr>
							<td><input id="registpassword" name="registpassword"
								type="password" placeholder="&nbsp;&nbsp;密码"
								style="font-family:微软雅黑;height: 50px;width: 300px;margin-top: 10px;" /><span
								id="Rregistpassword"></span></td>
						</tr>
						<tr>
							<td><input id="vregistpassword" type="password"
								onblur="checkpassword()" placeholder="&nbsp;&nbsp;确认密码"
								style="font-family:微软雅黑;height: 50px;width: 300px;margin-top: 10px;" /><span
								id="Rvregistpassword"></span></td>
						</tr>
						
						<tr>
							<td>性别： <input id="sex" name="sex" checked="checked"
								value="男" type="checkbox"
								style="height: 18px;width: 18px;margin-top: 20px;" />男 <img
								src="include/img/little/male.png"> &nbsp;&nbsp;&nbsp;&nbsp; <input
								id="sex" name="sex" value="女" type="checkbox"
								style="height: 18px;width: 18px;margin-top: 20px;" />女 <img
								src="include/img/little/female.png">
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="注&nbsp;册"
								style=" font-family:微软雅黑;height: 50px;width: 250px;margin-left:22px;margin-top: 20px;background-color:#0081BC;color:white;border-radius:30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 注册框结束 -->

			<!-- 分界线开始 -->
			<img src="<%=basePath%>/include/images/s.jpg"
				style="height: 400px;width: 1px;margin-top:50px;opacity:0.3;">
			<!-- 分界线结束 -->

			<!-- 登录框开始 -->
			<div class="rc1-right"
				style="width: 499px;height: 100%;float: right;">
				<form id="login2_form" method="post" onsubmit="return false">
					<h3 style="margin-top: 20px;font-family: 微软雅黑">
						<font size="5px" color="black">会员登录</font>
					</h3>
					<input name="vcode2" id="vcode2" type="hidden" />
					<table cellspacing="5" cellpadding="5"
						style="margin-top: 50px;margin-left: 100px;">
						<tr>
							<td><input id="loginemail" name="loginemail"
								value="<%=applicantAccount%>" type="text"
								placeholder="&nbsp;&nbsp;邮箱"
								style="font-family:微软雅黑;height: 50px;width: 300px;font-size: 10px;" /><span
								id="Lloginemail"></span></td>
						</tr>
						<tr>
							<td><input id="loginpassword" name="loginpassword"
								value="<%=applicantPassword%>" type="password"
								placeholder="&nbsp;&nbsp;密码"
								style="font-family:微软雅黑;height: 50px;width: 300px;margin-top: 10px;" /><span
								id="Lloginpassword"></span></td>
						</tr>
						<tr>
							<td><input id="code2" name="code2" type="text"
								placeholder="&nbsp;&nbsp;验证码" onblur="check2()"
								onfocus="delData2()"
								style=" font-family:微软雅黑;height: 50px;width: 300px;margin-top: 10px;"><span
								id="Bcc" style="height:50px;width: 100px;background-color: red;"></span>

							</td>
							<td><img src="ValidateCodeServlet" id="validateCode2"
								title="单击换一换" onclick="changeValidateCode2()"
								style="cursor: pointer;padding-right: 400px;"></td>
						</tr>
						<tr>
							<td><input checked="checked" name="rememberMe2"
								id="rememberMe2" type="checkbox" value="true"
								style="height: 18px;width: 18px;margin-top: 20px;margin-top: 10px;">
								<label for="RemeberPassword" style="color: gray">记住密码</label></td>
						</tr>
						<tr>
							<td><input id="login2_submit" type="submit" value="登&nbsp;录"
								style=" font-family:微软雅黑;height: 50px;width: 250px;margin-left:22px;margin-top: 20px;background-color:#0081BC;color:white;border-radius:30px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 登录框结束 -->
		</div>
		<!-- /注册层 -->

		<div style="width: 100%;height: 500px;">
			<script type="text/javascript" src="<%=basePath%>/include/js/jquery.min.js"></script>

			<!-- 图片轮播层开始 -->
			<div class="win">
				<div class="box">
					<div style="left:0">
						<a href="javascript:move()"><img src="<%=basePath%>/include/images/1.jpg" /></a>
					</div>
					<div>
						<a href="javascript:move()"><img src="<%=basePath%>/include/images/2.jpg" /></a>
					</div>
					<div>
						<a href="javascript:move()"><img src="<%=basePath%>/include/images/3.jpg" /></a>
					</div>
					<div>
						<a href="javascript:move()"><img src="<%=basePath%>/include/images/4.jpg" /></a>
					</div>
					<div>
						<a href="javascript:move()"><img src="<%=basePath%>/include/images/5.jpg" /></a>
					</div>
				</div>
				<div class="title">
					<a href="javascript:;" style="color:#fff">1</a> <a
						href="javascript:;">2</a> <a href="javascript:;">3</a> <a
						href="javascript:;">4</a> <a href="javascript:;">5</a>
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
							<a class="xuangou" href="#title" onclick="javascript:openShopLogin();"><img
								src="<%=basePath%>/include/images/movies/xuangou.png"
								style="padding-top: 20px;margin-left: 10px;"></a>
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
							<a class="xuangou" href="#"><img
								src="<%=basePath%>/include/images/movies/xuangou.png"
								style="padding-top: 20px;margin-left: 10px;"></a>
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
	</div>
</body>
	<script type="text/javascript">
			$(document).ready(function () {
			    $('#login_form input').keydown(function (e) {
			        if (e.keyCode == 13)
			        {
			            checkUserName();//$('#login_submit').click();
			        }
			    });
			    $("#login_submit").click(checkUserName);  
			});
			function checkUserName()//登录前，校验用户信息
			{    
			      var a=$('#account').val();
			      var b=$('#password').val();
			      var vcode = $('#vcode').val();
			      var code = $('#code').val();
			      var rememberMe = $('#rememberMe').val();
			      if(a==""){alert("请输入帐号");return;      }			      
			      var re=/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
			      if(!re.test(a)){alert("邮箱格式错误");return;     }
			      if(b==""||b==undefined){alert("请输入登录密码");return;}
			      if(code == ""){alert("请输入验证码");return;      }
			      if(code != vcode){alert("验证码不正确");return;      }
			      $.ajax({
			            url : base+"user/doLogin",
			            data :{"uid":a,"pwd":b,"rememberMe":rememberMe},
			            type:"POST",
			            success : function (res) {
			                  if (res.ok) {
			                        window.location.href=base+res.msg;
			                  }else {alert(res.msg);             }
			                  return false;
			            },
			            error : function(res) {alert("系统错误！");      }
			      });
			}
		</script>
		<script type="text/javascript">
			$(document).ready(function () {
			    $('#login2_form input').keydown(function (e) {
			        if (e.keyCode == 13)
			        {
			            checkUserName();//$('#login_submit').click();
			        }
			    });
			    $("#login2_submit").click(checkUserName2);  
			});
			function checkUserName2()//登录前，校验用户信息
			{    
			      var a=$('#loginemail').val();
			      var b=$('#loginpassword').val();
			      var vcode = $('#vcode2').val();
			      var code = $('#code2').val();
			      var rememberMe = $('#rememberMe2').val();
			      if(a==""){alert("请输入帐号");return;      }			      
			      var re=/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
			      if(!re.test(a)){alert("邮箱格式错误");return;     }
			      if(b==""||b==undefined){alert("请输入登录密码");return;}
			      if(code == ""){alert("请输入验证码");return;      }
			      if(code != vcode){alert("验证码不正确");return;      }
			      $.ajax({
			            url : base+"user/doLogin",
			            data :{"uid":a,"pwd":b,"rememberMe":rememberMe},
			            type:"POST",
			            success : function (res) {
			                  if (res.ok) {
			                        window.location.href=base+res.msg;
			                  }else {alert(res.msg);             }
			                  return false;
			            },
			            error : function(res) {alert("系统错误！");      }
			      });
			}
		</script>
		<script type="text/javascript">
			/*<!-- 检查注册规则开始 -->*/
			function checkRegist() {
				var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
				var email = document.getElementById("email");
				var check = document.getElementById("result").value;
				var xm = document.getElementById("xm");
				var vregistcode = document.getElementById("vregistcode");
				var registcode = document.getElementById("registcode");
				var registpassword = document.getElementById("registpassword");
				var vregistpassword = document.getElementById("vregistpassword");
				var sex = document.getElementById("sex");
				if (!pattern.test(email.value)) {
					document.getElementById("Remail").innerHTML = "格式错误";
					email.focus();
					return false;
				} else if (email.value == "") {
					document.getElementById("Remail").innerHTML = "不能为空";
					email.focus();
					return false;
				} else {
					document.getElementById("Remail").innerHTML = "";
				}
				if (Number(check) == 1) {
					document.getElementById("Remail").innerHTML = "用户已存在";
					email.focus();
					return false;
				} else {
					document.getElementById("Remail").innerHTML = "";
				}
				if (registcode.value == "") {
					document.getElementById("Rregistcode").innerHTML = "不能为空";
					registcode.focus();
					return false;
				}
				
				if (String(registcode.value) != String(vregistcode.value)) {
					document.getElementById("Rregistcode").innerHTML = "验证码错误";
					registcode.focus();
					return false;
				} else {
					document.getElementById("Rregistcode").innerHTML = "";
				}
				
				if (xm.value == "") {
					document.getElementById("s_xm").innerHTML = "不能为空";
					xm.focus();
					return false;
				}
				
				if (registpassword.value == "") {
					document.getElementById("Rregistpassword").innerHTML = "不能为空";
					registpassword.focus();
					return false;
				} else {
					document.getElementById("Rregistpassword").innerHTML = "";
				}
				if (vregistpassword.value == "") {
					document.getElementById("Rvregistpassword").innerHTML = "不能为空";
					vregistpassword.focus();
					return false;
				} else {
					document.getElementById("Rvregistpassword").innerHTML = "";
				}	
			}		
		</script>
</html>
