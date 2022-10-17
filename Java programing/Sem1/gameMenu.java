/**
 * Name: Gursimar Singh Ply
 * Class: SD1C
 * Project 1 
 * 
 * Question 1) Dice game 
 * Question 2) Acey Ducey
 * Question 3) Game of my choice (Black jack)
 */


 /**        SUMMARY OF THE CODE 
  *     
        mainMenu() - This function is the main fuction of the whole program which handels all games , 
        when asked by user it calls the respective fuction to start the perticular game 

            -------------------Que 1--------------
        diceGame() - Dice game fuction is for the first question dice game which will run so that user can play dice game 
            --------------------------------------

            -------------------Que 2----------------

        aceyDeucy() - this fuction is the beginning of the second game Acey Ducey which will ask the user the amount to put in the pot

        deckPicker() - this fuction picks two cards that will be shown to user so that the user can bet on the next card

        faceCardCheck() - Returns the name of the card if the card is a face card 

        higherLower() - when both the cards are equal or adjecant, it handels that senario of the game.

        guess() - is the fuction which is used to put bet if the next card is in middle of two cards or not.



            --------------------Que 3----------------
        
        blackJackMenu() - this is the menu of black jack.

        deck() - this fuction will pick cards when the player chooses to hit 

        choice() - this fuction will ask the user if he wants to hit or stand 

        blackJack() - when a player gets blackJack this fuction is called.

        lose() - this fuction is called when the player looses the game 

        dealerPull() - this function deals when its dealers turn to draw cards, it will automatically decide when dealer should stop
        drawing cards to win. 

        dealerBurst() - when dealer goes above the card range this fuction is called to show specific output

        restartMenu() - this fuction asks the user if he wants to restart the game or go to main menu.




        -----------------------------------------------------------

        endScreen()- Is called when the a game is exited and it resets all the value.

        ruleSet() - has all the rules for each and every game for the user.

  */


import java.util.Scanner;
import java.util.Random;

public class gameMenu {

    public static final int cardRange = 21;

    public static String playerName = " ";              //To store player name 
    public static int totalPlayerCardValue = 0;         //To store total value of players card for game 3 Black Jack
    public static int cpuCard = 0;                      //To store total value of CPU card for game 3 Black Jack
    static int pot = 0;                                 //To store total value of pot for game 2 Acey Ducey
    static int card1 = 0;                               //To store value of first card for game 2 Acey Ducey
    static int card2 = 0;                               //To store value of second card for game 2 Acey Ducey
    static int hiddenCard = 0;                          //To store value of hidden card for game 2 and 3 
    static int maxCard = 0;                             //To store value of card with higher value 
    static int minCard = 0;                             //To store value if card with low value
    static int[] playerWon = { 0, 0 };                  //To store the value of how much a player has earned 
    static String cardNameOne = "";                     //To store face name of first card 
    static String cardNameTwo = "";                     //To store face name of second card

    public static void main(String[] args) {

        mainMenu();     //TO go to main menu of the game 

    }

    public static void mainMenu() {     //Main menu of games to 

        Scanner keyboard = new Scanner(System.in);

        System.out.println("GAME MENU");
        System.out.println("1: Dice Game");
        System.out.println("2: Acey Deucey");
        System.out.println("3: Black Jack");
        System.out.println("4: Rule Set");
        System.out.println("0: To Quit");
        System.out.println("---------------");
        System.out.print("Enter the Number to play the game :");

        int menuKey = keyboard.nextInt();

        switch (menuKey) {

            case 1:
                diceGame();
                break;

            case 2:
                aceyDeucey();
                break;

            case 3:
                blackJackMenu();
                break;

            case 4:
                ruleSet();
                break;

            case 0:
                System.out.println("Thank you! have a nice day.");
                System.exit(0);
                break;

            default:
                System.out.println("Input Error choose a correct option \n \n");
                mainMenu();
                break;
        }

        keyboard.close();
    }

