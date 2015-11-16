package org.highlandschool.course;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.highlandschool.course.GraphicalCard.CardStatus;

public class GraphicalCardHand extends CardHand implements MouseListener {
	public static final boolean debug = false;
	
	HashMap<JLabel, GraphicalCard> cardMap = new HashMap<JLabel, GraphicalCard>();

	JPanel handPanel = new JPanel(new FlowLayout());
	
	public GraphicalCardHand(int cardsInHand) {
		super(cardsInHand);		
		handPanel.addMouseListener(this);
		evaluateRank();
	}
	
	public enum HandRanking { BUST, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH } ;
	HandRanking handRanking;
	
	public HandRanking getHandRanking() { return handRanking; }

	public void evaluateRank() 
	{
		int rank = 0; // assume a BUST
		
		if (numCardsInHand == 5) 
		{
			cards.sort(null);
			int pairIndex = -1;
			
			// check for pair
			for (int i=0; i < 4; i++) {
				if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
					pairIndex = i;
					rank = 1;
					break;
				}
			}
			
			// check for 2 pair
			if (rank == 1) {
				for (int i=pairIndex + 2; i < 4; i++) {
					if (cards.get(i).getRank() == cards.get(i+1).getRank())
						rank = 2;
				}
			}
			
			// check for 3 of a kind or full house
			for (int i=0; i < 3; i++) {
				if (cards.get(i).getRank() == cards.get(i+1).getRank() && cards.get(i+1).getRank() == cards.get(i+2).getRank()) {
					rank = 3;
					if (i == 0 && cards.get(3).getRank() == cards.get(4).getRank() || i == 2 && cards.get(0).getRank() == cards.get(1).getRank() )
						rank = 6;
				}
			}
			
			// check for 4 of a kind
			for (int i=0; i < 2; i++) {
				if (cards.get(i).getRank() == cards.get(i+1).getRank() && 
					cards.get(i+1).getRank() == cards.get(i+2).getRank() &&
					cards.get(i+2).getRank() == cards.get(i+3).getRank() ) {
						rank = 7;
					}
			}
			
			// check for straight
			if (rank == 0) {
				if ((cards.get(4).getRank().ordinal() - cards.get(0).getRank().ordinal() == 4)  ||
				(cards.get(3).getRank().ordinal() - cards.get(0).getRank().ordinal() == 3 &&
				cards.get(4).getRank().ordinal() == 14 &&
				cards.get(0).getRank().ordinal() == 2) ) {
					rank = 4;
				}
			}
			
			// check for flush
			boolean flush;
			if (rank == 0 || rank == 4) {
				flush = true;
				for (int i=0; i < 4; i++) {
					if (debug) System.out.println("card1 suit: " + cards.get(i).getSuit() + ", card2 suit: " + cards.get(i+1).getSuit());
					if (cards.get(i).getSuit() != cards.get(i+1).getSuit()) {
						flush = false;
						break;
					}
				}
				if (flush && rank == 4)
					rank = 8; // straight flush
				else if (flush)
					rank = 5;
			}
			
			// check for a royal flush
			if (rank == 8 && cards.get(4).getRank().ordinal() == 14 &&
					cards.get(0).getRank().ordinal() == 10) {
				rank = 9;
			}			
		}		
		
		if (debug) System.out.println("Rank is: " + rank);
		handRanking = HandRanking.values()[rank];
	}
	
	public JPanel getHandPanel() 
	{
		handPanel.removeAll(); // clear the container for successive calls to this
		// generate and return panel
		for (int i=0; i < numCardsInHand; i++)
		{
			GraphicalCard gc = getCard(i);
			if (gc.getCardStatus() == CardStatus.FACEUP) {
				JLabel cardLabel = new JLabel(new ImageIcon(gc.getFrontOfCard()));
				handPanel.add(cardLabel);
				cardMap.put(cardLabel,  gc);
				cardLabel.addMouseListener(this);
			}
			else
			{
				// face down
				JLabel cardLabel2 = new JLabel(new ImageIcon(GraphicalCard.backOfCard));
				handPanel.add(cardLabel2);
				cardMap.put(cardLabel2, gc);
				cardLabel2.addMouseListener(this);
			}
			
		}
		
		return handPanel;
	}
	
	public void addCard(GraphicalCard gc) 
	{
		super.addCard(gc);
	}
	
	public GraphicalCard removeCard(int idx) 
	{
		return (GraphicalCard) super.removeCard(idx);
	}

	public GraphicalCard getCard(int idx)
	{
		return (GraphicalCard) super.getCard(idx);
	}
	
	public void fold(GraphicalCardDeck gcDeck) throws Exception {
		// remove all cards
		for (int i=numCardsInHand-1; i >= 0; i--) {
			GraphicalCard gCard = removeCard(i);
			if (gCard == null) {
				throw new Exception("Failed to remove card: " + i);
			}
			else
			{
				// return to Deck
				gcDeck.returnToDeck(gCard);
			}
		}
		
		// clear hashmap of jlabel, cards
		cardMap.clear();
	}

	public static void main(String[] args) 
	{
		try 
		{
			GraphicalCardDeck gcDeck = new GraphicalCardDeck();
			gcDeck.shuffle();
			
			// deal 5 cards
			GraphicalCardHand gch = new GraphicalCardHand(5);
			
			for (int i=0; i < 5; i++) 
			{
				GraphicalCard gcard = gcDeck.dealCard();
				gcard.setCardStatus(CardStatus.FACEUP);
				gch.addCard(gcard);
			}

	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,200);
	        frame.add(gch.getHandPanel());
	        frame.pack();
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //frame.addMouseListener(gch);
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (debug) {
			System.out.println("In mouse Clicked!");
			System.out.println("Component: " + e.getComponent());
		}
		if (e.getComponent() instanceof JLabel) {
			JLabel clickedLabel = (JLabel) e.getComponent();
			GraphicalCard gc = cardMap.get(clickedLabel);
			if (gc != null) {
				CardStatus status = gc.getCardStatus();
				if (status == CardStatus.FACEUP) {
					gc.setCardStatus(CardStatus.FACEDOWN);
					clickedLabel.setIcon(new ImageIcon(GraphicalCard.backOfCard));
				}
				else {
					gc.setCardStatus(CardStatus.FACEUP);
					clickedLabel.setIcon(new ImageIcon(gc.getFrontOfCard()));
				}
			}
		}            
		handPanel.paintAll(handPanel.getGraphics());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
