<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="icon" href="../include/img/exam.jpg" type="image/x-icon"/>
    <title>拒绝访问</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>/include/css/404.css" rel="stylesheet" type="text/css" media="all" />
  </head>
  
  <body>
    <div class="container demo-2">
	<div class="content">
        <div id="large-header" class="large-header">
			<h1 class="header-w3ls">您没有权限访问此页面</h1>
			<p class="w3-agileits1">抱歉!</p>
            <canvas id="demo-canvas"></canvas>
			<img src="<%=basePath%>/include/img/owl.gif" alt="flashy" class="w3l">
            <h2 class="main-title"><span>403</span></h2>
			<p class="copyright" style="margin-left: 80px;" >Copyright © 2017 莫兴龙  All Rights Reserved.</p>
        </div>
	</div>
	</div>
	<!-- js files -->
	<script src="<%=basePath%>/include/js/rAF.js"></script>
	<script src="<%=basePath%>/include/js/demo-2.js"></script>
	<!-- /js files -->
	<script type="text/javascript">
	    setTimeout(reDo, 2000);
	    function reDo(){
	        top.location.href = "<%=basePath%>";
	    }
	</script>
  </body>
</html>
