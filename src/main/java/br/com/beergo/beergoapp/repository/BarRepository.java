package br.com.beergo.beergoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.beergo.beergoapp.model.Bar;

public interface BarRepository extends CrudRepository<Bar, Long> {
	public Bar findByName(String name);
	public Bar findByCurrentCode(String currentCode);
}
