package com.xf.kafka;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.xf.WebSocketHandler;

public class KafkaConsumer implements MessageListener<Integer, String>{  
    
	@Override
	public void onMessage(ConsumerRecord<Integer, String> record) {
		 String value = record.value();
		 System.out.println(value);
		 Map<String,WebSocketSession> sessionMap = WebSocketHandler.getSessionMap();
         if (!sessionMap.isEmpty()) { 
     		JSONObject js = (JSONObject) JSONObject.parse(value);
     		String strategyId = js.getString("strategy_id");
     		WebSocketSession wss = sessionMap.get(strategyId);
    		try {
    			if(wss.isOpen()) {
    				wss.sendMessage(new TextMessage(value));
    				System.out.println("value:" + value);
    			}
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
		
		
	}  
  
}  


