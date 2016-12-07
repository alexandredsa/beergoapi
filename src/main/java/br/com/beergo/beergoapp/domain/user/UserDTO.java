package br.com.beergo.beergoapp.domain.user;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
	private String name;
	private int level;
	private long experience;
	@SerializedName("required_experience")
	private long requiredExperience;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExperience() {
		return experience;
	}

	public void setExperience(long experience) {
		this.experience = experience;
	}

	public long getRequiredExperience() {
		return requiredExperience;
	}

	public void setRequiredExperience(long requiredExperience) {
		this.requiredExperience = requiredExperience;
	}

}
