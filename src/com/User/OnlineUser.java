package com.User;

import javax.websocket.Session;

public class OnlineUser {
	private String name;
	private String type;
	private Session session;

	public OnlineUser() {
	}

	public OnlineUser(String name, String type, Session session) {
		this.name = name;
		this.type = type;
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
