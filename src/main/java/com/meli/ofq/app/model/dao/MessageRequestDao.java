package com.meli.ofq.app.model.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meli.ofq.app.model.MessageRequest;

public interface MessageRequestDao extends MongoRepository<MessageRequest, String> {
	
	public MessageRequest findByName(String name);
	
}
