package com.kosoeo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;


@ServerEndpoint("/endpoint")
public class ChatServer {
	
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
	/**
	 *  @OnOpen allows us to intercept the creation of a new session.
	 *  The session class allows us to send data to the user.
	 *  In the method onOpen, we'll let the user know that the handshke was
	 *  successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		try {
			final Basic basic = session.getBasicRemote();
			JsonObject msg = new JsonObject();
			msg.addProperty("head", "알림");
			msg.addProperty("msg", "채팅에 접속하였습니다");
			basic.sendText(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId() + " has ended");
		sessions.remove(session);
	}
	/**
	 * When a user sends a message to the server,
	 * this method will intercept the message and allow us to react to it.
	 * For now the message is read as a String.
	 */
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + ": " + message);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sendAllSessionToMessage(session, message);
	}
	
	/**
	 * 모든 사용자에게 메시지를 전달한다.
	 */
	
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for(Session session : ChatServer.sessions) {
				if(! self.getId().equals(session.getId()))
					session.getBasicRemote().sendText(message);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
}
