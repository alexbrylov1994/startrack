import java.util.Random;
import java.util.Scanner;

/*
    Starting code provide by James Tam. Constructor(), generateCoordinates()
     and display() methods
*/

// As for AI, it's random but I made it really agressive, it makes the game a little bit more interesting and harder.  
// fix the bug when after movement of AI, you can't attack it, but wait...
//fix cheat bug 
public class Galaxy 
{
    public static final int SIZE = 4;
    private StarShip [][] grid;
   	private StarShip humanPlayer;
    private StarShip computerPlayer;

    int r;
    int c; 

    private int [] currentLocation;
    private int [] aiLocation;

    int damage;
    boolean regenerate = false;
    boolean hit;
    boolean hitAI; // hit for ai

    boolean skipAttack; 
    Random generator = new Random ();

        /*
      Starting code to initialize galaxy written by James Tam.
      Player's ship always in [0][0] the terran sector. The 
      computer ship location randomly generated.
    */
    public Galaxy ()
    {

  boolean squareOccupied = false;
	grid = new StarShip [SIZE][SIZE];
	Random generator = new Random ();
	Scanner in = new Scanner(System.in);
	int r=0;
	int c=0;
	int hull;

	char appearance;

	System.out.print("Computer ship hull value: ");

	hull = in.nextInt();

  while(hull<=0)
  {
    System.out.println("Please enter a positive hull value!");
    System.out.printf("Computer ship hull value:");
    hull= in.nextInt();
  }

	if (hull>400)
		hull = 400;

	computerPlayer = new StarShip(hull);
	computerPlayer.setAppearance(StarShip.DEFAULT_APPEARANCE);

	// Initialize all default locations to null.
	for (r = 0; r < SIZE; r++)
	{
	    for (c = 0; c < SIZE; c++)
	    {
		grid[r][c] = null;
	    }
	}


	humanPlayer = new StarShip('H');
	
	currentLocation = new int[2];


	grid[0][0] = humanPlayer;

	currentLocation[0]=0;
	currentLocation[1]=0;

	
         do
	 {
	     // Call to method randomly generate a set of 
             // (row, column coordinates somewhere within the 
             // galaxy).
	     generateCoordinates(computerPlayer);
	     if ((computerPlayer.getRow() == 0) && 
                 (computerPlayer.getColumn() == 0))
		 squareOccupied = true;
	     else
		 squareOccupied = false;
	 } while (squareOccupied == true); 
	 grid[computerPlayer.getRow()][computerPlayer.getColumn()] = computerPlayer;
    }

    /*
      Display method to display the galaxy (no grid) written entirely by 
      James Tam
    */ 

    public boolean getHit()
    {
    	return (hit);
    }
    public int getComputerHull(){

    	return (computerPlayer.getHullValue());
    }

    public int getPlayerHull(){

    	return (humanPlayer.getHullValue());
    }

    public int[] getArray()
    {
    	return(currentLocation);
    }

    public StarShip getPlayer()
    {
    	return (humanPlayer);
    }

    public StarShip getAI()
    {
    	return (computerPlayer);
    }

    public boolean hullCheck()
    {
    	boolean game_on = false;
        int pHull = humanPlayer.getHullValue();
        int aiHull = computerPlayer.getHullValue();

        if (pHull>=1 && aiHull>=1)
        	game_on = true;

        return game_on;

        
    }

