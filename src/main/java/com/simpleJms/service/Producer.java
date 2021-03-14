package com.simpleJms.service;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.stereotype.Service;


// a producer without calling Spring jms service
@Service
public class Producer extends JMSConfig{
	
	public boolean sendMessage(String QName, String message) {
		try {
			Connection cnx = connexion(connectionFactory());
			cnx.start();
			Session session = session(cnx);
			produceMessage(QName,message,session);
			session.close();
			cnx.close();
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
}
