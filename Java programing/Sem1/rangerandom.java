import java.util.Random;
import java.util.Scanner;

class rangerandom {
     
    public static void main (String args[]){

        Random rand = new Random();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the range of the random number to be genrated");
        
        System.out.println("Minimum");
        int min = keyboard.nextInt();
        
        System.out.println("Maximum");
        int max = keyboard.nextInt();
        
        
        int r = rand.nextInt(max - min) + min;

        System.out.println(r);

        keyboard.close();
    }
}