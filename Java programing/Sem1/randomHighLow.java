import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public class randomHighLow {

    public static void main(String[] args) throws IOException {

        try {
            Scanner keyboard = new Scanner(System.in);
            Random rand = new Random();

            System.out.println("Please enter \"high\" number : ");
            int high = keyboard.nextInt();

            System.out.println("Please enter \"low\" number : ");
            int low = keyboard.nextInt();

            int mean = (high + low) / 2;

            int randomNumber = rand.nextInt(high - low + 1) + low;

            if (mean > randomNumber) {
                System.out.print("Mean " + mean + " is greater than " + randomNumber);
            } else if (mean < randomNumber) {
                System.out.print("Mean " + mean + " is less than " + randomNumber);
            } else {
                System.out.println("Mean " + mean + " is equal to " + randomNumber);
            }
            keyboard.close();
        } catch (Exception e) {

            System.out.println("Error");
           
        }
    }

}
