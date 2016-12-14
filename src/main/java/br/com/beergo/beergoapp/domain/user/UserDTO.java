package br.com.beergo.beergoapp.domain.user;

import com.google.gson.annotations.SerializedName;

import br.com.beergo.beergoapp.model.User;

public class UserDTO {
	private long id;
	private String name;
	private int level;
	private long experience;
	@SerializedName("required_experience")
	private long requiredExperience;

	public UserDTO(User u) {
		id = u.getID();
		name = u.getLogin();
		experience = u.getExperience();
		LevelExperience lvlExperience = LevelExperience.getLevel(experience);
		level = lvlExperience.getInt();
		requiredExperience = lvlExperience.getNext().getExperience();
	}
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



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
