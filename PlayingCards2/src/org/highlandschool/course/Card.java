package org.highlandschool.course;

public class Card 
{
	public enum Suit { SPADES, CLUBS, HEARTS, DIAMONDS };
	public enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };
	
	protected Suit suit;
	protected Rank rank;
	
	public Suit getSuit() 
	{
		return suit;
	}
	
	public void setSuit(Suit suit) 
	{
		this.suit = suit;
	}
	
	public Rank getRank() 
	{
		return rank;
	}
	
	public void setRank(Rank rank) 
	{
		this.rank = rank;
	}
	
	public Card(Suit suit, Rank rank) 
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	public String toString() 
	{
		return "[Rank: " + rank + ", Suit: " + suit + "]";
	}
}
