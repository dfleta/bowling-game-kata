package org.mvpigs.bowlingGame;

import org.mvpigs.bowlingGame.ScoreCard;

public class Game {

	private int total = 0;

	public Game() {
		this.total = 0;
	}

	public int getTotal() {
		return this.total;
	}

	private void setTotal(int total) {
		this.total = total;
	}

	public void roll(char pins) {
		this.setTotal(this.getTotal() + ScoreCard.getSymbolValue(pins));
	}

}
