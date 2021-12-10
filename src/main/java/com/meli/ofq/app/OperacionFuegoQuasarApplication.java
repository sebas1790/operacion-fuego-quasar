package com.meli.ofq.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.meli.ofq.app.model.Position;
import com.meli.ofq.app.model.Satelite;
import com.meli.ofq.app.service.SateliteService;

@SpringBootApplication
public class OperacionFuegoQuasarApplication implements CommandLineRunner {
	
	@Autowired
	private SateliteService sateliteService;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(OperacionFuegoQuasarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("satelites");
		mongoTemplate.dropCollection("entradas");
		
		sateliteService.save(Satelite.builder().name("kenobi").position(Position.builder().x(-500.00).y(-200.00).build()).build());
		sateliteService.save(Satelite.builder().name("skywalker").position(Position.builder().x(100.00).y(-100.00).build()).build());
		sateliteService.save(Satelite.builder().name("sato").position(Position.builder().x(500.00).y(100.00).build()).build());
	}
	
	

}
