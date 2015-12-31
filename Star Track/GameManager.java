 /*
   The code for the GameManager written entirely by James 
   Tam. A constructor to create the galaxy and to send a message
   to the galaxy to display its current state.
 */
import java.util.Scanner;
import java.util.Random;
public class GameManager
 {
    private Galaxy milkyWay;
    
    int computer_hull;
    int player_hull;

    boolean attack_phase;

    boolean after_attack; 
    // if attaks is fine, (hit), this one becomes true and AI moves :D 
    
    int skip_attack = 0;

    boolean cheat = false; 

    Scanner in = new Scanner(System.in);
    Random generator = new Random();
    

    //String choice;

    private StarShip humanPlayer;
    private StarShip computerPlayer;

     public GameManager()
     {
     // 
          milkyWay = new Galaxy ();
          player_hull = milkyWay.getPlayerHull();
          computer_hull = milkyWay.getComputerHull();

          attack_phase = milkyWay.attackLocationPlayer();

     }

     // try to chage method that chekcs hit, might help!!!
     public void  startProcessingCommands()
     {
    
      //if(GameStatus.gameLost==false && GameStatus.gameWon==false){

  
      while (milkyWay.hullCheck())
        {
        
      if(cheat)
        {
          GameStatus.hiddenMenu();
          char choice = in.next().charAt(0);

          if(GameStatus.cheatModeOn)
          {
            GameStatus.cheatMenuON();
            choice = in.next().charAt(0);
            if (choice == 'c')
              GameStatus.cheatModeOn=false;
            GameStatus.cheatMenuOFF();
              System.out.println("Cheat mode de-activated: Ship can be damaged");
          }
          
          else if (choice=='c')
          {
            GameStatus.cheatModeOn = true;
            System.out.println("Cheat mode activated: Ship is now invunerable to further attack");
          }

          if(choice=='d')
          {
            GameStatus.debugModeOn = true;
            System.out.println("Bebug mode activated: ");
          }

          cheat=false;
        }

        if (GameStatus.cheatModeOn)
          System.out.println("Cheat mode is on");

	      milkyWay.display();
        

        if(GameStatus.cheatModeOn)
          GameStatus.cheatMenuON();

        if(milkyWay.attackLocationPlayer())
        {
          attackMenu();
          
          if(milkyWay.getHit())
            milkyWay.moveAI();

          if(skip_attack==1)
          {
            milkyWay.display();
            System.out.println("\nYou skipped the attack!");
            movementOptions();
          }
        }

        if (milkyWay.attackLocationPlayer()==false)
        {
         movementOptions();
        }

       // milkyWay.moveAI();
        //player_hull += (generator.nextInt(40) +1);
      }
      
    }

// correct 1,9,
     public void movementOptions()
    {
        System.out.println("\nMOVEMENT MENU: OPTIONS"+"\n The numbers on the keypad represent the direction of movement");
        System.out.println("7 8 9" + "\n4   6" +"\n1 2 3");
        System.out.println("(5) pass on movement phase");
        int player_move = in.nextInt();
        System.out.println("You Entered: " + player_move);
        System.out.println("");

        if (player_move==0)
          System.exit(0); 

        if (player_move==7)
          milkyWay.movePlayer(-1,-1);

        if (player_move==8)
          milkyWay.movePlayer(-1,0);

        if (player_move==9)
          milkyWay.movePlayer(-1,1);

        if (player_move==4)
          milkyWay.movePlayer(0,-1);

        if (player_move == 5);
            milkyWay.movePlayer(0,0);
            // add skip 

        if (player_move==6)
            milkyWay.movePlayer(0,1);

        if (player_move==1)
            milkyWay.movePlayer(1,-1); 

        if (player_move==2)
          milkyWay.movePlayer(1,0);

        if (player_move==3)
          milkyWay.movePlayer(1,1); 

        if (player_move<=-1)
          cheat = true;
    }
    public void attackMenu()
    {
        System.out.println("\nATTACK MENU: OPTIONS"+"\nThe numbers on the keypad represent the direction of movement");
        System.out.println("7 8 9" + "\n4   6" +"\n1 2 3");
        System.out.println("(5) pass on movement phase");
        int player_choice = in.nextInt();
        System.out.println("You Entered: " + player_choice);
        System.out.println("");
        
        if (player_choice==0)
          System.exit(0); 

        if (player_choice==7)
          milkyWay.checkAttack(-1,-1);

        if (player_choice==8)
          milkyWay.checkAttack(-1,0);

        if (player_choice==9)
          milkyWay.checkAttack(-1,1);

        if (player_choice==4)
          milkyWay.checkAttack(0,-1);

        if (player_choice == 5);
        {
          milkyWay.checkAttack(0,0);
          skip_attack=1;
        }

        if (player_choice==6)
            milkyWay.checkAttack(0,1);

        if (player_choice==1)
            milkyWay.checkAttack(1,-1); 

        if (player_choice==2)
          milkyWay.checkAttack(1,0);

        if (player_choice==3)
          milkyWay.checkAttack(1,1);
    }



 }
// try to use static attributes for moves, hp, etc. might help  public(private) static int Name = 0 for example.