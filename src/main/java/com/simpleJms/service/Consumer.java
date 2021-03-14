package com.simpleJms.service;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;
//a consumer without calling Spring jms service
@Service
public class Consumer extends JMSConfig{

	
	public boolean receiveMessage(String QName) {
		try {
			Connection cnx = connexion(connectionFactory());
			cnx.start();
			Session session = session(cnx);
			MessageConsumer consumer = consumeMessage(QName, session);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {

					try {
						if(message instanceof TextMessage) {
							TextMessage textMessage = (TextMessage) message;
							String text = textMessage.getText();
							System.out.println("Received Message : "+text);
						}else {
							System.out.println("Received not a text message : "+message.toString());
						}
						session.close();
						cnx.close();
					} catch (Exception e) { 
						// TODO: handle exception
					}
				}
			});
			
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
}
