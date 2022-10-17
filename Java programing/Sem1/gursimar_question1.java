import java.util.Scanner;
public class gursimar_question1 {

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);

        String str = "";
        int n = 0;

        System.out.println("Enter a String:");
        str = keyboard.nextLine();

        System.out.println("Enter a number:");
        n = keyboard.nextInt();

        int stringLength = str.length(); //str.length() functions allows us to get the length of the string , here str.
        int index = stringLength - n;    // stringLength - n will give us the index from which we have to print the substring

        for(int i = 0; i < n; i++){

            System.out.print(str.substring(index,stringLength));    //Printing substring(index,stringLength) 

        }

        keyboard.close();

    }
    
}
