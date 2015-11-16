package org.highlandschool.course;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import org.highlandschool.course.Card.Rank;
import org.highlandschool.course.Card.Suit;

public class CardDeck 
{
	protected ArrayList<Card> cards;
	int numberOfCardsInDeck;
	
	public CardDeck()
	{
		numberOfCardsInDeck = 52;
		cards = new ArrayList<Card>();
		int count = 0;
		for (Suit s : Suit.values())
		{
			for (Rank r: Rank.values())
			{
				Card c = new Card(s, r);
				cards.add(c);
			}
		}
	}
	
	public void shuffle() 
	{
		Random rand = new Random();
		// get a random # between 0 and 52
		// swap that card with the ith card
		for (int i = 0; i < 52; i++) 
		{
			int randomIdx = rand.nextInt(52);
			swap(i, randomIdx);
		}
	}	
	
	private void swap(int idx1, int idx2) 
	{
		Card tempCard = cards.get(idx1);
		cards.set(idx1,cards.get(idx2));
		cards.set(idx2,tempCard);
	}

	public Card getCard(int idx) 
	{
		Card c = null;
		if (idx >= 0 && idx < 52)
		{
			c = cards.get(idx);
		}
		return c;
	}
	
	public void printDeck(PrintStream ps) 
	{
		for (int i=0; i < 52; i++) 
		{
			Card c = getCard(i);
			ps.println("Card #: " + i + ":" + c);
		}
	}
	
	public Card dealCard()
	{
		// return the top card
		return cards.remove(0);
	}
	
	public boolean returnToDeck(Card c)
	{
		return cards.add(c);
	}
	
	public static void main(String [] args) 
	{
		try
		{
			CardDeck deck = new CardDeck();
			deck.printDeck(System.out);
			
			// shuffle them
			deck.shuffle();
			
			deck.printDeck(System.out);			
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
		}
	}
}
