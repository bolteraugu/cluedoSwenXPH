package Java;

import java.util.ArrayList;
import java.util.List;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4597.b7ac3a910 modeling language!*/



// line 69 "model.ump"
// line 221 "model.ump"
public class Turn
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Turn Associations
  private Game game;
  private Player player;
  private Suggestion suggestion;
  private Accusation accusation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player getPlayer() {
	  return player;
  }
  
  public Turn(Game aGame, Player p)
  {
	  player = p;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create turn due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    suggestion = null;
    accusation = null;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeTurn(this);
    }
    game.addTurn(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeTurn(this);
    }
  }

  // line 73 "model.ump"
   public int rollDice(){
	   int dieRoll1 = (int)(Math.random() * 6); //random value between 0 and 5
	   int dieRoll2 = (int)(Math.random() * 6); //random value between 0 and 5
	   return ((dieRoll1+1) + (dieRoll2+1)); //Add 1 since we want a dice roll between 1 and 6 not 0 and 5
  }

  // line 76 "model.ump"
   public void makeSuggestion(CardSet c){
	   suggestion = new Suggestion(c);
  }
   
   public Suggestion getSuggestion() {
	   return suggestion;
   }
   
   // line 79 "model.ump"
   public void makeAccusation(CardSet c){
	   accusation = new Accusation(c);
  }
   
   public Accusation getAccusation() {
	   return accusation;
   }

  // line 83 "model.ump"
   public List<Integer> move(int steps, String direction, Board board){
	   int x = player.getToken().getX();
	   int y = player.getToken().getY();
	   
	   if (direction.equals("W")) {
		   player.getToken().setXPos(x-1);
	   }
	   else if (direction.equals("NW")) {
		   player.getToken().setXPos(x-1);
		   player.getToken().setYPos(y-1);
	   }
	   else if (direction.equals("N")) {
		   player.getToken().setYPos(y-1);
	   }
	   else if (direction.equals("NE")) {
		   player.getToken().setYPos(y-1);
		   player.getToken().setXPos(x+1);
	   }
	   else if (direction.equals("E")) {
		   player.getToken().setXPos(x+1);
	   }
	   else if (direction.equals("SE")) {
		   player.getToken().setXPos(x+1);
		   player.getToken().setYPos(y+1);
	   }
	   else if (direction.equals("S")) {
		   player.getToken().setYPos(y+1);
	   }
	   else if (direction.equals("SW")) {
		   player.getToken().setXPos(x-1);
		   player.getToken().setYPos(y+1);
	   }
	   List<Integer> coords = new ArrayList<Integer>();
	   coords.add(x);
	   coords.add(y);
	   board.getLocation(x, y).setPlayer(null);
	   board.getLocation(player.getToken().getX(), player.getToken().getY()).setPlayer(player);
	   
	   return coords;
  }

}