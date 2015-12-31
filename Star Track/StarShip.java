import java.util.*;

/*
  The code for this class written entirely by James Tam: the 
  constructor() and the display() method.
*/
public class StarShip
{
    public static final int MAX_HULL = 400;
    public static final char DEFAULT_APPEARANCE = 'C';
    private char appearance;
    private int hullValue;
    private int row;
    private int column;
    
    public StarShip ()
    {
	   appearance = DEFAULT_APPEARANCE;
	   hullValue = MAX_HULL;
    }

    public StarShip (int hull)
    {
	   appearance = DEFAULT_APPEARANCE;
	   hullValue = hull;
    }

    public StarShip (char newAppearance)
    {
	this();
	appearance = newAppearance;
    }


    public char getAppearance ()
    {
	return appearance;
    }

    public int getColumn()
    {
	return(column);
    }

    public int getRow()
    {
	return(row);
    }

    public int getHullValue()
    {
	return(hullValue);
    }   

    public void setAppearance(char newAppearance)
    {
	appearance = newAppearance;
    }

    public void setColumn(int newColumn)
    {
	column = newColumn;
    }

    public void setRow(int newRow)
    {
	row = newRow;
    }

    public void setHullValue(int hull)
    {
    hullValue = hull;
    }

    public int[] calculateCoordinates(int row, int column)
    {

    Random aGenerator = new Random();
    int [] newCoordinates = new int[2];
    
    newCoordinates[0] = row;
    newCoordinates[1] = column ;

    return(newCoordinates);

    }
}