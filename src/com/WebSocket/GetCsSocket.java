package com.WebSocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/getcssocket")
public class GetCsSocket {
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<GetCsSocket> getcsSocketSet = new CopyOnWriteArraySet<GetCsSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		getcsSocketSet.add(this); // 加入set中
		for (GetCsSocket item : getcsSocketSet) {
			String message = "";
			for (Iterator<String> it = CsSocket.onlineCS.iterator(); it.hasNext();) {
				String res = it.next();
				String automes = "<li value='" + res + "'>" + res + "</li>";
				message += automes;
			}
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		getcsSocketSet.remove(this); // 从set中删除
		for (GetCsSocket item : getcsSocketSet) {
			String message = "";
			for (Iterator<String> it = CsSocket.onlineCS.iterator(); it.hasNext();) {
				String res = it.next();
				String automes = "<li value='" + res + "'>" + res + "</li>";
				message += automes;
			}
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

}
