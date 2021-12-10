package com.meli.ofq.app.service;

import java.util.List;
import java.util.Optional;

import com.meli.ofq.app.model.Satelite;

public interface SateliteService {
	
	public List<Satelite> findAll();

	public Optional<Satelite> findById(String id);

	public Satelite save(Satelite satelite);
	
	public Satelite findByName(String name);
}
