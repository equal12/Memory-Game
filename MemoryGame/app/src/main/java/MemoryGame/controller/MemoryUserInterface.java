package MemoryGame.controller;
import MemoryGame.model.*;

public interface MemoryUserInterface
{
  public void showFace(int c);
  public void showBack(int c);
  public boolean playAgain();
  public void disableButtons(int c);//should be boolean

}
