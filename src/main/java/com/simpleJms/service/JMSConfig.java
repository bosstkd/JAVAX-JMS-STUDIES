package com.simpleJms.service;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public abstract class JMSConfig {

    protected ConnectionFactory connectionFactory() {
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		return cf;
	}
	
	//public Connection connection;
	
    protected Connection connexion(ConnectionFactory connectionFactory) throws JMSException {
		return connectionFactory.createConnection();
	}
	
    protected Session session(Connection connection) throws JMSException {
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return session;
	}

    protected void produceMessage(String QName, String message, Session session) throws JMSException {
		Destination destination = session.createQueue(QName);
		// if you want a topic
		// Destination destination = session().createTopic(QName);
		MessageProducer producer = session.createProducer(destination);
		// pour rendre le message non persistant
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		TextMessage msg = session.createTextMessage(message);
		producer.send(msg);
		session.close();
	}
	
    protected MessageConsumer consumeMessage(String QName, Session session) throws JMSException {
		Destination destination = session.createQueue(QName);
		MessageConsumer consume = session.createConsumer(destination);
		return consume;
	}
	
}
