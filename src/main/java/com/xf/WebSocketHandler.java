package com.xf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
	private static Map<String,WebSocketSession> sessionMap;
	private WebSocketHandler(){
		sessionMap=new ConcurrentHashMap<String,WebSocketSession>();
	}
	public static Map<String,WebSocketSession> getSessionMap() {
		return sessionMap;
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessionMap.forEach((k,v)->{
			if(v.equals(session)) {
				sessionMap.remove(k);
				System.out.println("a new connection close..." + session);
			}
		});
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		System.out.println("a new connection establish..."+session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		if(payload.contains("backtest_id")) {
			System.out.println("backtest_id:" + payload);
			String backtestId = payload.split("=")[1];
			sessionMap.put(backtestId, session);
		}
		System.out.println("server received:" + payload);
		
		session.sendMessage(new TextMessage(payload));
	}

}