    // DICE GAME QUESTION 1 ************************************************
    public static void diceGame() {

        Random random = new Random();
        Scanner keyboard = new Scanner(System.in);

        int playerDice = 0;
        int cpuDice = 0;
        int exit = 1;
        int numberOfGamesWon = 0;
        int numberOfGames = 0;
        String playerName = " ";

        System.out.print("\033[H\033[2J");
        System.out.flush();
        //Code referenced from https://stackoverflow.com/questions/2979383/how-to-clear-the-console

        System.out.println("Please enter your name :");
        playerName = keyboard.nextLine();

        do {
            System.out.println("Press enter to Roll the dice ");
            keyboard.nextLine();
            playerDice = random.nextInt(5) + 1;
            cpuDice = random.nextInt(5) + 1;

            System.out.println("Your Dice number :" + playerDice);
            System.out.println("CPU Dice number  :" + cpuDice);

            if (playerDice > cpuDice) {

                System.out.println("------" + playerName + " won------");
                numberOfGamesWon++;

            } else if (playerDice < cpuDice) {

                System.out.println("-------CPU won-----");

            } else {

                System.out.println("-------its a draw-------");

            }

            numberOfGames++;

            System.out.println("---------------------------");
            System.out.println("IF you want to exit press 0.");
            exit = keyboard.nextInt();

            keyboard.nextLine();

        } while (exit != 0);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(" ");
        System.out.println("Number of games played :" + numberOfGames);
        System.out.println("Number of games Won    :" + numberOfGamesWon);
        System.out.println("Number of games Lost   :" + (numberOfGames - numberOfGamesWon));
        endScreen();
        keyboard.close();

    }
    // END OF DICE GAME ***********************************


    // ACEY DEUCEY ************************************************
    public static void aceyDeucey() {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-------------------------------");
        System.out.println("Welcome to Acey-Ducey");
        System.out.println("Enter the amount to put in pot");
        System.out.println("-------------------------------");
        pot = keyboard.nextInt();

        deckPicker();

        keyboard.close();
    }

    public static void deckPicker() {

        Random random = new Random();

        card1 = random.nextInt(13) + 1;
        card2 = random.nextInt(13) + 1;
        hiddenCard = random.nextInt(13) + 1;

        maxCard = Math.max(card1, card2);
        minCard = Math.min(card1, card2);

        cardNameOne = faceCardCheck(card1);
        cardNameTwo = faceCardCheck(card2);
        
        System.out.println("Pot Value: " + pot);
        System.out.println("");
        System.out.println("-----*********************-----");
        System.out.println("First card :" + card1 + " " + cardNameOne);
        System.out.println("Second card :" + card2 + " " + cardNameTwo);
        System.out.println("-----*********************-----");

        if(pot <= 0){

            System.out.println("Pot got empty!");
            endScreen();
        }


        if (card1 == card2 || (card1 - card2) == 1  || (card1 - card2) == -1) {

            higherLower();

        } else {

            guess();

        }

    }

    static String faceCardCheck(int card) {

       String name = " ";

            switch (card) {

                case 1:
                    name = "Ace";
                    break;

                case 11:
                    name  = "Joker";
                    break;

                case 12:
                    name = "Queen";
                    break;

                case 13:
                    name = "King";
                    break;
                
                default: name = "";
                    break;

            }

        return name;
        
    }

    public static void higherLower() {

        Scanner keyboard = new Scanner(System.in);

        for (int currentPlayer = 0; currentPlayer < 2; currentPlayer++) {

            System.out.println("");
            System.out.println("Press 1 to bet on higher");
            System.out.println("Press 2 to bet on lower");
            System.out.println("Enter 0 to exit");
            int choice = keyboard.nextInt();

            int bet = 0;

            if (choice == 1) {

                System.out.println("How much would you like to bet?");
                bet = keyboard.nextInt();
                pot += bet;

                if (hiddenCard > card1 || minCard > card2) {

                    System.out.println("Correct Guess! Player" + (currentPlayer + 1));
                    System.out.println("Card Pulled"+hiddenCard);
                    
                    playerWon[currentPlayer] += bet * 2;
                    System.out.println("Earned :"+playerWon[currentPlayer]);
                    
                    pot -= bet * 2;

                    deckPicker();

                } else {

                    System.out.println("Unlucky, Wrong guess Player " + (currentPlayer + 1));
                    System.out.println(hiddenCard);
                    pot += bet;

                    deckPicker();
                }

            } else if (choice == 2) {

                System.out.println("How much would you like to bet?");
                bet = keyboard.nextInt();
                pot += bet;

                if (hiddenCard > card1 || minCard > card2) {

                    System.out.println("Correct Guess! Player" + (currentPlayer + 1));
                    System.out.println(hiddenCard);
                    playerWon[currentPlayer] += bet * 2;
                    System.out.println("Earned :"+playerWon[currentPlayer]);
                    pot -= bet * 2;

                    deckPicker();

                } else if (bet == 0) {

                    endScreen();

                } else {

                    System.out.println("Unlucky, Wrong guess Player " + (currentPlayer + 1));
                    System.out.println(hiddenCard);
                    pot += bet;

                    deckPicker();
                }
            }
        }
        keyboard.close();
    }

