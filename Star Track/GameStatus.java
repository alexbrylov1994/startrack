/*********************************************************************************
 Author:  James Tam
 Version: March 9, 2004

 The sole purpose of this class to store global flags that indicate the current
 status of the game.
********************************************************************************/
import java.util.Scanner;
public class GameStatus
{
    public static boolean gameLost = false ;
    public static boolean gameWon = false ;
    public static boolean debugModeOn = false ;
    public static boolean cheatModeOn = false ;

    //Scanner in = new Scanner(System.in);

    public static void hiddenMenu()
    {
    	System.out.println("\nCheat Menu"+"\n(c)heat mode ON" + "\n(d)ebug mode ON");
    	System.out.println("Enter your choice: ");
        // might make statement false inside 
    }

    public static void cheatMenuON()
    {
        System.out.println("\nCheat Menu is on. "+"\n(c)heat mode OFF" + "\n(d)ebug mode ON");
    }
     public static void cheatMenuOFF()
    {
        System.out.println("\nCheat Menu is on. "+"\n(c)heat mode ON" + "\n(d)ebug mode ON");
    }
       
    public static void debugModeOn()
    {
        System.out.println("\nCheat Menu is on. "+"\n(c)heat mode ON" + "\n(d)ebug mode OFF");
    }

    public static void debugModeOFF()
    {
        System.out.println("\nCheat Menu is on. "+"\n(c)heat mode ON" + "\n(d)ebug mode ON");
    }

    public static void debugAndCheatON()
    {
        System.out.println("\nCheat Menu is on. "+"\n(c)heat mode OFF" + "\ndebug mode OFF"); 
    }

    public static void winMenu()
    {
        System.out.println("Congratulations, you destoyed Dr. James Tong's ship, and you saved entire galaxy including CPSC 233 students!");
    }

    public static void lostMenu()
    {
        System.out.println("GAME IS OVER, YOU LOST!");
    }


}

