import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a number to be reversed :");
        int number = keyboard.nextInt(); 
        int reverse = 0;
        while (number != 0) {
            int remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number = number / 10;
        }
        System.out.println("The reverse number : " + reverse);
        keyboard.close();
    }

}