package br.com.beergo.beergoapp.domain.maps;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MapsMobileDTO {
	@SerializedName("percent_off")
	private double percentOff;
	private MapsLocation location;
	private String name;
	private float rating;
	@SerializedName("is_open")
	private boolean isOpen;

	public void setPercentOff(double percentOff) {
		this.percentOff = percentOff;
	}

	public void setLocation(MapsLocation location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
