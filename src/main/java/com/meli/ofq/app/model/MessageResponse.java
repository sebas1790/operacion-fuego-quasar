package com.meli.ofq.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MessageResponse {
	
	private Position position;
	private String message;

}
