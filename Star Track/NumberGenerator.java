import java.util.Random; 
public class NumberGenerator
{
    public static void main(String[] args)
    {
		Random genenerator = new Random();
		int a = genenerator.nextInt(3)+1 ;
		System.out.println(a);
    }
}
