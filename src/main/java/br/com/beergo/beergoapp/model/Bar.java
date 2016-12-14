package br.com.beergo.beergoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.beergo.beergoapp.domain.user.LevelExperience;

@Entity
public class Bar {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	@Column(name = "name", length = 45, nullable = false, unique = false)
	private String name;
	@Column(name = "max_discount_offered", length = 45, nullable = false, unique = false)
	private int maxDiscountOffered;
	@Column(name = "owner_name", length = 45, nullable = false, unique = false)
	private String ownerName;
	@Column(name = "owner_cpf", length = 45, nullable = false, unique = false)
	private String ownerCpf;
	@Column(nullable = true)
	private String currentCode;
	@Column
	private double rating;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxDiscountOffered() {
		return maxDiscountOffered;
	}

	public void setMaxDiscountOffered(int maxDiscountOffered) {
		this.maxDiscountOffered = maxDiscountOffered;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerCpf() {
		return ownerCpf;
	}

	public void setOwnerCpf(String ownerCpf) {
		this.ownerCpf = ownerCpf;
	}

	public String getCurrentCode() {
		return currentCode;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}

	public double calculateDiscount(int userLevel) {
		int maxLevel = LevelExperience.getMaxLevel().getInt();
		return ((float) userLevel / (float) maxLevel) * maxDiscountOffered;
	}

	public double calculateReward() {
		double rewardRating = rating * 18;
		return rating + 22;
	}

}