    public void display ()
    {
	int r;
	int c;

  if(GameStatus.debugModeOn)
  {
    System.out.println("(Player's Location) " + "Row: " + currentLocation[0] + "\tColumn: " + currentLocation[1]);
    System.out.println("(Computer's Location) " + "Row: " + computerPlayer.getRow() + "\tColumn: " + computerPlayer.getColumn());
    if (attackLocationPlayer())
      System.out.println("You are in the attacking Region.");
  }

  if(attackLocationAI())
    {
    int damage = generator.nextInt(40)+1;
    if(GameStatus.cheatModeOn)
      damage = 0;

    humanPlayer.setHullValue(humanPlayer.getHullValue()-damage);
    System.out.println("Computer attack on player:  opponent attacks you for " +damage+ " damage");
    }

  if(hit)
    {
    int damage = generator.nextInt(40)+1;
    computerPlayer.setHullValue(computerPlayer.getHullValue()-damage);
    System.out.println("Player attack on computer-controlled ship: You attack opponent for " +damage + " damage");
    }

 	System.out.println("\n   0  1  2  3");

	for (r = 0; r < SIZE; r++)
	{
		System.out.print(r + " ");

	    for (c = 0; c < SIZE; c++)
	    {

		if (grid[r][c] != null)
		{
		    System.out.print("(" + grid[r][c].getAppearance()+")");
		}
		else
		{
		    System.out.print("( )");
		}
	    }
	    int testRow = computerPlayer.getRow();
	  	int testColumn = computerPlayer.getColumn();

	    System.out.println();
	}

  if(computerPlayer.getHullValue()>0 && humanPlayer.getHullValue()>0)
	 System.out.println("Computer's Hull: " + computerPlayer.getHullValue()+"\tPlayer's Hull: " +humanPlayer.getHullValue());

  if(computerPlayer.getHullValue()<=0)
  {
    GameStatus.winMenu();
    GameStatus.gameWon=true;
  }

  if(humanPlayer.getHullValue()<=0){
    GameStatus.lostMenu();
    GameStatus.gameLost = true;
  }

	}

    public void wipe()
    {
	int r;
	int c;
	for (r = 0; r < SIZE; r++)
        {
	    for (c = 0; c < SIZE; c++)
		grid[r][c] = null;
		}    
	}
    /*
      Method to randomly generate coordinates for a ship written entirely by
      James Tam.
     */

   // !!!!!!!!!!!!!!! Use it to check it it works and then use this to make sure thhat ships doesn't overlap
   

   public void movePlayer(int row,int column)
    {
	// Get the current Row/Col coordinates of the Tardis

	int currentRow = currentLocation[0];
	int currentColumn = currentLocation[1];

	int oldRow = currentRow;
	int oldColumn = currentColumn;
	// store the pair of coordinates.
	row += oldRow ;
	column += oldColumn;

	if (row > 3)
		row = 3;

	if (row < 0)
		row =0;

	if (column > 3)
		column = 3;

	if (column <0)
		column=0;

	if(grid[row][column]==grid[computerPlayer.getRow()][computerPlayer.getColumn()])
		System.out.println("You can go there. You can't ram the enemy ship");
	else
	{
	currentLocation = grid[currentRow][currentColumn].calculateCoordinates(row,column);

	// Update temporary values with current location
	currentRow = currentLocation[0];
	currentColumn = currentLocation[1];

	// Copy the reference to the tardis from the old location to the new one.
	grid[currentRow][currentColumn] = grid[oldRow][oldColumn];

	
	if ((currentRow == oldRow) && (currentColumn == oldColumn))
	{
	    System.out.println("You Are Skipped Move");
	}
	else
        {
	    // old location no longer refers to the tardis because the tardis has 'moved' off of it.
	    grid[oldRow][oldColumn] = null;
	}
}
	//display();
	moveAI();
	//attackMethod();
    }


    public void generateCoordinates(StarShip ship)
    {
	int r =3;//generator.nextInt(SIZE); // Change values to SIZE letter on, it's just a check for now!!!!!!!!!!
	int c =3;//generator.nextInt(SIZE);
	// test  00  03V  30V  33v Check.
	// test 2 02V  32V  20V  23V Check.
	//test 3  22
	ship.setRow(r);
	ship.setColumn(c);
    }

    // sometimes AI Makes not illegale moves, try to trace it and fix it.



