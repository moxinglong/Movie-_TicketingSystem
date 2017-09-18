
function NotAction(){
				alert("该影片尚未上映，请您选择其他影片进行观赏");
			}
function openLogin() {
		var div = document.getElementById("lc1");
		var div1 = document.getElementById("rc1");
		//点击按钮弹出div 在点击按钮关闭该div 
		if (div.style.display == "none") {
			div1.style.display = "none";
			div.style.display = "block";
		} else {
			div.style.display = "none";
		}
	}


	function openRegist() {
		var div = document.getElementById("lc1");
		var div1 = document.getElementById("rc1");
		//点击按钮弹出div 在点击按钮关闭该div 
		if (div1.style.display == "none") {
			div.style.display = "none";
			div1.style.display = "block";
		} else {
			div1.style.display = "none";
		}
	}


	function openShopLogin() {
		var div = document.getElementById("lc1");
		var div1 = document.getElementById("rc1");
		alert("请您先登录");
		div1.style.display = "none";
		div.style.display = "block";
	}



	function changeValidateCode() {
		document.getElementById("validateCode").src = "ValidateCodeServlet?rand=" + Math.random();
	}
	function check() {
		var code = document.getElementById("code").value;
		$.ajax({
			type : "post",
			url : "check",
			data : "",
			success:function(msg) {
				document.getElementById("vcode").value = String(msg);
				if (String(msg) == String(code)) {
					document.getElementById("Acc").innerHTML = "<img src='include/img/little/right.png'/>";
				} else {
					document.getElementById("Acc").innerHTML = "<img src='include/img/little/wrong.png'/>";
				}
			}
		});
	}
	function delData() {
		document.getElementById("code").value = "";
	}
	function validate() {
		var account = document.getElementById("account");
		var password = document.getElementById("password");

		var code = document.getElementById("code").value;
		var vcode = document.getElementById("vcode").value;

		var code1 = document.getElementById("code");

		if (account.value == "") {
			alert("账号不能为空");
			account.focus();
			return false;
		}
		if (password.value == "") {
			alert("密码不能为空");
			password.focus();
			return false;
		}
		if (code1.value == "") {
			alert("验证码不能为空");
			code1.focus();
			return false;
		}
		if (String(code) != String(vcode)) {
			alert("验证码不正确");
			changeValidateCode();
			code1.focus();
			return false;
		}
	}

/*<!-- 处理注册页面登录 -->*/


	function changeValidateCode2() {
		document.getElementById("validateCode2").src = "ValidateCodeServlet?rand=" + Math.random();
	}
	function check2() {
		var code2 = document.getElementById("code2").value;
		$.ajax({
			type : "post",
			url : "check",
			data : "",
			success:function(msg){
				document.getElementById("vcode2").value = String(msg);
				if (String(msg) == String(code2)) {
					document.getElementById("Bcc").innerHTML = "<img src='include/img/little/right.png'/>";
				} else {
					document.getElementById("Bcc").innerHTML = "<img src='include/img/little/wrong.png'/>";
				}
			}
		});
	}
	function delData2() {
		document.getElementById("code2").value = "";
	}
	function checkLogin2() {
		var loginemail = document.getElementById("loginemail");
		var loginpassword = document.getElementById("loginpassword");

		var code3 = document.getElementById("code2").value;
		var vcode2 = document.getElementById("vcode2").value;

		var code2 = document.getElementById("code2");

		if (loginemail.value == "") {
			document.getElementById("Lloginemail").innerHTML = "不能为空";
			loginemail.focus();
			return false;
		} else {
			document.getElementById("Lloginemail").innerHTML = "";
		}
		if (loginpassword.value == "") {
			document.getElementById("Lloginpassword").innerHTML = "不能为空";
			loginpassword.focus();
			return false;
		} else {
			document.getElementById("Lloginpassword").innerHTML = "";
		}
		if (code2.value == "") {
			document.getElementById("Bcc").innerHTML = "不能为空";
			code2.focus();
			return false;
		} else {
			document.getElementById("Bcc").innerHTML = "";
		}
		if (String(code3) != String(vcode2)) {
			code2.focus();
			return false;
		}
	}


/*<!-- 获取注册码开始 -->*/

	function Regist(obj) {
		var email = document.getElementById("email").value;
		if (email == "") {
			alert("邮箱不能为空");
		} else {
			$.ajax({
				type : "post",
				url : "Regist",
				data : "email=" + email,
				success:function(msg){
					document.getElementById("vregistcode").value = String(msg);
				}
			});
			var obj = $(obj);
			obj.attr("disabled", "disabled");
			var time = 30;
			var set = setInterval(function() {
				obj.val(--time + "s后可点击");
			}, 1000);
			setTimeout(function() {
				obj.attr("disabled", false).val("获取验证码");
				clearInterval(set);
			}, 30000);
		}
	}

/*<!-- 获取注册码结束 -->*/


	function move() {
		scrollTo(0, 650);
	}



