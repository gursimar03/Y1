package exam15;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Gursimar
 */
public class question1 {

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();

        int lowerLimit = 0;                 //To store the lower limit 
        int upperLimit = 0;
        int count = 0;
        int randomNumberGenerated = 0;
        int largestNumber = 0;
        int total = 0;
        double average = 0;
        boolean checker = false;

        System.out.print("Enter A Lower Limit:");
        lowerLimit = keyboard.nextInt();

        System.out.print("Enter a Upper Limit:");
        upperLimit = keyboard.nextInt();

        System.out.print("Please input the count of random number to be generated:");
        count = keyboard.nextInt();

        for(int i = 0 ; i < count ; i++){

            randomNumberGenerated = rand.nextInt(upperLimit - lowerLimit) + lowerLimit;

            System.out.print(randomNumberGenerated+ " ");


            //Question 1 (b) modification
            total += randomNumberGenerated;

            if(randomNumberGenerated > largestNumber){

                largestNumber = randomNumberGenerated;
            }

            //Question 1(c) modification
            if(randomNumberGenerated == lowerLimit){

                checker = true;
            }

        }
        System.out.println("");
        System.out.println("");

        average = (double) (total/count);   // calculating average 

        System.out.println("The highest number is "+largestNumber);
        System.out.printf("The average of all random numbers is %.2f",average);
        System.out.println("");
        System.out.println("The lowest limit was randomly generated: "+checker);

        keyboard.close();
    }
    
}
