import java.util.Random;
import java.util.Scanner;

public class highLow {

    public static void main(String[] args) {

        //Making Random and Scanner Objects
        Random random = new Random();
        Scanner keyboard = new Scanner(System.in);

        //All variable Initilizations
        int high = 0;
        int low = 0;
        int guess = 0;
        int randomNumber = 0;

        System.out.print("Please enter a low number : ");
        low = keyboard.nextInt();

        System.out.print("Please enter a high number : ");
        high = keyboard.nextInt();

        //Genrating Random number
        randomNumber = random.nextInt(high - low + 1) + low;

        // random.nextInt(10)
        
        while (guess != randomNumber) {

            System.out.println(" ");
            System.out.print("Please enter your guess :");
            guess = keyboard.nextInt();
            
            if (randomNumber > guess){

                System.out.println("Your guess was too low!");
            
            } else if (randomNumber < guess){

                System.out.println("Your guess was too High!");
            
            } else {
                
                System.out.println(" ");
                System.out.println("You guessed it correctly! The number is " + randomNumber);
            }
        }
        
        //Closing Scanner Object
        keyboard.close();

    }
}