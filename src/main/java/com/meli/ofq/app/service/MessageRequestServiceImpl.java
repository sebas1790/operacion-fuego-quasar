package com.meli.ofq.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.ofq.app.model.MessageRequest;
import com.meli.ofq.app.model.dao.MessageRequestDao;

@Service
public class MessageRequestServiceImpl implements MessageRequestService {

	@Autowired
	private MessageRequestDao messageRequestDao;

	@Override
	public List<MessageRequest> findAll() {
		return messageRequestDao.findAll();
	}

	@Override
	public Optional<MessageRequest> findById(String name) {
		return messageRequestDao.findById(name);
	}

	@Override
	public MessageRequest save(MessageRequest messageRequest) {
		return messageRequestDao.save(messageRequest);
	}

	@Override
	public MessageRequest findByName(String name) {
		return messageRequestDao.findByName(name);
	}

	@Override
	public void delete(MessageRequest messageRequest) {
		messageRequestDao.delete(messageRequest);
	}

	@Override
	public void deleteAll() {
		messageRequestDao.deleteAll();
	}

}
