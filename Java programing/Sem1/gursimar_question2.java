import java.util.Scanner;
public class gursimar_question2 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        String word = "";
        String sepretor = "";
        int count = 0;

        System.out.println("Enter a word");
        word= keyboard.nextLine();
        
        System.out.println("Enter the sepretor:");
        sepretor = keyboard.nextLine();

        System.out.println("Enter count:");
        count = keyboard.nextInt() - 1 ; // Decrimenting count by 1 as we do not need to print sepretor in the end

        for(int i = 0 ; i < count; i++){

            System.out.print(word+sepretor);
        }
        System.out.print(word);

        keyboard.close();
    }
    
}
