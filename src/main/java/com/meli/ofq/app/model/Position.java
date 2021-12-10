package com.meli.ofq.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Position {

	private Double x;
	private Double y;
	
}
