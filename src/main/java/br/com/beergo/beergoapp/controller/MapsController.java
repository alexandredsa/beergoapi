package br.com.beergo.beergoapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapsController {
	@RequestMapping("/")
	public String get(@RequestParam("location") List<Double> latLng) {
		
		return "Greetings from Spring Boot!";
	}
}
