package com.meli.ofq.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.ofq.app.model.Satelite;
import com.meli.ofq.app.model.dao.SateliteDao;

@Service
public class SateliteServiceImpl implements SateliteService {
	
	@Autowired
	private SateliteDao sateliteDao;
	
	public Satelite findByName(String name) {
		return sateliteDao.findByName(name);
	}

	@Override
	public List<Satelite> findAll() {
		return sateliteDao.findAll();
	}

	@Override
	public Optional<Satelite> findById(String id) {
		return sateliteDao.findById(id);
	}

	@Override
	public Satelite save(Satelite satelite) {
		return sateliteDao.save(satelite);
	}

}
