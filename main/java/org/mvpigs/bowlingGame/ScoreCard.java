package org.mvpigs.bowlingGame;

public class ScoreCard {
	
	private String scoreCard = "";
	private int totalScore = 0;
	private boolean firstRoll = true;
	private boolean spare = false;
	private boolean strike = false;
	private int firstRollPins = 0;
	private boolean doubleStrike = false;
	
	private int rolls = 0;
	
	public static String symbols = "-123456789X/";
	
	/* Constructores */
	
	public ScoreCard(){
		this.scoreCard = "";
		this.rolls = 0;
	}
	
	public ScoreCard(String scoreCard){
		this.scoreCard = scoreCard;
		this.rolls = scoreCard.length();
	}
	
	/* getters y setters */
	
	public String getScoreCard(){
		return this.scoreCard;
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}

	private void setTotalScore(int total) {
		this.totalScore = total;
	}
		
	private boolean isSpare(){
		return this.spare;
	}
	
	private void setSpare(boolean spare){
		this.spare = spare;
	}
	
	private boolean isFirstRoll(){
		return this.firstRoll;
	}
	
	private void setFirstRoll(boolean value){
		this.firstRoll = value;
	}
	
	private void setFirstRollPins(int pins){
		this.firstRollPins = pins;
	}
	
	private int getFirstRollPins(){
		return this.firstRollPins;
	}
	
	private void setStrike(boolean strike){
		this.strike = strike;
	}
	
	private boolean isStrike(){
		return this.strike;
	} 
	
	private void setDouble(boolean value){
		this.doubleStrike = value;
	}
	
	private boolean isDouble(){
		return this.doubleStrike;
	}
	
	/* Parte privada */
	
	private boolean isExtraRoll(){
		return this.rolls <= 2;
	}
	
	private boolean isLastExtraRoll(){
		return 	this.rolls == 1;
	}
	
	private boolean isLastFrame(){
		return this.rolls <= 3;
	}
	
	
	/* Interfaz Publica */
	
	public static int getSymbolValue(char symbol){
		return symbols.indexOf(symbol);
	}
	
	public void calculateScore(){
		
		for(int i=0; i < getScoreCard().length(); i++){
			char pin = getScoreCard().charAt(i);
			roll(pin);
		}		
	}
	
	public void roll(char pins){
				
		if(pins == 'X'){
			if(isStrike() & !isExtraRoll()){
				setTotalScore(getTotalScore() + getFirstRollPins());
			}
			if(isDouble() & !isLastExtraRoll()){
				// la primera bola extra cuenta doble si hay un double strike
				setTotalScore(getTotalScore() + getFirstRollPins());
			}
			if(isStrike() & !isLastFrame()){
				setDouble(true);
			}
			setTotalScore(getTotalScore() + getSymbolValue(pins));
			setStrike(true);
			setFirstRoll(true);
		}
		else if(isFirstRoll()){
			setFirstRollPins(getSymbolValue(pins));	
			setFirstRoll(false);
			if(isStrike() & !isExtraRoll()){
				setTotalScore(getTotalScore() + getFirstRollPins());
			}
			if(isDouble() & !isLastExtraRoll()){
				// la primera bola extra cuenta doble si hay un double strike
				setTotalScore(getTotalScore() + getFirstRollPins());
			}
			// first roll is not a strike X
			setStrike(false);
			setDouble(false);
			if(isSpare()){
				// ultima bola extra si es X no cuenta doble
				// ya la he sumado en el caso pins == 'X'
				setTotalScore(getTotalScore() + getFirstRollPins());
			}
		}
		else if(pins == '/'){
			setTotalScore(getTotalScore() + 10);
			setSpare(true);
			if(isStrike()){
				setTotalScore(getTotalScore() + 5);
			}
			setStrike(false);
			setFirstRoll(true);			
		}
		else {
			// second roll not spare and not strike
			setTotalScore(getTotalScore() + getFirstRollPins() + getSymbolValue(pins));
			if(isStrike()){
				setTotalScore(getTotalScore() + getSymbolValue(pins));
			}
			setFirstRoll(true);
			setSpare(false);
			setStrike(false);
		}
		this.rolls-= 1;
	}
}
