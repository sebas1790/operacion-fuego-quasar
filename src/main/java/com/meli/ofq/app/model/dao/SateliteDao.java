package com.meli.ofq.app.model.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meli.ofq.app.model.Satelite;

public interface SateliteDao extends MongoRepository<Satelite, String> {

	public Satelite findByName(String name);
	
}
