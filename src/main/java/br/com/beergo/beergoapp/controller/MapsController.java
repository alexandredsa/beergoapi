package br.com.beergo.beergoapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import br.com.beergo.beergoapp.config.AppConfig;
import br.com.beergo.beergoapp.domain.maps.MapsMobileDTO;
import br.com.beergo.beergoapp.domain.maps.MapsResultsModel;
import br.com.beergo.beergoapp.domain.user.LevelExperience;
import br.com.beergo.beergoapp.model.User;
import br.com.beergo.beergoapp.repository.BarRepository;
import br.com.beergo.beergoapp.repository.UserRepository;
import br.com.beergo.beergoapp.tools.ObjectSerializer;

@RestController
@RequestMapping("/maps")
public class MapsController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BarRepository barRepository;

	@RequestMapping(value = "", produces = "application/json")
	public ResponseEntity<List<MapsMobileDTO>> get(@RequestParam("location") List<Double> latLng,
			@RequestParam("id") Long userId) throws JsonProcessingException {
		User u = userRepository.findOne(userId);

		if (u == null)
			return new ResponseEntity("Usuário não cadastrado", HttpStatus.BAD_REQUEST);

		MapsResultsModel results = retrieveFromMaps(latLng);
		List<MapsMobileDTO> dto = results.toDTO(barRepository, LevelExperience.getLevel(u.getExperience()).getInt());
		return new ResponseEntity(dto.toString(), HttpStatus.OK);
	}

	private MapsResultsModel retrieveFromMaps(List<Double> latLng) {
		RestTemplate restTemplate = new RestTemplate();
		String url = AppConfig.GOOGLE_MAPS_RETRIEVE_PATH + "?location=" + latLng.get(0) + "," + latLng.get(1) + "&"
				+ "radius=2500&types=bar&key=" + AppConfig.GOOGLE_MAPS_API_KEY;

		// return restTemplate.getForObject(url, MapsResultsModel.class);
		String jsonMaps = restTemplate.getForObject(url, String.class);
		return new Gson().fromJson(jsonMaps, MapsResultsModel.class);
	}
	
/*
	@RequestMapping(value = "/mock", produces = "application/json")
	private String mock() throws IOException {
		return new String(Files.readAllBytes(Paths.get("C:\\xampp\\htdocs\\maps.json")));
	}*/
}
