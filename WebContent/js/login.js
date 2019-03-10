$(document).ready(()=>{
	// 背景图片设置
	let num=rd(5,7);
	$('body').css("background-image","url(../img/background-"+num+".png)");
	$("#btn-login").click(()=>{
		login();
	});
});

let rd=(n,m)=>{
	let c = m-n+1;	
	return Math.floor(Math.random() * c + n);
}

let login=()=>{
	
	let url="/../LawyerConsultationSystem/login.do";
	let type=$("input[type='radio']:checked").val();
	if(type==='用户'){
		url+="?method=userlogin";
		let res=$.ajax({
			type: "POST",
			url:url,
			data:{username:$("#name").val(),password:$("#pass").val()},
			dataType: "json",
			success:(data)=>{
				if(data === "success"){
					window.open('../',"_self");
				}
				else if(data === "密码错误"){
					$("#error-message").css('display','block');
					$("#error-message").html("密码错误");
				}
				else if(data === "用户名不存在"){
					$("#error-message").css('display','block');
					$("#error-message").html("用户名不存在，注册一个吧！");
				}
				else if(data === "服务器错误"){
					$("#error-message").css('display','block');
					$("#error-message").html("服务器炸了呢1551");
				}
			},
		});
	}
	else if(type==='客服'){
		url+="?method=cslogin";
		let res=$.ajax({
			type: "POST",
			url:url,
			data:{csname:$("#name").val(),password:$("#pass").val()},
			dataType: "json",
			success:(data)=>{
				if(data === "success"){
					window.open('../',"_self");
				}
				else if(data === "密码错误"){
					$("#error-message").css('display','block');
					$("#error-message").html("密码错误");
				}
				else if(data === "用户名不存在"){
					$("#error-message").css('display','block');
					$("#error-message").html("用户名不存在，注册一个吧！");
				}
				else if(data === "服务器错误"){
					$("#error-message").css('display','block');
					$("#error-message").html("服务器炸了呢1551");
				}
			},
		});
	}
}

