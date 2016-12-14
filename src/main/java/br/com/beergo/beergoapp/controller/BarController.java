package br.com.beergo.beergoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.beergo.beergoapp.domain.user.UserDTO;
import br.com.beergo.beergoapp.model.Bar;
import br.com.beergo.beergoapp.repository.BarRepository;
import br.com.beergo.beergoapp.tools.BarPromoCodeGenerator;

@RestController
@RequestMapping("/bar")
public class BarController {

	@Autowired
	BarRepository barRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<UserDTO> save(@RequestBody Bar b) {
		Bar bar = barRepository.findByName(b.getName());
		
		if (bar != null)
			b.setID(bar.getID());
		b.setCurrentCode(BarPromoCodeGenerator.generate());
		barRepository.save(b);
		return new ResponseEntity(HttpStatus.CREATED);
	}

}
