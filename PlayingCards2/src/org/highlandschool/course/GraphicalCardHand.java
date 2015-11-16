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
	}
	
	public JPanel getHandPanel() 
	{
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
