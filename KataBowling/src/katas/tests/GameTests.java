package katas.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import katas.Game;

public class GameTests {

	public static final int NORMAL_ROLL_2 = 2;
	public static final int NORMAL_ROLL_3 = 3;
	public static final int NORMAL_ROLL_5 = 5;
	public static final int NORMAL_ROLL_7 = 7;
	public static final int NORMAL_STRIKE = 10;
	private Game bowlingGame;

	@Before
	public void initBowlingGame() {
		this.bowlingGame = new Game();
	}

	@Test
	public void WHEN_bowlingGameIsInitialized_THEN_ScoreIsZero() {
		assertEquals(0, this.bowlingGame.score());
	}

	@Test
	public void WHEN_firstRollWithLessThanTen_THEN_ScoreIsZero() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_2);
		assertEquals(2, this.bowlingGame.score());
	}

	@Test
	public void WHEN_firstAndSecondRollWithLessThanTen_THEN_ScoreIsNumberOfPinsDown() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_3);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_2);
		assertEquals(5, this.bowlingGame.score());
	}

	@Test
	public void WHEN_spareFollowedByNormalBowl_THEN_ScoreIsNumberOfPinsDownPlusNextRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		assertEquals(20, this.bowlingGame.score());
	}

	@Test
	public void WHEN_spareFollowedByTwoNormalBowls_THEN_ScoreIsNumberOfPinsDownPlusNextRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_2);
		assertEquals(22, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingSpare_THEN_ScoreIsAccurate() {
		this.bowlingGame.roll(9);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(6);

		assertEquals(31, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingSpareWith10PinsOnSecondThrow_THEN_ScoreIsAccurate() {
		this.bowlingGame.roll(9);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(6);

		assertEquals(31, this.bowlingGame.score());
	}

	@Test
	public void WHEN_twoSpareBackToBack_THEN_ScoreIsNumberOfPinsDownPlusNextRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_3);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_7);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_2);
		assertEquals(27, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingStrike_THEN_ScoreIsNumberOfPinsDownPlusNextTwoRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_STRIKE);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_2);
		assertEquals(24, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingThreeSpares_THEN_ScoreIsNumberOfPinsDownPlusNextTwoRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_3);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_7);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_5);
		this.bowlingGame.roll(GameTests.NORMAL_ROLL_3);
		assertEquals(44, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingThreeStrikes_THEN_ScoreIsNumberOfPinsDownPlusNextTwoRoll() {
		this.bowlingGame.roll(GameTests.NORMAL_STRIKE);
		this.bowlingGame.roll(GameTests.NORMAL_STRIKE);
		this.bowlingGame.roll(GameTests.NORMAL_STRIKE);
		assertEquals(60, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingCompleteGameWithoutSpareOrStrike_THEN_ScoreIsAccurate() {
		this.bowlingGame.roll(9);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(5);
		this.bowlingGame.roll(6);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(6);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(5);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(2);
		this.bowlingGame.roll(5);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(1);

		assertEquals(82, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingCompleteGameWithSpareButNoStrike_THEN_ScoreIsAccurate() {
		this.bowlingGame.roll(9);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(6);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(5);
		this.bowlingGame.roll(5);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(8);
		this.bowlingGame.roll(2);
		this.bowlingGame.roll(8);

		assertEquals(131, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingCompleteGameWithSpareAndStrike_THEN_ScoreIsAccurate() {
		this.bowlingGame.roll(10); // Strike Frame 1
		this.bowlingGame.roll(3);
		this.bowlingGame.roll(7); // Spare Frame 2
		this.bowlingGame.roll(6);
		this.bowlingGame.roll(1);
		this.bowlingGame.roll(10); // Strike Frame 4
		this.bowlingGame.roll(10); // Strike Frame 5
		this.bowlingGame.roll(10); // Strike Frame 6
		this.bowlingGame.roll(2);
		this.bowlingGame.roll(8); // Spare Frame 7
		this.bowlingGame.roll(9);
		this.bowlingGame.roll(0);
		this.bowlingGame.roll(7);
		this.bowlingGame.roll(3); // Spare Frame 9
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);

		assertEquals(193, this.bowlingGame.score());
	}

	@Test
	public void WHEN_bowlingPerfectGame_THEN_ScoreIsAccurate() {

		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10); // Last Frame: Throwing 3 balls
		this.bowlingGame.roll(10);
		this.bowlingGame.roll(10);

		assertEquals(300, this.bowlingGame.score());
	}

}
