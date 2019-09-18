package com.study.jsp;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
	/**
	 *  @OnOpen allows us to intercept the creation of a new session.
	 *  The session class allows us to send data to the user.
	 *  In the method onOpen, we'll let the user know that the handshke was
	 *  successful.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id : " + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
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
			basic.sendText("to : " + message);
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
			for(Session session : WsServer.sessions) {
				if(! self.getId().equals(session.getId()))
					session.getBasicRemote().sendText("All : " + message);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
}
