package org.highlandschool.course;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PokerGame implements ActionListener {
	public static final int PLAYER_IDX = 0;
	public static final int COMPUTER_IDX = 1;
	
	public final boolean debug = true;
	
	public final String NEW_HAND = "New Hand";
	public final String FOLD_HAND = "Fold Hand";
	public final String NEW_CARDS = "New Cards";
	public final String SHOW_CARDS = "Show Cards";

	GraphicalCardTable gcTable;
	GraphicalCardHand playerHand;
	GraphicalCardHand computerHand;
	JPanel scorePanel;
	JPanel buttonPanel;
	JTextField messageField= new JTextField();
	JTextField playerScoreField = new JTextField(5);
	int playerScore = 0;
	JTextField computerScoreField = new JTextField(5);
	int computerScore = 0;
	Box gameBox = Box.createVerticalBox();
	
	public Box getGameBox() { return gameBox; }
	
	public PokerGame() throws IllegalArgumentException, IOException {
		gcTable = new GraphicalCardTable(2,5);
		
		GraphicalCardHand[] gcHands = gcTable.getGraphicalCardHands();
		playerHand = gcHands[PokerGame.PLAYER_IDX];
		computerHand = gcHands[PokerGame.COMPUTER_IDX];
		
		scorePanel = new JPanel(new FlowLayout());
		JLabel playerLabel = new JLabel("Player Score:");
		JLabel computerLabel = new JLabel("Computer Score:");
		scorePanel.add(playerLabel);
		playerScoreField.setEditable(false);
		playerScoreField.setText("" + playerScore);
		scorePanel.add(playerScoreField);
		scorePanel.add(computerLabel);
		scorePanel.add(computerScoreField);
		computerScoreField.setEditable(false);
		computerScoreField.setText("" + computerScore);
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton newHandButton = new JButton(NEW_HAND);
		newHandButton.addActionListener(this);
		JButton foldHandButton = new JButton(FOLD_HAND);
		foldHandButton.addActionListener(this);
		JButton newCardsButton = new JButton(NEW_CARDS);
		newCardsButton.addActionListener(this);
		JButton showCardsButton = new JButton(SHOW_CARDS);
		showCardsButton.addActionListener(this);
		buttonPanel.add(newHandButton);
		buttonPanel.add(foldHandButton);
		buttonPanel.add(newCardsButton);
		buttonPanel.add(showCardsButton);
		gameBox.add(gcTable);
		gameBox.add(scorePanel);
		gameBox.add(buttonPanel);
		gameBox.add(messageField);
	}
	
	public static void main(String[] args) {
		try {
			JFrame mainFrame = new JFrame();
			mainFrame.setTitle("5-Card Poker");
			
			PokerGame game = new PokerGame();

			mainFrame.add(game.getGameBox());
			mainFrame.pack();
			mainFrame.setVisible(true);
	        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (debug) {
			System.out.println("Command is: " + command);
		}
		switch (command) {
		case NEW_HAND:
			// deal a new hand
			try {
				gcTable.foldHands();
			} catch (Exception e1) {
				System.out.println("Error folding hand!");
				e1.printStackTrace();
			}
			gcTable.dealHands();
			gcTable.paintAll(gcTable.getGraphics());
			break;
		case FOLD_HAND:
			// fold your hand and deal another
			
			break;
		case NEW_CARDS:
			// get new cards
			
			break;
		case SHOW_CARDS:
			// show cards and score
			playerHand.evaluateRank();
			computerHand.evaluateRank();
			if (debug) {
				System.out.println("playerHand rank: " + playerHand.getHandRanking());
				System.out.println("computerHand rank: " + computerHand.getHandRanking());
			}
			
			if (playerHand.getHandRanking().ordinal() > computerHand.getHandRanking().ordinal()) {
				playerScore += 1;
				playerScoreField.setText("" + playerScore);
			}
			else if (computerHand.getHandRanking().ordinal() > playerHand.getHandRanking().ordinal())
			{
				computerScore += 1;
				computerScoreField.setText("" + computerScore);
			}
			break;
		}
		
	}

}
