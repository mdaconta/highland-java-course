package org.highlandschool.course;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.highlandschool.course.Card.Rank;
import org.highlandschool.course.Card.Suit;

public class GraphicalCardDeck extends CardDeck {
	private BufferedImage deckImage;
	
	public BufferedImage getDeckImage() { return deckImage; }
	
	public GraphicalCardDeck() throws IOException
	{
		// load the image
		deckImage = null;
		URL url = this.getClass().getResource("/classic-cards/deck.png");
		if (url != null)
		{
			deckImage = ImageIO.read(url);
		}
		else
		{
			System.out.println("Cannot find the image:deck.png");
		}
		
		numberOfCardsInDeck = 52;
		cards = new ArrayList<Card>();
		for (Suit s : Suit.values())
		{
			for (Rank r: Rank.values())
			{
				GraphicalCard gc = new GraphicalCard(s, r);
				cards.add(gc);
			}
		}
	}
	
	public GraphicalCard dealCard()
	{
		return (GraphicalCard) super.dealCard();
	}
	
	public boolean returnToDeck(GraphicalCard gCard) 
	{
		return super.returnToDeck(gCard);
	}
	
	public static void main(String[] args) 
	{
		try 
		{
			GraphicalCardDeck gcardDeck = new GraphicalCardDeck();
			ImageIcon icon=new ImageIcon(gcardDeck.getDeckImage());
	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,200);
	        JLabel lbl=new JLabel();
	        lbl.setIcon(icon);
	        frame.add(lbl);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
		}

	}

}
