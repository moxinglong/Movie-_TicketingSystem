function NotCheck(e){
		$("#squaredThree"+e).attr("checked",false);
		$("#squaredThree"+e).attr("disabled",true);
		$("#squaredThree"+e).css("background-color","yellow");
	}
function check(){
	var obj=document.getElementsByName("check");
	var num=0;
	for (i=0;i<obj.length ;i++ ){
		if(obj[i].checked ){
	    	num+=1;
	    }
	}
	if(num==0){
	    	alert("必须选择一个座位");
	    	return false;
	}
}
function cktest(elm){
	var obj=document.getElementsByName("check");
	var price = document.getElementById("price").value;
	var obk;
	var num=0;
	var j = 0;
	for (i=0;i<obj.length ;i++ ){
		if(obj[i].checked ){
	    	num+=1;
	    	document.getElementById("allprice").value = parseInt(price*num);
	    	document.getElementById("num").value = parseInt(num);
	    	j = i+1;
	    }
	    if(obj[elm-1].checked){
	    	document.getElementById("seat1").value = "";
		    document.getElementById("seat2").value = "";
		    document.getElementById("seat3").value = "";
	    	document.getElementById("seat4").value = "";
	    	
	    	document.getElementById("sno1").value = "";
		    document.getElementById("sno2").value = "";
		    document.getElementById("sno3").value = "";
	    	document.getElementById("sno4").value = "";
	    	
	    	obk = document.getElementsByName("check");
	    	var e = 4;
	    	var f = 0;
	    		for (k=0;k<obk.length ;k++ ){
					if(obj[k].checked ){
						if(e==1){
							f = k+1;
							document.getElementById("seat4").value = document.getElementById("xuanzhong"+f).value;
							document.getElementById("sno4").value = k+1;
						}
						if(e==2){
							f = k+1;
							document.getElementById("seat3").value = document.getElementById("xuanzhong"+f).value;
							document.getElementById("sno3").value = k+1;
							e--;
						}
						if(e==3){
							f = k+1;
							document.getElementById("seat2").value = document.getElementById("xuanzhong"+f).value;
							document.getElementById("sno2").value = k+1;
							e--;
						}
						if(e==4){
							f = k+1;
							document.getElementById("seat1").value = document.getElementById("xuanzhong"+f).value;
							document.getElementById("sno1").value = k+1;
							e--;
						}
				    }
			}
	    }else{
		    document.getElementById("seat1").value = "";
		    document.getElementById("seat2").value = "";
		    document.getElementById("seat3").value = "";
	    	document.getElementById("seat4").value = "";
	    	
	    	document.getElementById("sno1").value = "";
		    document.getElementById("sno2").value = "";
		    document.getElementById("sno3").value = "";
	    	document.getElementById("sno4").value = "";
	    	
	    	obk = document.getElementsByName("check");
	    	var c = 4;
	    	var d = 0;
	    		for (k=0;k<obk.length ;k++ ){
					if(obj[k].checked ){
						if(c==1){
							d = k+1;
							document.getElementById("seat4").value = document.getElementById("xuanzhong"+d).value;
							document.getElementById("sno4").value = k+1;
						}
						if(c==2){
							d = k+1;
							document.getElementById("seat3").value = document.getElementById("xuanzhong"+d).value;
							document.getElementById("sno3").value = k+1;
							c--;
						}
						if(c==3){
							d = k+1;
							document.getElementById("seat2").value = document.getElementById("xuanzhong"+d).value;
							document.getElementById("sno2").value = k+1;
							c--;
						}
						if(c==4){
							d = k+1;
							document.getElementById("seat1").value = document.getElementById("xuanzhong"+d).value;
							document.getElementById("sno1").value = k+1;
							c--;
						}
				    }
			}
	    }
	}    
	if(num==4){
		alert("最多选择4个座位");
		for (k=0;k<obk.length ;k++ ){
			if(obj[k].checked ){	
			}else{
				obj[k].disabled = "disabled";
			}
		}
	}else{
		for (k=0;k<obk.length ;k++ ){
			obj[k].disabled = "";
		}
	}
	if(num==0){
		document.getElementById("num").value = 0;
		document.getElementById("allprice").value = "";
	}
}