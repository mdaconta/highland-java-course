package org.highlandschool.course;

import java.util.ArrayList;

public class CardHand {
	
	protected ArrayList<Card> cards;
	int numCardsInHand;
	int maxCardsInHand;
	
	public CardHand(int cardsInHand)
	{
		maxCardsInHand = cardsInHand;
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card c)
	{
		cards.add(c);
		numCardsInHand++;
	}
	
	public Card removeCard(int idx)
	{
		Card c = cards.remove(idx);
		if (c != null) 
			numCardsInHand--;
		return c;
	}

	public Card getCard(int idx)
	{
		Card c = null;
		if (cards != null && numCardsInHand > 0) 
		{
			if (idx >= 0 && idx < maxCardsInHand)
			{
				c = cards.get(idx); 
			}
		}
		return c;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
