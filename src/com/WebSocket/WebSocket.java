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

@ServerEndpoint("/websocket/{name}/{type}")
public class WebSocket {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	// private static CopyOnWriteArraySet<WebSocket> webSocketSet = new
	// CopyOnWriteArraySet<WebSocket>();

	// 改用map来存放客户
	private static Map<String, OnlineUser> onlineUserMap = new ConcurrentHashMap<String, OnlineUser>();

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("name") String name, @PathParam("type") String type) {
		OnlineUser onlineUser = new OnlineUser(name, type, session);
		onlineUserMap.put(name, onlineUser);
		// webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(@PathParam("name") String name) {
		onlineUserMap.remove(name);
		// webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);
		JSONObject json;
		json = JSONObject.parseObject(message);
		String mes = json.getString("message");
		String from = json.getString("from");
		String to = json.getString("to");
		System.out.println(mes);
		System.out.println(from + " " + to);
		onlineUserMap.get(from).getSession().getAsyncRemote().sendText(mes);
		onlineUserMap.get(to).getSession().getAsyncRemote().sendText(mes);
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}

}
