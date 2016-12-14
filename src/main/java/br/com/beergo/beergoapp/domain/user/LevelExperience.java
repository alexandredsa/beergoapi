package br.com.beergo.beergoapp.domain.user;

public enum LevelExperience {
	ONE(0, 1), TWO(200, 2), THREE(400, 3), FOUR(800, 4), FIVE(1500, 5), SIX(2000, 6), SEVEN(3000, 7), EIGHT(4000,
			8), NINE(5500, 9), TEN(7000, 10);
	private long experience;
	private int value;

	private LevelExperience(long experience, int value) {
		this.experience = experience;
		this.value = value;
	}

	public long getExperience() {
		return experience;
	}

	public LevelExperience getNext() {
		switch (this) {
		case ONE:
			return TWO;
		case TWO:
			return THREE;
		case THREE:
			return FOUR;
		case FOUR:
			return FIVE;
		case FIVE:
			return SIX;
		case SIX:
			return SEVEN;
		case SEVEN:
			return EIGHT;
		case EIGHT:
			return NINE;
		case NINE:
			return TEN;
		case TEN:
			return TEN;
		}

		return ONE;
	}

	public static LevelExperience getLevel(long experience) {

		if (experience >= TEN.getExperience())
			return TEN;

		if (experience >= NINE.getExperience())
			return NINE;

		if (experience >= EIGHT.getExperience())
			return EIGHT;

		if (experience >= SEVEN.getExperience())
			return SEVEN;

		if (experience >= SIX.getExperience())
			return SIX;

		if (experience >= FIVE.getExperience())
			return FIVE;

		if (experience >= FOUR.getExperience())
			return FOUR;

		if (experience >= THREE.getExperience())
			return THREE;

		if (experience >= TWO.getExperience())
			return TWO;

		return ONE;
	}

	public int getInt() {
		return value;
	}

	public static LevelExperience getMaxLevel() {
		return TEN;
	}

}
