let result;
let resofto;
let niname;
let nitype;
let check_arr=new Array;
let linum;
let linum2;
let to;
let from;
let res;
let from1;
let to1;


// 生成emoji
function renderEmoji(){
    let emos = getEmojiList()[0];
    let html = '<ul class="clearfix">';
        for (let j= 0; j<emos.length; j++){
            let emo = emos[j];
            let data = 'data:image/png;base64,' + emo[2];
            if (j % 20 == 0) {
                html += '';
            } else {
                html += '';
            }
            html += '<img style="display: inline;vertical-align: middle;" src="' + data + '"  unicode16="' + emo[1] + '"  class="emoji-icon"  /></li>';
        }
    $(".emoji-cont").append(html);
}

// 通过该方法。可以直接把emoji在输入框中显示出来。
function parse(arg){  
    if (typeof ioNull !='undefined') {  
        return  ioNull.emoji.parse(arg);      
    }  
    return '';  
};


// socket
let CSsocket=()=>{
	if('WebSocket' in window){
		//let url_cs="ws://10.138.126.61:8080/LawyerConsultationSystem/cssocket/"+name;
		let url_cs="ws://localhost:8080/LawyerConsultationSystem/cssocket/"+name;
	    cssocket = new WebSocket(url_cs);
	    console.log("link cs success");
	}else{
	    alert('Not support websocket');
	}
    
  // 连接发生错误的回调方法
	cssocket.onerror = function(){
	    setMessageInnerHTML("error");
	};
	 
	// 连接成功建立的回调方法
	cssocket.onopen = function(event){
		// 接收到消息的回调方法
		cssocket.onmessage = function(event){
		      setMessageInnerHTML(event.data);
		}
	}
	 
	// 连接关闭的回调方法
	cssocket.onclose = function(){
	    //
	}
	 
	// 发送消息
	let CSsend=()=>{
		//
	}
}

if(name==='null'&&type==='null'){
	$("#header").html("<div class='law-header'>律师咨询平台</div><a href='./html/login.jsp' class='a-login'>登录</a><a href='./html/signup.jsp' class='a-signup'>注册</a>");
}
else{
	$("#header").html("<div class='law-header'>律师咨询平台</div><div class='welcome'>欢迎"+type+" "+name+"</div>");
	$("#header").html($("#header").html()+"<div id='logout'>退出登录</div>");
	if(type==='cs'){
		CSsocket();
		$("#cs-info").css('display','none');
		$("#user-comein").css('display','');
	}
}

// 开启websocket
// 三种分别为信息socket、在线客服socket、来信用户socket
if('WebSocket' in window){
	if(name==='null'&&type==='null'){
		niname='临时用户'+Math.ceil(Math.random()*10000);
		nitype='user';
	}
	else{
		niname=name;
		nitype=type;
	}
	//let url_web="ws://10.138.126.61:8080/LawyerConsultationSystem/websocket/"+niname+"/"+nitype;
	let url_web="ws://localhost:8080/LawyerConsultationSystem/websocket/"+niname+"/"+nitype;
    websocket = new WebSocket(url_web);
    //let url_getcs="ws://10.138.126.61:8080/LawyerConsultationSystem/getcssocket";
    let url_getcs="ws://localhost:8080/LawyerConsultationSystem/getcssocket";
    getcssocket = new WebSocket(url_getcs);
    //let url_getuser="ws://10.138.126.61:8080/LawyerConsultationSystem/getusersocket/"+niname+"/"+nitype;
	let url_getuser="ws://localhost:8080/LawyerConsultationSystem/getusersocket/"+niname+"/"+nitype;
    getusersocket = new WebSocket(url_getuser);
    console.log("link success");
}else{
    alert('Not support websocket');
}

// 连接发生错误的回调方法
websocket.onerror = function(){
    setMessageInnerHTML("error");
};
getcssocket.onerror = function(){
    setMessageInnerHTML("error");
};
getusersocket.onerror = function(){
    setMessageInnerHTML("error");
};
 
