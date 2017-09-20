package katas;

public class Game {

	public static final int NUMBER_OF_PINS = 10;
	public static final int NUMBER_OF_FRAMES_PER_GAME = 10;
	public static final int NUMBER_OF_SHOTS_PER_GAME = 21;
	public static final int PENULTIMATE_FRAME = NUMBER_OF_FRAMES_PER_GAME - 1;

	private int[] pinsDownPerThrow = new int[Game.NUMBER_OF_SHOTS_PER_GAME];
	private int bowlCounter = 0;

	public int score() {

		int totalScore = 0;
		int scoreForFrame = 0;

		for (int i = 0; i < NUMBER_OF_FRAMES_PER_GAME; i++) {

			scoreForFrame = this.getRegularScore(i);
			scoreForFrame += this.getExtras(i);

			totalScore += scoreForFrame;
		}

		return totalScore;
	}

	private int getRegularScore(int frame) {
		return this.pinsDownPerThrow[frame * 2] + this.pinsDownPerThrow[frame * 2 + 1];
	}

	public void roll(int numberOfPinsDown) {

		this.setNumberOfPinsDownForBowl(numberOfPinsDown);
		this.setBlankSecondBowlForStrike();
		this.increaseBowlCounter();

	}

	private boolean isStrike(int frame) {
		return (this.pinsDownPerThrow[frame * 2] == Game.NUMBER_OF_PINS);
	}

	private boolean isSpare(int frame) {
		return (this.getRegularScore(frame) == Game.NUMBER_OF_PINS);
	}

	private boolean isPenultimateFrame(int frame) {
		return frame >= PENULTIMATE_FRAME;
	}

	private int getExtras(int frame) {
		if (this.isPenultimateFrame(frame))
			return this.pinsDownPerThrow[frame * 2 + 2];
		else
			return this.getBonus(frame);
	}

	private int getBonus(int frame) {

		int bonus = 0;

		if (this.isStrike(frame))
			bonus = this.getBonusSpare(frame) + this.getBonusStrike(frame);
		else if (this.isSpare(frame))
			bonus = this.getBonusSpare(frame);

		return bonus;
	}

	private int getBonusStrike(int frame) {

		if (this.isStrike(frame + 1))
			return this.pinsDownPerThrow[(frame + 2) * 2];
		else
			return this.pinsDownPerThrow[((frame + 1) * 2) + 1];

	}

	private int getBonusSpare(int frame) {
		return this.pinsDownPerThrow[(frame + 1) * 2];
	}

	private void increaseBowlCounter() {
		this.bowlCounter++;
	}

	private void setBlankSecondBowlForStrike() {
		if (this.isStrike(this.bowlCounter / 2) && !this.isPenultimateFrame(this.bowlCounter / 2))
			this.increaseBowlCounter();
	}

	private void setNumberOfPinsDownForBowl(int numberOfPinsDown) {
		if (this.bowlCounter < Game.NUMBER_OF_SHOTS_PER_GAME)
			this.pinsDownPerThrow[this.bowlCounter] = numberOfPinsDown;
	}
}
