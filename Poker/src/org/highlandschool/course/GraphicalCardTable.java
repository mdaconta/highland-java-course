package org.highlandschool.course;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.highlandschool.course.GraphicalCard.CardStatus;

public class GraphicalCardTable extends JPanel {
	
	GraphicalCardDeck gcDeck; 
	int numberOfPlayers;
	int numberOfCardsInHand;
	GraphicalCardHand [] gcHands; 
	
	public GraphicalCardHand [] getGraphicalCardHands() {
		return gcHands;
	}
	
	public GraphicalCardDeck getGcDeck() {
		return gcDeck;
	}

	public void setGcDeck(GraphicalCardDeck gcDeck) {
		this.gcDeck = gcDeck;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void foldHands() throws Exception {
		for (int i=0; i < numberOfPlayers; i++) {
			gcHands[i].fold(gcDeck);
		}
	}
	
	public void dealHands() {
		for (int i=0; i < numberOfPlayers; i++) {
			for (int j=0; j < numberOfCardsInHand; j++) {
				GraphicalCard gCard = gcDeck.dealCard();
				if (i == PokerGame.PLAYER_IDX) // note: should be passed in
					gCard.setCardStatus(CardStatus.FACEUP);
				else 
					gCard.setCardStatus(CardStatus.FACEDOWN);
				gcHands[i].addCard(gCard);
			}
		}
		
		refreshHandPanels();
	}
	
	public void refreshHandPanels() {
		// player order is S, N, E, W
		String currentPosition = BorderLayout.SOUTH;
		// add the players
		for (int i=0; i < numberOfPlayers; i++)
		{
			JPanel playerPanel = gcHands[i].getHandPanel();
			this.add(playerPanel, currentPosition);
			currentPosition = nextPosition(currentPosition);
			playerPanel.paintAll(playerPanel.getGraphics());
		}
	}

	public void setNumberOfPlayers(int numberOfPlayers) throws IllegalArgumentException {
		// TEMP
		if (numberOfPlayers != 2)
			throw new IllegalArgumentException("Number of Players must be 2 for now.");
		/*
		if (numberOfPlayers < 2 || numberOfPlayers > 4)
			throw new IllegalArgumentException("Invalid # of players!");
		*/
		this.numberOfPlayers = numberOfPlayers;
	}

	public GraphicalCardTable(int numberOfPlayers, int numberOfCardsInHand) throws IllegalArgumentException, IOException
	{
		this.setLayout(new BorderLayout());
		setNumberOfPlayers(numberOfPlayers);
		this.numberOfCardsInHand = numberOfCardsInHand;
		
		// create the Deck
		gcDeck = new GraphicalCardDeck();
		gcDeck.shuffle();
		
		// create the card hand
		gcHands = new GraphicalCardHand[numberOfPlayers];
		for (int i=0; i < numberOfPlayers; i++)
		{
			gcHands[i] = new GraphicalCardHand(numberOfCardsInHand);
			
			for (int j=0; j < numberOfCardsInHand; j++)
			{
				GraphicalCard gCard = gcDeck.dealCard();
				if (i == 0)
					gCard.setCardStatus(CardStatus.FACEUP);
				else
					gCard.setCardStatus(CardStatus.FACEDOWN);
				gcHands[i].addCard(gCard);
			}
		}
		
		// create the table
		JLabel centerLabel = new JLabel(new ImageIcon(gcDeck.getDeckImage()));
		this.add(centerLabel, BorderLayout.CENTER);
		
		// player order is S, N, E, W
		String currentPosition = BorderLayout.SOUTH;
		// add the players
		for (int i=0; i < numberOfPlayers; i++)
		{
			JPanel playerPanel = gcHands[i].getHandPanel();
			this.add(playerPanel, currentPosition);
			currentPosition = nextPosition(currentPosition);
		}
	}
	
	public JPanel getTable() { return this; }
	
	public String nextPosition(String currentPosition)
	{
		String newPosition = null;
		
		switch (currentPosition) 
		{
		case BorderLayout.SOUTH:
			newPosition = BorderLayout.NORTH;
			break;
		case BorderLayout.NORTH:
			newPosition = BorderLayout.EAST;
			break;
		case BorderLayout.EAST:
			newPosition = BorderLayout.WEST;
			break;
		case BorderLayout.WEST:
			newPosition = BorderLayout.SOUTH;
			break;
		default:
			newPosition = "ERROR";
		}
	
		return newPosition;
	}

	public static void main(String[] args) 
	{
		try 
		{
			GraphicalCardTable gcTable = new GraphicalCardTable(2,5);
	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,200);
	        frame.add(gcTable.getTable());
	        frame.pack();
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		} catch (Throwable t) 
		{
			t.printStackTrace();
		}

	}

}
