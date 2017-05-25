package com.xf.kafka;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConfigListener implements ServletContextListener {
	private KafkaThread kafkaThread;  

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("listener");
//		kafkaThread = new KafkaThread();  
//		kafkaThread.start(); // servlet 上下文初始化时启动 socket  
	}
 
}