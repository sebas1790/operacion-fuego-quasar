package com.meli.ofq.app.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@Document(collection = "satelites")
public class Satelite {
	
	@Id
	private String id;
	private String name;
	private Position position;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Satelite other = (Satelite) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Satelite [name=" + name + ", position=" + position + "]";
	}

}
