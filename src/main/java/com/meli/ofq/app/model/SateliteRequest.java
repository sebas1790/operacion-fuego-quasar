package com.meli.ofq.app.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SateliteRequest {
	
	private List<MessageRequest> satellites;

}