/*<!-- 检查用户是否存在 -->*/

	function isUserNameExist() {
		var email = document.getElementById("email").value;
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (email != "") {
			$.ajax({
				type : "post",
				url :  base+"/user/queryByEmail",
				data : "email=" + email,
				success:function(msg){
					if (String(msg) == "true") {
						document.getElementById("Remail").innerHTML = "用户已存在";
						document.getElementById("result").value = msg;
					} else if (!pattern.test(email)) {
						document.getElementById("Remail").innerHTML = "格式错误";
					} else {
						document.getElementById("Remail").innerHTML = "用户名可用";
						document.getElementById("result").value = msg;
					}
				}
			});
		} else {
			document.getElementById("Remail").innerHTML = "不能为空";
		}
	}

/*<!-- 检查两次输入的密码是否一致 -->*/

	function checkpassword() {
		var registpassword = document.getElementById("registpassword").value;
		var vregistpassword = document.getElementById("vregistpassword").value;
		if (vregistpassword != registpassword) {
			document.getElementById("Rvregistpassword").innerHTML = "密码不一致";
			document.getElementById("vregistpassword").value = "";
		} else {
			document.getElementById("Rvregistpassword").innerHTML = "<img src='include/img/little/right.png'>";
		}
	}

/*<!-- 检查注册规则结束 -->*/


	function changecolor(e) {
		var i;
		for (i = 1; i <= 8; i++) {
			if (Number(e) == i) {
				document.getElementById("a" + i).style.color = "black";
			} else {
				document.getElementById("a" + i).style.color = "blue";
			}
		}
	}


	function openmovieType(e) {
		var i;
		for (i = 1; i <= 8; i++) {
			if (e == i) {
				document.getElementById("t" + i).style.display = "";
			} else {
				document.getElementById("t" + i).style.display = "none";
			}
		}
	}
$(document).ready(function() {
			
					$('#test').find('input[type=checkbox]').bind('click', function() {
			
						$('#test').find('input[type=checkbox]').not(this).attr("checked", false);
			
					});
			
				});

$(function() {
					var win = $(".win");
					var links = $(".title a");
					var float = $(".float");
					var divs = $(".box div");
					var num1 = 0; //??±
					var num2 = 0;
					win.hover(function() {
						$(".leftB,.rightB").css("display", "block");
					}, function() {
						$(".leftB,.rightB").css("display", "none");
					});
					$(".leftB").click(function() {
						divs.finish();
						float.stop(true);
						var temp = num1;
						num1--;
						if (num1 == -1) {
							num1 = 4;
						}
						divs.eq(num1).css("left", 1365).animate({
							left : 0
						});
						divs.eq(temp).animate({
							left : -1365
						});
						links.css("color", "");
						float.animate({
							left : links.eq(num1).position().left
						})
						links.eq(num1).css("color", "white");
					});
					$(".rightB").click(function() {
						divs.finish();
						float.stop(true);
						var temp = num1;
						num1++;
						if (num1 == 5) {
							num1 = 0;
						}
						divs.eq(num1).css("left", -1365).animate({
							left : 0
						});
						divs.eq(temp).animate({
							left : 1365
						});
						links.css("color", "");
						float.animate({
							left : links.eq(num1).position().left
						})
						links.eq(num1).css("color", "white");
					});
					links.hover(function() {
						//
						divs.finish();
						float.stop(true);
						links.css("color", "");
						var that = $(this);
						var lefts = $(this).position().left;
						float.animate({
							left : lefts
						}, function() {
							that.css("color", "white");
						})
						//
						//?仯
						var index = $(this).index(".title a");
						num2 = index;
						if (num1 == num2) {
							return;
						} else if (num1 < num2) {
							divs.eq(num2).css("left", 1365).animate({
								left : 0
							});
							divs.eq(num1).animate({
								left : -1365
							});
						} else if (num1 > num2) {
							divs.eq(num2).css("left", -1365).animate({
								left : 0
							});
							divs.eq(num1).animate({
								left : 1365
							});
						}
						num1 = num2;
						num2 = "";
					}, function() {})
				})
$(document).ready(function() {
				var slideShow = $(".win"), //获取最外层框架的名称
					div = slideShow.find(".box"),
					showNumber = slideShow.find(".rightB"), //获取按钮
					oneWidth = slideShow.find("div div").eq(0).width(); //获取每个图片的宽度
				var timer = null; //定时器返回值，主要用于关闭定时器
				var iNow = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0			        
		
		
				/*定时自动轮播图片代码开始*/
				function autoPlay() {
					timer = setInterval(function() { //打开定时器
						iNow++; //让图片的索引值次序加1，这样就可以实现顺序轮播图片
						if (iNow > showNumber.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
							iNow = 0;
						}
						showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
					}, 3000); //3000为轮播的时间
				}
				autoPlay();
				/*定时自动轮播图片代码结束*/
		
				/*鼠标悬浮图片停止轮播代码开始*/
				slideShow.hover(
					function() {
						clearInterval(timer);
					}, function() {
						timer = setInterval(function() { //打开定时器
							iNow++; //让图片的索引值次序加1，这样就可以实现顺序轮播图片
							if (iNow > showNumber.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
								iNow = 0;
							}
							showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
						}, 3000); //2000为轮播的时间
					}
				);
			/*鼠标悬浮图片停止轮播代码结束*/
			})