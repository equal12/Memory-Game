package MemoryGame.controller;
import MemoryGame.model.*;
import MemoryGame.view.*;

import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.event.*;

public class MemoryController
{

  public Board board;
  private StartScreen menu;
  protected MemoryUserInterface userInterface;
  private Mode mode;
  private int level;


  public MemoryController(MemoryUserInterface ui)
  {
    //this.board = b;
    this.userInterface = ui;
    //System.out.println("MemoryController Constructor");
  }
  
  
  public void startNewGame(int level, Mode m)
  {
    // need to open grid
    // have the cards (from a randomized 2D array) be assigned to the buttons
    // have the cards face up with a boolean -- true
    // timer for 10 seconds
    // have the cards flip to be face down with a boolean - false for a showFace value	
    this.board = new Board(level, this);
    this.mode = m;
    this.level = level;
    board.shuffle();
    mode.Level(level, board);

    
    
    System.out.println("New Game Mode started");
  }

  public void onFlipCard(int buttonID) // this will change the button in the view part
  {
    // use a showFace method in interface

	userInterface.showFace(buttonID);
	userInterface.showFace(buttonID);
   
  }
  
  
  
  public void onCompare(int c1, int c2, int c1ID, int c2ID)
  {
	final Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				@Override
				public void run(){
	
					if ( c1 != c2 )
					{
						userInterface.showBack(c1ID);
						userInterface.showBack(c2ID);
						//System.out.println("c1 "+c1+"!= c2 " + c2);
					}
					else 
					{
						//System.out.println("c1 "+c1+"= c2 " + c2);
						onMatch(c1ID, c2ID);
					}
					board.AttemptCounter++;//attempts counter
					timer.cancel();
				}
			}, 1000);
	  
  }

  public void onMatch(int c1ID, int c2ID)
  {
				  
	  // when values are the same -- remove from interface
	  // when values are not the same -- card 1 and card 2 .showFace() -- false
	  board.numMatches--;
	  userInterface.disableButtons(c1ID);
	  userInterface.disableButtons(c2ID);
	  if (board.numMatches == 0){
		  this.onEndGame();
	  }

  }



  public void onEndGame()
  {
	  //when game knows that all pairs have been found
	  //determine the counter value
	  //display counter value and option to play again or return to home screen
	  // call .playAgain() method
	  boolean playAgain = userInterface.playAgain();
	  System.out.println(level);
	  if(playAgain){
		 mode.mainFrame.dispatchEvent(new WindowEvent(mode.mainFrame, WindowEvent.WINDOW_CLOSING));
		 try{
			menu = new StartScreen();
		 }
		 catch(Exception e){}
	  }
	  
  }



}//end MemoryController
