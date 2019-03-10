$(document).ready(()=>{
	// 背景图片设置
	let num=rd(5,7);
	$('body').css("background-image","url(../img/background-"+num+".png)");
	$("#btn-signup").click(()=>{
		signup();
	});
	$("#name").blur(()=>{
		checksignup();
	});
});

let rd=(n,m)=>{
	let c = m-n+1;	
	return Math.floor(Math.random() * c + n);
}

let signup=()=>{
	let url="/../LawyerConsultationSystem/signup.do?method=usersignup";
	let res=$.ajax({
		type: "POST",
		url:url,
		data:{username:$("#name").val(),password:$("#pass").val()},
		dataType: "json",
		success:(data)=>{
			if(data === "success"){
				alert("注册成功");
				window.open('../html/login.jsp',"_self");
			}
			else if(data === "存在用户名"){
				$("#error-message").css('display','block');
				$("#error-message").html("存在用户名");
			}
			else if(data === "服务器错误"){
				$("#error-message").css('display','block');
				$("#error-message").html("服务器炸了呢1551");
			}
		},
	});
}
let checksignup=()=>{
	let url="/../LawyerConsultationSystem/signup.do?method=checksignup";
	let res=$.ajax({
		type: "POST",
		url:url,
		data:{username:$("#name").val()},
		dataType: "json",
		success:(data)=>{
			if(data === "yes"){
				$("#error-message").css('display','block');
				$("#error-message").html("存在用户名");
			}
			else if(data === "no"){
				$("#error-message").css('display','none');
			}
		},
	});
}

