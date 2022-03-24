package MemoryGame.view;

import java.awt.event.*;
import MemoryGame.model.*;
import MemoryGame.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Mode extends JPanel implements MemoryUserInterface
{
	private JPanel mainPanel = new JPanel();
	private JPanel mainRulePanel;
	public JFrame mainFrame;
	private JFrame mainRuleFrame;
	private JPanel buttons = new JPanel();
	private JLabel title;
	private JLabel ruleTitle;
	private JLabel rulesText;
	private JLabel footer;
	private JLabel leaderBoard;
	private JLabel left;
	private JLabel right;
	private CardButton card;
	public ArrayList<CardButton> ButtonList = new ArrayList<CardButton>();
	private CardFace face = new CardFace();
	private Dimension window;
	private int numButtons;
	private String mode;
	public Board board;
	public ArrayList<ImageIcon> cardFaces;


	public void Level(int level, Board board)
	{
		this.board = board;
		 switch (level){
			case 1: numButtons = 12;
					mode = "Easy";
					window = new Dimension(900,1100);
					cardFaces = face.HalloweenCards();
					break;
			case 2: numButtons = 16;
					mode = "Medium";
					window = new Dimension(900,1100);
					cardFaces = face.ThanksgivingCards();
					break;
			case 3: numButtons = 20;
					mode = "Hard";
					window = new Dimension(1000,1100);
					cardFaces = face.ChristmasCards();
					break;

			case 4: numButtons = 0;
					mode = "Rules";
					createRuleFrame();
					break;
				}

		mainFrame = new JFrame("Memory Game - "+ mode);
        mainFrame.setPreferredSize(window);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        layout.setVgap(50);
        
        mainPanel.setBackground(new Color(80, 109, 132));
        mainPanel.setLayout(layout);

        title = new JLabel("Level: "+mode, SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 30));
        //title.setOpaque(true);
        title.setForeground(Color.WHITE);
        mainPanel.add(title, BorderLayout.PAGE_START);

        left = new JLabel("                  ");
        right = new JLabel("                 ");
       // sides.setOpaque(true);
        mainPanel.add(left, BorderLayout.LINE_START);
		mainPanel.add(right, BorderLayout.LINE_END);


		footer = new JLabel("Good Luck!", SwingConstants.CENTER); //the number will change later
		footer.setFont(new Font("Serif", Font.BOLD, 20));
		footer.setForeground(Color.WHITE);
		footer.setSize(500,500);
		//footer.setLocation(0,650);
		mainPanel.add(footer, BorderLayout.PAGE_END);

		// we can attempt later how to put it in different locations depending on how it looks
		// leaderBoard = new JLabel("Lowest attemps #: James", SwingConstants.RIGHT);
		// leaderBoard.setForeground(Color.WHITE);
		// leaderBoard.setLocation(500,600);
		// mainPanel.add(leaderBoard);


		GridLayout bLayout = new GridLayout(4,3);
		bLayout.setHgap(10);
		bLayout.setVgap(10);
		buttons.setLayout(bLayout);
		buttons.setOpaque(true);


		for (int i = 0; i <numButtons; i++)
		{
			int k = board.cards.get(i);
			card = new CardButton(i, board, cardFaces.get(k), face.frontCard, true);
			card.setBackground(new Color(212, 180, 153));
			card.setSize(10, 10);
			card.setOpaque(true);
			card.setBorderPainted(false);
			buttons.add(card);
			this.ButtonList.add(card);
		}


		mainPanel.add(buttons, BorderLayout.CENTER);

		mainFrame.add(mainPanel);
		mainFrame.pack();
        mainFrame.setVisible(true);


	}//end level


	public void createRuleFrame()
	{
		mainRuleFrame = new JFrame("Welcome to the Rules Page");
		mainRuleFrame.setPreferredSize(new Dimension(400, 500));
		mainRuleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		layout.setVgap(50);
		mainRulePanel = new JPanel();
		mainRulePanel.setBackground(new Color(80,109,132));
		mainRulePanel.setLayout(layout);
		ruleTitle = new JLabel("The rules for Holiday Memory game:", SwingConstants.CENTER);
		ruleTitle.setFont(new Font("Serif", Font.BOLD, 30));
		ruleTitle.setForeground(Color.WHITE);
		mainRulePanel.add(ruleTitle, BorderLayout.PAGE_START);
		rulesText = new JLabel("Rules for Holiday Memories", SwingConstants.CENTER); //the number will change later
		rulesText.setFont(new Font("Serif", Font.BOLD, 20));
		rulesText.setForeground(Color.WHITE);
		rulesText.setSize(500,500);
		mainRulePanel.add(rulesText, BorderLayout.PAGE_END);
		
		mainRuleFrame.add(mainRulePanel);
		mainRuleFrame.pack();
		mainRuleFrame.setVisible(true);

	}


	@Override
	public boolean playAgain()
	{
		int choice = JOptionPane.showConfirmDialog(mainFrame, "It Took You "+board.AttemptCounter+ " Attempts \nDo You Want To Play Again?", "Results", JOptionPane.YES_NO_OPTION);
       return choice == JOptionPane.YES_OPTION;
	}
	@Override
	public void showBack(int c)
	{
		for (CardButton button : ButtonList){
			if(c == button.getButtonNumber()){
				button.setFront();
			}
		}
	}
	@Override
	public void showFace(int c)
	{
		for (CardButton button : ButtonList){
			if(c == button.getButtonNumber()){
				button.setFace();
				button.revalidate();
				button.setVisible(true);
			}
		}
	}
	@Override
	public void disableButtons(int c)
	{
		for (CardButton button : ButtonList){
			if(c == button.getButtonNumber()){
				button.setEnabled(false);
				//System.out.println("disabled");
				
			}
		}
	}
}
