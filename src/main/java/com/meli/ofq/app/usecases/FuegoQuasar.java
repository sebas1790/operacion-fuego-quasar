package com.meli.ofq.app.usecases;

import java.util.List;

import com.meli.ofq.app.model.Position;
import com.meli.ofq.app.model.SateliteInformacion;

public interface FuegoQuasar {
	
	public Position getLocation(List<SateliteInformacion> detalles);
	
	public String getMessage(List<SateliteInformacion> detalles);

}
