/**
 * 
 */
package org.highlandschool.course;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author mdaconta
 *
 */
public class GraphicalCard extends Card {
	public static Image backOfCard;
	
	static {
		// load back of Card Image
		backOfCard = null;
		URL url = GraphicalCard.class.getResource("/classic-cards/b1fv.png");
		if (url != null) 
		{	
			try {
			    backOfCard = ImageIO.read(url);
			} catch (IOException e) {
				System.out.println("Error loading image: " + e.getMessage());
			}
		}
		else
		{
			System.out.println("Cannot find the image: b1fv.png");
		}
	}
	
	public enum CardStatus { FACEDOWN, FACEUP };
	
	protected CardStatus cardStatus;
	
	protected BufferedImage frontOfCard;
	
	public BufferedImage getFrontOfCard() {
		return frontOfCard;
	}

	public CardStatus getCardStatus() { return cardStatus; }
	
	public void setCardStatus(CardStatus status) { cardStatus = status; }
	
	public GraphicalCard(Suit suit, Rank rank) throws IOException{
		super(suit, rank);
		
		// load the image
		frontOfCard = null;
		URL url = this.getClass().getResource("/classic-cards/" + rank.toString() + "-" + suit.toString() + ".png");
		if (url != null)
		{
			frontOfCard = ImageIO.read(url);
		}
		else
		{
			System.out.println("Cannot find the image: " + rank.toString() + "-" + suit.toString() + ".png");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try 
		{
			GraphicalCard gcard = new GraphicalCard(Suit.SPADES, Rank.ACE);
			ImageIcon icon=new ImageIcon(gcard.getFrontOfCard());
			ImageIcon icon2 = new ImageIcon(GraphicalCard.backOfCard);
	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,200);
	        JLabel lbl=new JLabel();
	        lbl.setIcon(icon);
	        JLabel lbl2 = new JLabel();
	        lbl2.setIcon(icon2);
	        frame.add(lbl);
	        frame.add(lbl2);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
		}
	}

}