    public static void guess() {

        Scanner keyboard = new Scanner(System.in);

        int bet = 0;
        do {
            for (int currentPlayer = 0; currentPlayer < 2; currentPlayer++) {

                System.out.println("");
                System.out.println("Player " + (currentPlayer + 1));
                System.out.println("Bet on the next card if you think it will lie between the drawn two cards.  ");
                System.out.println("Enter -1 to exit to main menu ");
                System.out.println("Enter 0 to pass:");
                bet = keyboard.nextInt();

                if (bet >= 0) {
                    

                    if (bet > 0 && bet <= pot) {

                        if (hiddenCard > minCard && hiddenCard < maxCard) {

                            System.out.println("");
                            System.out.println("you won!");
                            
                            playerWon[currentPlayer] += bet * 2;
                            System.out.println("Earned :"+playerWon[currentPlayer]);

                            pot -= bet * 2;
                            System.out.println(hiddenCard);
                            
                            deckPicker();

                        } else {

                            System.out.println("");
                            System.out.println("you lost");
                            System.out.println("Lost :"+ bet*2);
                            pot += bet * 2;

                            if (currentPlayer == 1) {

                                System.out.println(hiddenCard);
                                deckPicker();

                            }

                            System.out.println("");
                            System.out.println("-----*********************-----");
                            System.out.println("First card :" + card1 + " " + cardNameOne);
                            System.out.println("Second card :" + card2 + " " + cardNameTwo);
                            System.out.println("-----*********************-----");
                        }

                    } else if (bet == 0) {

                        System.out.println("Passed the turn to Player " + (currentPlayer + 2));

                    } else {

                        System.out.println("Wrong input.");

                    }
                } else if (bet == -1) {

                    endScreen();

                } else {

                    System.out.println("Wrong input");

                }

            }

        } while (bet != -1);

        keyboard.close();
        mainMenu();

    }

    // END OF ACEY DEUCEY *****************************************





    // GAME OF MY CHOICE
    // BLACK JACK QUESTION 3
    // ****************************************************************
    public static void blackJackMenu() {

        Scanner keyboard = new Scanner(System.in);

        cpuCard = 0;
        totalPlayerCardValue = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Please enter your name ");
        playerName = keyboard.nextLine();

        System.out.println("\nPress enter to scramble the deck of cards ");
        keyboard.nextLine();
        deck();
        keyboard.close();
    }

    public static void deck() {

        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        int cardPulled = random.nextInt(11) + 1;

        System.out.println("--------------------");
        switch (cardPulled) {

            case 1:
                System.out.println("Ace");
                cardPulled = 11;

                if (cardPulled + totalPlayerCardValue > cardRange) {

                    cardPulled = 1;
                }
                break;

            case 10:
                System.out.println("Face Card");
                break;

            default:
                System.out.println("You got " + cardPulled);
                break;
        }

        totalPlayerCardValue += cardPulled;

        if (totalPlayerCardValue > cardRange) {

            lose();

        } else if (totalPlayerCardValue == cardRange) {

            blackJack();

        } else {

            choice();

        }

        keyboard.close();

    }

