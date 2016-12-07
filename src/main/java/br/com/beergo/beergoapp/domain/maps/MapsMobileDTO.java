package br.com.beergo.beergoapp.domain.maps;

import com.google.gson.annotations.SerializedName;

public class MapsMobileDTO {
	@SerializedName("percent_off")
	private int percentOff;
	private MapsLocation location;
	private String name;
	private float rating;
	@SerializedName("is_open")
	private boolean isOpen;
	
	public void setPercentOff(int percentOff) {
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
	
	
}
