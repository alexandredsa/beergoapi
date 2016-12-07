package br.com.beergo.beergoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.beergo.beergoapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByLoginAndPassword(String login, String password);
}