    public static void choice() {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.println(" ");
        System.out.println("Your total card number is " + totalPlayerCardValue);
        System.out.println("");
        System.out.println("Do you want to hit or stand");
        System.out.println("For Hit please press 1");
        System.out.println("For Stand please press 2");
        int choose = keyboard.nextInt();

        if (choose == 1) {

            deck();

        } else if (choose == 2) {

            dealerpull();

        } else {

            System.out.println("Incorrect Input \n Try Again.");
            choice();

        }

        keyboard.close();

    }

    public static void blackJack() {

        System.out.println("-------------------------");
        System.out.println("You got Black Jack!");
        System.out.println("Congratualtions, You Won!");
        restartMenu();

    }

    public static void lose() {

        System.out.println("-----------------");
        System.out.println("Sorry you went burst!");
        restartMenu();

    }

    public static void dealerpull() {

        Random random = new Random();

        int cardPulled = 0;

        cardPulled = random.nextInt(11) + 1;
        cpuCard += cardPulled;

        System.out.println("---------------------------");
        System.out.println("Dealer pulled " + cardPulled);
        System.out.println("---------------------------");

        if (cpuCard <= cardRange && cpuCard > totalPlayerCardValue) {

            System.out.println("Total value of dealrs card " + cpuCard);
            System.out.println("Dealer Won.");
            System.out.println("Better luck next time " + playerName + ".");
            restartMenu();

        } else if (cpuCard <= totalPlayerCardValue) {

            dealerpull();

        } else {

            System.out.println("Total value of dealrs card " + cpuCard);
            dealerburst();

        }

    }

    public static void dealerburst() {

        System.out.println("Dealer Went Burst");
        System.out.println("You Won " + playerName + ".");
        restartMenu();

    }

    public static void restartMenu() {

        Scanner keyboard = new Scanner(System.in);

        int quitOption = 0;

        System.out.println("To play again enter any number");
        System.out.println("To exit enter 0");
        quitOption = keyboard.nextInt();

        if (quitOption == 0) {

            System.out.println("");
            System.out.println("Thanks for playing " + playerName);
            endScreen();

        } else {

            System.out.print("\033[H\033[2J");
            System.out.flush();

            blackJackMenu();

        }

        keyboard.close();
    }

    // END OF BLACK JACK **************************************************


   

    public static void endScreen(){

       


        System.out.println("Thank you for playing!");
        System.out.println("");
        playerName = " ";
        totalPlayerCardValue = 0;
        cpuCard = 0;
        pot = 0;
        card1 = 0;
        card2 = 0;
        hiddenCard = 0;
        maxCard = 0;
        minCard = 0;
        

        mainMenu();

    }


    public static void ruleSet() {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();


        System.out.println("*************************RULE SET**************************");
        System.out.println("--------------GAME 1 || DICE GAME ------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("There is no rule for Game 1, Its just you and computer will roll a dice and whoever gets the higher number Wins !!!");
        System.out.println("");
        System.out.println("");
        System.out.println("--------------GAME 2 || ACEY DUECY ------------------");
        System.out.println("1. Acey duecy is a two player game.");
        System.out.println("2. Both the player will put some amount in the pot in the starting of the game. ");
        System.out.println("3. After that dealer will pull three cards and 2 will be show and the third will be hidden.");
        System.out.println("4. Player will need to bet if the hidden card lies in between the two shown cards.");
        System.out.println("5. If the two cards shown are adjecent or equal in terms of value, Then player will choose to bet if the third card is higher or lower than the shown cards.");
        System.out.println("");
        System.out.println("");
        System.out.println("--------------GAME 3 || Black Jack ------------------");
        System.out.println("1. Black jack is a game between player and the dealer.");
        System.out.println("2. In this game value of Ace is 1 or 11 depending upon the situation and face cards have the value of 10");
        System.out.println("3. The dealer will give a card to player and player has to choose to hit or stand ");
        System.out.println("4. The player needs to keep the value of total card under or equal 21. if went above the player will get burst and lose");
        System.out.println("5. When a player chooses to stand, dealer will pull cards for himself as much as possible to get total number above players total number.");
        System.out.println("6. If any of them gets total number equal to 21 they will win immediately.");
        System.out.println("");
        System.out.println("");
        System.out.println("*****************************************************************");
        System.out.println("Press enter to continue to main menu");

        keyboard.nextLine();
        mainMenu();

        keyboard.close();

    }
}