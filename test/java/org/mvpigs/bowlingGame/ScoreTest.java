package org.mvpigs.bowlingGame;

import static org.junit.Assert.*;

import org.mvpigs.bowlingGame.Game;
import org.junit.Test;

public class ScoreTest {

	@Test
	public void RollTestFirstFrameHittingPins() {

		// If in two tries, he fails to knock them all down, his score
		// for that frame is the total number of pins knocked down
		// in his two tries.

		char[] pins = { '1', '2' };
		int total = 3;

		Game score = new Game();
		score.roll(pins[0]);
		score.roll(pins[1]);

		assertEquals(total, score.getTotal());

	}

	/*
	@Test
	public void TotalScoreHittingPins(){
		
		// Hitting pins total = 60
		String pins = "12345123451234512345";
		int total = 60;
		
		Game score = new Game();
		
		for(int i=0; i<pins.length(); i++){
			int pinValue = Character.getNumericValue(pins.charAt(i));
			score.roll(pinValue);
		}
		
		assertEquals(total, score.getTotal());		
		
	}
	*/

	@Test
	public void TotalScoreHittingPins() {

		// Hitting pins total = 60
		String pins = "12345123451234512345";
		int total = 60;

		Game score = new Game();

		for (int i = 0; i < pins.length(); i++) {
			char pin = pins.charAt(i);
			score.roll(pin);
		}

		assertEquals(total, score.getTotal());

	}

	@Test
	public void TotalScoreHittingPinsFail() {

		// test symbol -

		String pins = "9-9-9-9-9-9-9-9-9-9-";
		int total = 90;

		Game score = new Game();

		for (int i = 0; i < pins.length(); i++) {
			char pin = pins.charAt(i);
			score.roll(pin);
		}

		assertEquals(total, score.getTotal());
	}

}
