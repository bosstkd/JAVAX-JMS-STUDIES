package com.simpleJms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simpleJms.service.Consumer;
import com.simpleJms.service.Producer;

@RestController
@RequestMapping("/simpleJms")
public class SimpleRestController {

	@Autowired
	private Producer producer;
	
	
	@GetMapping("/produce")
	public boolean produceMessage(@RequestParam String message, @RequestParam String QName) {
		return producer.sendMessage(QName, message);
	}
	
	@Autowired
	private Consumer consumer;
	
	@GetMapping("/consumer")
	public boolean consumeMessage(@RequestParam String QName) {
		return consumer.receiveMessage(QName);
	}
	
	
}
