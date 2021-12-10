package com.meli.ofq.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.ofq.app.model.MessageRequest;
import com.meli.ofq.app.model.MessageResponse;
import com.meli.ofq.app.model.Position;
import com.meli.ofq.app.model.SateliteInformacion;
import com.meli.ofq.app.model.SateliteRequest;
import com.meli.ofq.app.service.MessageRequestService;
import com.meli.ofq.app.service.SateliteService;
import com.meli.ofq.app.usecases.FuegoQuasar;

@RestController
public class ApiRestController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRestController.class);

	@Autowired
	private SateliteService sateliteService;

	@Autowired
	private MessageRequestService messageRequestService;

	@Autowired
	private FuegoQuasar fuegoQuasarUseCase;

	@PostMapping("/topsecret/")
	public ResponseEntity<MessageResponse> topsecret(@RequestBody SateliteRequest request) {

		List<MessageRequest> detalleSatelite = request.getSatellites();
		List<SateliteInformacion> sateliteXdetalle = new ArrayList<>();

		detalleSatelite.forEach((p) -> {
			validSaveMessageRequest(p);
			sateliteXdetalle.add(SateliteInformacion.builder().satelite(sateliteService.findByName(p.getName()))
					.messageRequest(p).build());
		});

		if (sateliteXdetalle.size() != 3) {
			return ResponseEntity.notFound().build();
		}

		return obtenerRespuesta(sateliteXdetalle);
	}

	@PostMapping("/topsecret_split/{satellite_name}")
	public ResponseEntity<MessageResponse> topsecret_split(@PathVariable String satellite_name,
			@RequestBody MessageRequest request) {

		if (sateliteService.findByName(satellite_name.toLowerCase()) == null) {
			return ResponseEntity.notFound().build(); // Satelite no existe
		}
		
		request.setName(satellite_name.toLowerCase());
		validSaveMessageRequest(request);
		

		return ResponseEntity.ok().body(MessageResponse.builder().message("Registrado correctamente").build());
	}

	@GetMapping("/topsecret_split/")
	public ResponseEntity<MessageResponse> getTopsecret() {

		if (messageRequestService.findAll().size() != 3) {
			return ResponseEntity.notFound().build();
		}

		List<MessageRequest> detalleSatelite = messageRequestService.findAll();
		List<SateliteInformacion> sateliteXdetalle = new ArrayList<>();

		detalleSatelite.forEach((p) -> {
			sateliteXdetalle.add(SateliteInformacion.builder().satelite(sateliteService.findByName(p.getName()))
					.messageRequest(p).build());
		});

		return obtenerRespuesta(sateliteXdetalle);
	}
	
	private void validSaveMessageRequest(MessageRequest messageRequest) {
		if (messageRequestService.findByName(messageRequest.getName().toLowerCase()) != null) {
			messageRequestService.delete(messageRequestService.findByName(messageRequest.getName().toLowerCase()));
		}
		messageRequestService.save(messageRequest);
	}
	
	private ResponseEntity<MessageResponse> obtenerRespuesta(List<SateliteInformacion> sateliteXdetalle) {
		Position posFinal = fuegoQuasarUseCase.getLocation(sateliteXdetalle);
		String msgFinal = fuegoQuasarUseCase.getMessage(sateliteXdetalle);

		if (msgFinal == null || posFinal == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(MessageResponse.builder().position(posFinal).message(msgFinal).build());
	}

}
