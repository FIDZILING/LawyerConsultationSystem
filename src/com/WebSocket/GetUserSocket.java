package com.WebSocket;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.User.OnlineUser;
import com.alibaba.fastjson.JSONObject;

@ServerEndpoint("/getusersocket/{name}/{type}")
public class GetUserSocket {
	// 改用map来存放客户
	private static Map<String, OnlineUser> onlineUserMap = new ConcurrentHashMap<String, OnlineUser>();

	@OnOpen
	public void onOpen(Session session, @PathParam("name") String name, @PathParam("type") String type) {
		OnlineUser onlineUser = new OnlineUser(name, type, session);
		onlineUserMap.put(name, onlineUser);
	}

	@OnClose
	public void onClose(@PathParam("name") String name) {
		onlineUserMap.remove(name);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		JSONObject json;
		json = JSONObject.parseObject(message);
		String mes = json.getString("message");
		String from = json.getString("from");
		String to = json.getString("to");
		System.out.println(mes);
		System.out.println(from + " " + to);
		String usernamemes = "<li value='" + from + "'>" + from + "</li>";
		onlineUserMap.get(to).getSession().getAsyncRemote().sendText(usernamemes);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
}
