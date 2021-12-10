package com.meli.ofq.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@Document(collection = "relacion")
public class SateliteInformacion {
	
	@Id
	private String id;
	private Satelite satelite;
	private MessageRequest messageRequest;

}
