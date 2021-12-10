package com.meli.ofq.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@Document(collection = "entradas")
public class MessageRequest {
	
	@Id
	private String id;
	private String name;
	private Double distance;
	private List<String> message;

}
