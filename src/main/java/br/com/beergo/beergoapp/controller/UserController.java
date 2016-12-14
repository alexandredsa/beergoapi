package br.com.beergo.beergoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.beergo.beergoapp.domain.user.UserDTO;
import br.com.beergo.beergoapp.model.Bar;
import br.com.beergo.beergoapp.model.User;
import br.com.beergo.beergoapp.repository.BarRepository;
import br.com.beergo.beergoapp.repository.UserRepository;
import br.com.beergo.beergoapp.tools.BarPromoCodeGenerator;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BarRepository barRepository;

	@RequestMapping(value = "/id/{id}/code/{code}", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> code(@PathVariable("id") Long id, @PathVariable("code") String code) {
		Bar b = barRepository.findByCurrentCode(code);
		User u = userRepository.findOne(id);

		if (b == null || u == null)
			return new ResponseEntity(HttpStatus.FORBIDDEN);

		b.setCurrentCode(BarPromoCodeGenerator.generate());
		barRepository.save(b);

		double reward = b.calculateReward();
		u.addExperience((long) reward);
		userRepository.save(u);
		return new ResponseEntity(new UserDTO(u), HttpStatus.OK);
	}

}
