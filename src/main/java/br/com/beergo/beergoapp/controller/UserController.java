package br.com.beergo.beergoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.beergo.beergoapp.domain.user.LevelExperience;
import br.com.beergo.beergoapp.domain.user.UserDTO;
import br.com.beergo.beergoapp.model.User;
import br.com.beergo.beergoapp.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> signUp(@RequestBody User u) {
		userRepository.save(u);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity auth(@RequestBody User u) {
		if (userRepository.findByLoginAndPassword(u.getLogin(), u.getPassword()) != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setName(u.getLogin());
			userDTO.setExperience(u.getExperience());
			LevelExperience levelExperience = LevelExperience.getLevel(userDTO.getExperience());
			userDTO.setLevel(levelExperience.getInt());
			userDTO.setRequiredExperience(levelExperience.getExperience());
			return new ResponseEntity(userDTO, HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}
}
