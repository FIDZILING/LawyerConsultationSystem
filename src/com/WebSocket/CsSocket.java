package com.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/cssocket/{param}")
public class CsSocket {
	// 记录当前客服
	public static ArrayList<String> onlineCS = new ArrayList<String>();

	// 记录在线客服人数
	private static int onlineCScount = 0;

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("param") String param) {
		this.session = session;
		CsSocket.onlineCS.add(param);
		CsSocket.onlineCS.forEach((n) -> System.out.println(n));

		addOnlineCSCount();
		System.out.println("有新客服加入！当前在线客服人数为" + getOnlineCSCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(@PathParam("param") String param) {
		Iterator<String> ListIterator = CsSocket.onlineCS.iterator();
		while (ListIterator.hasNext()) {
			String e = ListIterator.next();
			if (e.equals(param)) {
				ListIterator.remove();
			}
		}
		CsSocket.onlineCS.forEach((n) -> System.out.println(n));

		subOnlineCSCount();
		System.out.println("有一客服关闭！当前在线客服人数为" + getOnlineCSCount());
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

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		//this.session.getBasicRemote().sendText(message);
		this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCSCount() {
		return onlineCScount;
	}

	public static synchronized void addOnlineCSCount() {
		CsSocket.onlineCScount++;
	}

	public static synchronized void subOnlineCSCount() {
		CsSocket.onlineCScount--;
	}

}