   public void moveAI()
    {

    int result = generator.nextInt(9)+1;

    /// check for r and c at the beginning of the file, it can be causing some error.

  	if (result==7) {r = -1; c = -1;}

    if (result==8) {r =-1; c =0;}

  	if (result==9) { r =-1; c =1;}

  	if (result==4) {r =0; c =-1;}

  	if (result==5) {r =0; c =0;}

  	if (result==6) {r =1; c =-1;}

    if (result==1) {r =1; c =-1;}

    if (result==2) {r =1; c =0;}

  	if (result==3) {r =1; c =1;}

  	int currentRow =  computerPlayer.getRow();
    int currentColumn = computerPlayer.getColumn();
	
  	int oldRow = currentRow;
  	int oldColumn = currentColumn;

  	int newRow = oldRow + r;
  	int newColumn  = oldColumn + c;

  	if (newRow > 3)
  		newRow = 3;

  	if (newRow < 0)
  		newRow =0;

  	if (newColumn > 3)
  		newColumn = 3;

  	if (newColumn<0)
  		newColumn=0;
  	

  	if(grid[newRow][newColumn]==grid[currentLocation[0]][currentLocation[1]])
  		System.out.println("U can't go there,baby!");
  	else 
  	{
  		// just for now 
  	// int hp = computerPlayer.getHullValue();
  	// hp += 5; //generator.nextInt(41);

  	// if (hp > 100)
  	// 	hp = 400;
  	// computerPlayer.setHullValue(hp);

  	computerPlayer.setRow(newRow);
  	computerPlayer.setColumn(newColumn);

  	grid[newRow][newColumn] = computerPlayer;


	if ((currentRow == newRow) && (currentColumn == newColumn))
	    System.out.println("Rematerializing in same location");
	
	else
		{
	    // old location no longer refers to the tardis because the tardis has 'moved' off of it.
	    grid[oldRow][oldColumn] = null;
		}

   }
	}
	public boolean checkAttack(int r, int c) 

	{
    // write checks. When ai is on borders, it checks outside boarders and the game is fucked!!!
    // write something that return 2 values. Something like, if then Miss is false, and if miss is true it will generate more hull for the ship!
		if(r==0 && c==0)
    {
      if(computerPlayer.getHullValue()<400)
      {
      computerPlayer.setHullValue(computerPlayer.getHullValue()+5);
      }
      hit = false;
    }

    if(grid[currentLocation[0]+r][currentLocation[1]+c] == grid[computerPlayer.getRow()][computerPlayer.getColumn()])
			hit = true;

		else
    {
			hit = false;
      if(computerPlayer.getHullValue()<400)
      {
      computerPlayer.setHullValue(computerPlayer.getHullValue()+5);
      }
    }
		return(hit);
		
	}

  // public boolean checkAttackAI()
  // {
  //   hitAI = false;

  //   int column = (computerPlayer.getColumn() - humanPlayer.getColumn());
  //   int row = (computerPlayer.getRow() - humanPlayer.getRow());

  //   if(grid[computerPlayer.getRow() + row][computerPlayer.getColumn()+column]==grid[currentLocation[0]][currentLocation[1]])
  //     hitAI = true;

  //   return(hitAI);

  // }

  // method for Player, kills AI
	// public void attackMethod(){
	// 	if (hit)
	// 	{	//set between 1-20 =4-100 damage, suka!
	// 		int amount_of_damage = 2;
	// 		computerPlayer.setHullValue(computerPlayer.getHullValue()-amount_of_damage);
	// 	}
	// }

  // // aatack method for AI, kill player 
  // public void attackMethodAI()
  // {
  //   if(attackLocationAI())
  //   {
  //     int amount_of_damage = 5;
  //     humanPlayer.setHullValue(humanPlayer.getHullValue()-amount_of_damage);
  //   }

