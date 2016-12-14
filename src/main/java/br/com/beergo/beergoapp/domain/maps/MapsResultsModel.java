package br.com.beergo.beergoapp.domain.maps;

import java.util.ArrayList;
import java.util.List;

import br.com.beergo.beergoapp.model.Bar;
import br.com.beergo.beergoapp.repository.BarRepository;

public class MapsResultsModel {
	private List<MapsResult> results;

	public List<MapsResult> getResults() {
		return results;
	}

	public List<MapsMobileDTO> toDTO(BarRepository barRepository, int userLevel) {
		ArrayList<MapsMobileDTO> dto = new ArrayList<>();
		for (MapsResult result : results) {
			Bar bar = barRepository.findByName(result.getNome());
			updateBarRating(barRepository, bar, result.getAvaliacao());
			dto.add(result.toMobileDTO(calculateDiscount(userLevel, bar)));
		}
		return dto;
	}

	private void updateBarRating(BarRepository barRepository, Bar bar, float rating) {
		if (bar == null)
			return;
		bar.setRating(rating);
		barRepository.save(bar);
	}

	private double calculateDiscount(int userLevel, Bar b) {
		if (b == null)
			return 0;

		return b.calculateDiscount(userLevel);
	}
}
