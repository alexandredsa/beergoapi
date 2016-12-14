package br.com.beergo.beergoapp.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.beergo.beergoapp.domain.user.LevelExperience;
import br.com.beergo.beergoapp.domain.user.UserDTO;
import br.com.beergo.beergoapp.model.Bar;
import br.com.beergo.beergoapp.model.User;
import br.com.beergo.beergoapp.repository.BarRepository;
import br.com.beergo.beergoapp.repository.UserRepository;
import br.com.beergo.beergoapp.tools.BarPromoCodeGenerator;
import br.com.beergo.beergoapp.tools.SecurityUtils;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BarRepository barRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> signUp(@RequestBody User u) throws NoSuchAlgorithmException, IOException {
		u.setPassword(SecurityUtils.toMD5(u.getPassword()));
		userRepository.save(u);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> update(@RequestBody User u) throws NoSuchAlgorithmException, IOException {
		User user = userRepository.findOne(u.getID());
		if (user != null)
			u.setID(user.getID());

		u.setPassword(SecurityUtils.toMD5(u.getPassword()));
		userRepository.save(u);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDTO> auth(@RequestBody User u) throws NoSuchAlgorithmException, IOException {
		u = userRepository.findByLoginAndPassword(u.getLogin(), SecurityUtils.toMD5(u.getPassword()));
		if (u != null) {
			UserDTO userDTO = new UserDTO(u);
			return new ResponseEntity(userDTO, HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}
}