  // }

public boolean attackLocationPlayer()    {
   boolean placeTaken = false;

   int aiRow=computerPlayer.getRow();
   int aiColumn=computerPlayer.getColumn();

   int humanRow=currentLocation[0];
   int humanColumn=currentLocation[1];
   // Change for when it's on borders, add two checks(Diagonals)
   // check for Problem, when a ship is in corners
   if (aiRow==0 && aiColumn==0)
   		if(grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow+1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true;

   // This works well! 
  	if (aiRow==0 && aiColumn==3)

   		if(grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow+1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true;

   	//works fine!
	if (aiRow==3 && aiColumn==0)

   		if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;
   	// works
  	if (aiRow==3 && aiColumn==3)

   		if(grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true;




    // checks if ai is located on borders, but no it conrners
   	if (aiRow==0 && aiColumn!=0 && aiColumn!=3)

   		if(grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow+1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow+1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;


	if (aiRow==3 && aiColumn!=0 && aiColumn!=3)

   		if(grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow-1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow-1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;


	if (aiColumn==0 && aiRow!=0 && aiRow!=3)

   		if(grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow+1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow-1][aiColumn+1]==grid[humanRow][humanColumn])
   			placeTaken = true;


	if (aiColumn==3 && aiRow!=0 && aiRow!=3)

   		if(grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn])
   			placeTaken = true; 

   		else if(grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true;
   

   		else if(grid[aiRow+1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   		else if(grid[aiRow-1][aiColumn-1]==grid[humanRow][humanColumn])
   			placeTaken = true;

   	// checks if ai player is located in the middle of grid (outside bounders and not in corners of the grid)

   	if (aiRow!=0 && aiColumn!=0 && aiRow!=3 && aiColumn!=3)

		if(grid[aiRow-1][aiColumn]==grid[humanRow][humanColumn]) // 8
			placeTaken = true; 

		else if (grid[aiRow-1][aiColumn+1]==grid[humanRow][humanColumn]) //9
			placeTaken = true; 

		else if (grid[aiRow][aiColumn+1]==grid[humanRow][humanColumn]) // 6
			placeTaken = true; 

		else if (grid[aiRow+1][aiColumn+1]==grid[humanRow][humanColumn])// 3
			placeTaken = true; 

		else if (grid[aiRow+1][aiColumn]==grid[humanRow][humanColumn])//2
			placeTaken = true; 

		else if (grid[aiRow+1][aiColumn-1]==grid[humanRow][humanColumn])//1
			placeTaken = true;

		else if (grid[aiRow][aiColumn-1]==grid[humanRow][humanColumn])//4
			placeTaken = true; 

		else if (grid[aiRow-1][aiColumn-1]==grid[humanRow][humanColumn])//7
			placeTaken = true; 

   return placeTaken;

  	// test is checking if u have 2 things on one location. maybe use it to check it space it take or not. 
  	// just saying  
   // write the same thing for AI, I know Alex, embrace your self....
   }



public boolean attackLocationAI()    {
   boolean placeTaken = false;

   int aiRow=computerPlayer.getRow();
   int aiColumn=computerPlayer.getColumn();

   int humanRow=currentLocation[0];
   int humanColumn=currentLocation[1];
   // Change for when it's on borders, add two checks(Diagonals)
   // check for Problem, when a ship is in corners
   if (humanRow==0 && humanColumn==0)
   		if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   // This works well! 
  	if (humanRow==0 && humanColumn==3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   	//works fine!
	if (humanRow==3 && humanColumn==0)

   		if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;
   	// works
  	if (humanRow==3 && humanColumn==3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;




    // checks if ai is located on borders, but no it conrners
   	if (humanRow==0 && humanColumn!=0 && humanColumn!=3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanRow==3 && humanColumn!=0 && humanColumn!=3)

   		if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanColumn==0 && humanRow!=0 && humanRow!=3)

   		if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanColumn==3 && humanRow!=0 && humanRow!=3)

   		if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;
   

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   	// checks if ai player is located in the middle of grid (outside bounders and not in corners of the grid)

   	if (humanRow!=0 && humanColumn!=0 && humanRow!=3 && humanColumn!=3)

		if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn]) // 8
			placeTaken = true; 

		else if (grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn]) //9
			placeTaken = true; 

		else if (grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn]) // 6
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])// 3
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])//2
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])//1
			placeTaken = true;

		else if (grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])//4
			placeTaken = true; 

		else if (grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])//7
			placeTaken = true; 

   return placeTaken;

  	// test is checking if u have 2 things on one location. maybe use it to check it space it take or not. 
  	// just saying  
   // write the same thing for AI, I know Alex, embrace your self....
   }
}