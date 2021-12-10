package com.meli.ofq.app.service;

import java.util.List;
import java.util.Optional;

import com.meli.ofq.app.model.MessageRequest;

public interface MessageRequestService {
	
	public List<MessageRequest> findAll();
	
	public Optional<MessageRequest> findById(String name);
	
	public MessageRequest save(MessageRequest messageRequest);
	
	public MessageRequest findByName(String name);
	
	public void delete(MessageRequest messageRequest);
	
	public void deleteAll();

}