// 连接成功建立的回调方法
websocket.onopen = (event)=>{
	console.log("websocket success");
	websocket.onmessage = (event)=>{
		console.log("收到新消息"+event.data);
	    setMessageInnerHTML(event.data);
	}
}
getcssocket.onopen = (event)=>{
	console.log("cssocket success");
	getcssocket.onmessage = (event)=>{
		$("#cs-list")[0].innerHTML=event.data;
		$('#cs-list li').click(function(){
			result=$('#cs-list li').index(this);
			resofto=$('#cs-list li:eq('+result+')').attr('value');
			$('.whochat').html('现在正在和'+resofto+'对话');
			for(let i=0;i<$('#text-info').children().length;i++){
				if($('#text-info').children()[i].id===resofto){
					$("#"+$('#text-info').children()[i].id).css('display','');
				}
				else if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==""){
					$("#"+$('#text-info').children()[i].id).css('display','none');
				}
			}
		});
		if(nitype==='user'){
			/*
			 * setTimeout("linum=$('#cs-list li').length;"+ "for(let i=0;i<linum;i++){" +
			 * "let classname=$(\"#cs-list li:eq(\"+i+\")\").html();"+
			 * "console.log(classname);"+ "if($(\"#\"+classname+\"\").length<=0){"+
			 * "$('#text-info').html($('#text-info').html()+\"<div
			 * id=\"+classname+\"
			 * style='display:none;overflow:auto;height:460px;'></div>\");}"+
			 * "}",100);
			 */
			linum=$('#cs-list li').length;
			for(let i=0;i<linum;i++){
				let classname=$("#cs-list li:eq("+i+")").html();
				console.log(classname);
				if($("#"+classname+"").length<=0){
					$('#text-info').html($('#text-info').html()+"<div id="+classname+" style='display:none;overflow:auto;height:460px;'></div>");
				}
			}
		}
	}
}
getusersocket.onopen = (event)=>{
	console.log("usersocket success");
	getusersocket.onmessage = (event)=>{
		console.log(event.data);
		$("#user-list")[0].innerHTML+=event.data;
		$('#user-list li').click(function(){
			result=$('#user-list li').index(this);
			resofto=$('#user-list li:eq('+result+')').attr('value');
			$('.whochat').html('现在正在和'+resofto+'对话');
			for(let i=0;i<$('#text-info').children().length;i++){
				if($('#text-info').children()[i].id===resofto){
					$("#"+$('#text-info').children()[i].id).css('display','');
				}
				else if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==""){
					$("#"+$('#text-info').children()[i].id).css('display','none');
				}
			}
		});
		if(nitype==='cs'){
			/*
			 * setTimeout("linum2=$('#user-list li').length;"+ "for(let i=0;i<linum2;i++){" +
			 * "let classname=$(\"#user-list li:eq(\"+i+\")\").html();"+
			 * "console.log(classname);"+ "if($(\"#\"+classname+\"\").length<=0){"+
			 * "$('#text-info').html($('#text-info').html()+\"<div
			 * id=\"+classname+\"
			 * style='display:none;overflow:auto;height:460px;'></div>\");}"+
			 * "}",100);
			 */
	    	linum2=$('#user-list li').length;
			for(let i=0;i<linum2;i++){
				let classname=$("#user-list li:eq("+i+")").html();
				console.log(classname);
				if($("#"+classname+"").length<=0){
					$('#text-info').html($('#text-info').html()+"<div id="+classname+" style='display:none;overflow:auto;height:460px;'></div>");
				}
			}
	    }
	}
}

 
// 连接关闭的回调方法
websocket.onclose = function(){
	console.log("websocket close");
}
getcssocket.onclose = function(){
	console.log("websocket close");
}
getusersocket.onclose = function(){
	console.log("websocket close");
}
 
// 将消息显示在网页上
function setMessageInnerHTML(innerHTML){
	let patt=/(?<=:[0-9]{1}[0-9]{1} ).*?(?=<)/;
	let res=patt.exec(innerHTML);
	from1=res[0];
	if(from1===niname){
		to1=resofto;
		if($("#"+to1).length>0){
			$("#"+to1)[0].innerHTML+=innerHTML;
		}
	}
	else{
		if($("#"+from1).length>0){
			$("#"+from1)[0].innerHTML+=innerHTML;
		}
	}
	
	
}
 
 
// 发送消息
function send(){
	let myDate = new Date();
	let date=myDate.toLocaleString();
	let info=$("#info").html();
	let message="<p>"+date+" "+niname+"</p>"+"<p>"+info+"</p>";
	let jsonString ="{'message':'"+message+"','from':'"+niname+"','to':'"+resofto+"'}";
    websocket.send(jsonString);
    //错误地点
    if(check_arr.indexOf(resofto)===-1){
    	check_arr.push(resofto);
    	getusersocket.send(jsonString);
    }
    
}



// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
$(window).bind('beforeunload', function(){ 
	websocket.close();
    cssocket.close();
    getcssocket.close();
    getusersocket.close();
});


	$(document).ready(()=>{
		// wrapper高度计算
		let height=$(window).height();
		$('.wrapper').css('height',height-80);
		
		/*
		 * setInterval("$('#cs-list li').click(function(){" +
		 * "result=$('#cs-list li').index(this);" + "resofto=$('#cs-list
		 * li:eq('+result+')').attr('value');" +
		 * "$('.whochat').html('现在正在和'+resofto+'对话');"+ "for(let i=0;i<$('#text-info').children().length;i++){"+
		 * "if($('#text-info').children()[i].id===resofto){"+
		 * "$(\"#\"+$('#text-info').children()[i].id).css('display','')}"+ "else
		 * if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==\"\"){"+
		 * "$(\"#\"+$('#text-info').children()[i].id).css('display','none')}}"+
		 * "});",200);
		 */
		/*
		 * $('#cs-list li').click(function(){ result=$('#cs-list
		 * li').index(this); resofto=$('#cs-list
		 * li:eq('+result+')').attr('value');
		 * $('.whochat').html('现在正在和'+resofto+'对话'); for(let i=0;i<$('#text-info').children().length;i++){
		 * if($('#text-info').children()[i].id===resofto){
		 * $("#"+$('#text-info').children()[i].id).css('display',''); } else
		 * if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==""){
		 * $("#"+$('#text-info').children()[i].id).css('display','none'); } }
		 * });
		 */
		/*
		 * setInterval("$('#user-list li').click(function(){" +
		 * "result=$('#user-list li').index(this);" + "resofto=$('#user-list
		 * li:eq('+result+')').attr('value');" +
		 * "$('.whochat').html('现在正在和'+resofto+'对话');"+ "for(let i=0;i<$('#text-info').children().length;i++){"+
		 * "if($('#text-info').children()[i].id===resofto){"+
		 * "$(\"#\"+$('#text-info').children()[i].id).css('display','')}"+ "else
		 * if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==\"\"){"+
		 * "$(\"#\"+$('#text-info').children()[i].id).css('display','none')}}"+
		 * "});",200);
		 */
		/*
		 * $('#user-list li').click(function(){ result=$('#user-list
		 * li').index(this); resofto=$('#user-list
		 * li:eq('+result+')').attr('value');
		 * $('.whochat').html('现在正在和'+resofto+'对话'); for(let i=0;i<$('#text-info').children().length;i++){
		 * if($('#text-info').children()[i].id===resofto){
		 * $("#"+$('#text-info').children()[i].id).css('display',''); } else
		 * if($('#text-info').children()[i].id!==resofto&&$('#text-info').children()[i].id!==""){
		 * $("#"+$('#text-info').children()[i].id).css('display','none'); } }
		 * });
		 */
				
		/*
		 * $('#cs-list li').click(function(){ result=$('#cs-list
		 * li').index(this); res=$('#cs-list li:eq('+result+')').attr('value');
		 * console.log(res); });
		 */
		
		
		$("#btn-submit").click(()=>{
			send();
		});
		
		// 生成emoji 并把emoji表情加载到emoji-cont中进行显示。
		$("#btn").click(function(){
			$(".emoji-cont").html("");
			renderEmoji();
			$(".emoji-cont").toggle();
		    $(".emoji-icon").each(function(k,v){
		        $(v).click(function(){
		                let code=$(this).attr("unicode16");
		                // console.log(parse(code));
		                $("#info").html($("#info").html()+parse("&#"+parseInt(code,16)+";"));
		            });
		        });
		    
		});

		$(".clearfix li").each((k,v)=>{
			$(v).click(function(){
	            let code=$(this).attr("unicode16");
	            // console.log(parse(code));
			            $("#info").html($("#info").html()+parse("&#"+parseInt(code,16)+";"));
			   	});
			});
		});
		
		$("#logout").click(()=>{
			let ajax=$.ajax({
				type:'POST',
				url:'/../LawyerConsultationSystem/login.do?method=logout',
				success:()=>{
					name=null;
					type=null;
					location.reload();
				}
			});
			
		});
	  

