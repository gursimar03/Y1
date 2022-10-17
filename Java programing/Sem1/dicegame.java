import java.util.Random;
import java.util.Scanner;

public class dicegame {

    public static void main(String[] args) {
        
        Random random = new Random();
        Scanner keyboard = new Scanner(System.in);

        int playerDice = 0;
        int cpuDice = 0;
        int exit = 1;
        int numberOfGamesWon = 0;
        int numberOfGames = 0;
        String playerName = " ";

        System.out.println("Please enter your name :");
        playerName = keyboard.nextLine();

        
        do{
        System.out.println("Press enter to Roll the dice ");
        keyboard.nextLine();
        playerDice = random.nextInt(5) + 1;
        cpuDice = random.nextInt(5) + 1;

        System.out.println("Your Dice number :"+playerDice);
        System.out.println("CPU Dice number  :"+cpuDice);
        
        if(playerDice > cpuDice){
            
            System.out.println("------"+playerName+" won------");
            numberOfGamesWon++;
        }
        else if(playerDice < cpuDice){

            System.out.println("-------CPU won-----");
        }
        else{

            System.out.println("-------its a draw-------");
        }
        numberOfGames++;
        System.out.println("---------------------------");
        System.out.println("IF you want to exit press 0.");
        exit = keyboard.nextInt();
        keyboard.nextLine();
        }while(exit != 0);
        System.out.println(" ");
        System.out.println("Number of games played :"+numberOfGames);
        System.out.println("Number of games Won    :"+numberOfGamesWon);
        System.out.println("Number of games Lost   :"+(numberOfGames-numberOfGamesWon));
        keyboard.close();
    }
    
}
