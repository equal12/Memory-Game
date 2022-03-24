package MemoryGame.model;
import MemoryGame.controller.*;
import MemoryGame.view.*;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.*;

public class Board
{
  public ArrayList<Integer> cards = new ArrayList<Integer>();
  public MemoryController controller;
  public int c1;
  public int c2;
  public int c1ID;
  public int c2ID;
  public int pressCounter = 0;
  public int AttemptCounter = 0;
  public boolean flag = false;
  public int numMatches;
  Random randomizer; // To randomize the the values of the cards

  public Board(int level, MemoryController controller)
  {
	  this.controller = controller;
	  switch (level){
			case 1: for (int i =0; i<6; i++){
						this.cards.add(i);
						this.cards.add(i);
					}
					numMatches=6;
					break;
			case 2: for (int i =0; i<8; i++){
						this.cards.add(i);
						this.cards.add(i);
					}
					numMatches=8;
					break;
			case 3: for (int i =0; i<10; i++){
						this.cards.add(i);
						this.cards.add(i);
					}
					numMatches=10;
					break;
				}

	//boards will be chosen based on level
	//create each card
	randomizer = new Random();
    System.out.println("Board constructor randomized the mapped cards values");
  }

  //Board constructor with no shuffle -- used for testing -- to know values
  public Board()
  {
	  cards = new ArrayList<Integer>();
	  for(int i=0; i < cards.size(); i++)
	  {
		  cards.add(i);
		  cards.add(i);
	  }

	  System.out.println("from Board constructor -- no shuffle");

  }


  public void shuffle()
  {
	  for (int i = 0; i <cards.size(); i++)
	  {
		  int swap_with = randomizer.nextInt(cards.size());
		  int a = cards.get(i);
		  cards.set(i,cards.get(swap_with));
		  cards.set(swap_with,a);
	  }
  }


  //public void deal()//may not be needed in the future
  //{
	////how it deals will be based on the level/mode
    //System.out.println("Board assigns the gird with the randomized values");
  //}

  // she said "you'll need to keep track of whether it's card 1 or card 2.
  // You can do this in the controller or in the Board class."

  public void selectedCard(int buttonID)
  {
    //counter?
    //when the button is clicked, increment a counter then call this method
    
 


    switch(pressCounter)
    {
		case 1: controller.onFlipCard(buttonID);
				c1 = cards.get(buttonID);
				c1ID = buttonID;
				//System.out.println("c1 = " + c1);
				break;
		case 2: controller.onFlipCard(buttonID);
				c2 = cards.get(buttonID);
				c2ID = buttonID;
				pressCounter = 0;
				controller.onCompare(c1, c2, c1ID, c2ID);
				//System.out.println("c2 = " + c2);
				break;
			}
		
	

	}

   // System.out.println("Identifying card 1 or card 2 selected");
  }


  //public boolean playAgain()//we need to pass the nuber of pairs --
  //  {
      //  int choice = JOptionPane.showConfirmDialog(mainFrame, "You Want To Play Again?", JOptionPane.YES_NO_OPTION);
       // return choice == JOptionPane.YES_OPTION;
    //}

//end board
