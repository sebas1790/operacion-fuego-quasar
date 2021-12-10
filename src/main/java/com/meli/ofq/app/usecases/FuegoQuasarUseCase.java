package com.meli.ofq.app.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.meli.ofq.app.model.MessageRequest;
import com.meli.ofq.app.model.Position;
import com.meli.ofq.app.model.Satelite;
import com.meli.ofq.app.model.SateliteInformacion;

@Component
public class FuegoQuasarUseCase implements FuegoQuasar {

	private static final String MSG_INDETERMINATED = "No se puede determinar el mensaje";

	@Override
	public Position getLocation(List<SateliteInformacion> detalles) {
		Satelite satAux1 = detalles.get(0).getSatelite();
		Satelite satAux2 = detalles.get(1).getSatelite();
		Satelite satAux3 = detalles.get(2).getSatelite();

		MessageRequest msgAux1 = detalles.get(0).getMessageRequest();
		MessageRequest msgAux2 = detalles.get(1).getMessageRequest();
		MessageRequest msgAux3 = detalles.get(2).getMessageRequest();

		try {
			Double xFinal = ((Math.pow(msgAux1.getDistance(), 2)) - (Math.pow(msgAux2.getDistance(), 2))
					+ (Math.pow(satAux2.getPosition().getX(), 2))) / (2 * satAux2.getPosition().getX());

			Double yFinal = ((Math.pow(msgAux1.getDistance(), 2) - Math.pow(msgAux3.getDistance(), 2)
					+ Math.pow(satAux3.getPosition().getX(), 2) + Math.pow(satAux3.getPosition().getY(), 2))
					/ (2 * satAux3.getPosition().getY())
					- (satAux3.getPosition().getX() / satAux3.getPosition().getY()) * xFinal);

			return Position.builder().x(xFinal).y(yFinal).build();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getMessage(List<SateliteInformacion> detalles) {
		List<String> msgAux = new ArrayList<>();
		StringBuilder msgFinal = new StringBuilder();
		msgAux.addAll(detalles.get(0).getMessageRequest().getMessage());

		if (msgAux.size() != detalles.get(1).getMessageRequest().getMessage().size()
				&& msgAux.size() != detalles.get(2).getMessageRequest().getMessage().size())
			return null;

		for (int i = 0; i < msgAux.size(); i++) {
			if (!msgAux.get(i).isEmpty()) {
				msgFinal.append(msgAux.get(i));
			} else if (!detalles.get(1).getMessageRequest().getMessage().get(i).isEmpty()) {
				msgFinal.append(detalles.get(1).getMessageRequest().getMessage().get(i));
			} else if (!detalles.get(2).getMessageRequest().getMessage().get(i).isEmpty()) {
				msgFinal.append(detalles.get(2).getMessageRequest().getMessage().get(i));
			} else {
				return null;
			}

			msgFinal.append(" ");
		}

		return msgFinal.toString().trim();
	}

}
