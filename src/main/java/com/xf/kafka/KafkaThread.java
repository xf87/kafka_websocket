package com.xf.kafka;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.xf.WebSocketHandler;

public class KafkaThread extends Thread {  
    public void run() {  
    	System.out.println("kafka thread start ");
        while (!this.isInterrupted()) {// 线程未中断执行循环  
            try {  
                Thread.sleep(2000); //每隔2000ms执行一次  
                Map<String,WebSocketSession> sessionMap = WebSocketHandler.getSessionMap();
                if (!sessionMap.isEmpty()) {
                	String home = "home";
                	for (int i=0; i<10; i ++) {
                		home +=1;
                		WebSocketSession wss = sessionMap.get("home");
                		try {
							wss.sendMessage(new TextMessage(home));
							System.out.println("home:" + home);
						} catch (IOException e) {
							e.printStackTrace();
						}
                		Thread.sleep(2000); //每隔2000ms执行一次  
                	}
                	String hello = "hello";
                	for (int i=0; i<10; i ++) {
                		hello +=1;
                		WebSocketSession wss2 = sessionMap.get("hello");
                		try {
							wss2.sendMessage(new TextMessage(hello));
							System.out.println("hello:" + hello);
						} catch (IOException e) {
							e.printStackTrace();
						}
                		Thread.sleep(2000); //每隔2000ms执行一次  
                	}
                }
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
} 
