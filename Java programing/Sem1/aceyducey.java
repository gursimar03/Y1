import java.util.Scanner;
import java.util.Random;

class aceyducey {

    static int[] playerCashWon = { 0, 0 };
    static int pot = 0;
    static int[] cardPicked = { 0, 0, 0 };
    static String[] cardName = { "", "", "" };
    static int ante = 30;
    static int pointer = 1;
    static int maxCard = 0;
    static int minCard = 0;

    public static void main(String[] args) {

        startScreen();
    }

    static void startScreen() {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Welcome to Acey Ducey");
        for (int currentPlayer = 0; currentPlayer < 2; currentPlayer++) {
            System.out
                    .println("Player " + (currentPlayer + 1) + " please Enter number of coins you want to put in pot");
            pot = keyboard.nextInt();
        }
        cardPull();

        keyboard.close();
    }

    static void cardPull() {

        Random random = new Random();

        maxCard = Math.max(cardPicked[0], cardPicked[1]);
        minCard = Math.min(cardPicked[0], cardPicked[1]);

        pot += ante;

        for (int counter = 0; counter < 3; counter++) {

            cardPicked[counter] = random.nextInt(13) + 1;

        }

        if (cardPicked[0] == cardPicked[1] || (cardPicked[0] - cardPicked[1]) == 1
                || (cardPicked[0] - cardPicked[1]) == -1) {

            higherLower();
        } else {
            midGuess();
        }

    }

    static void higherLower() {

        Scanner keyboard = new Scanner(System.in);

        int choice = 0;
        int bet = 0;

        switchCard();

        System.out.println("Player " + pointer);

        for (int i = 0; i < 2; i++)
            System.out.println("Card " + (i + 1) + "" + cardPicked[i] + " " + cardName[i]);

        System.out.println("Press 1 to bet on higher");
        System.out.println("Press 2 to bet on lower");
        choice = keyboard.nextInt();

        if (choice == 1) {

            System.out.println("How much would you like to bet?");
            bet = keyboard.nextInt();
            pot += bet;

            if (cardPicked[2] > cardPicked[1] || cardPicked[2] > cardPicked[0]) {

                System.out.println("Correct Guess! Player" + (pointer));
                System.out.println(cardPicked[2] + " " + cardName[2]);
                playerCashWon[pointer - 1] += bet * 2;
                pot -= bet * 2;
                pointer++;
                cardPull();

            } else {

                System.out.println("Unlucky, Wrong guess Player " + (pointer));
                System.out.println(cardPicked[2] + " " + cardName[2]);
                pot += bet;
                pointer++;
                cardPull();
            }

        } else if (choice == 2) {

            System.out.println("How much would you like to bet?");
            bet = keyboard.nextInt();
            pot += bet;

            if (cardPicked[2] < cardPicked[1] || cardPicked[2] < cardPicked[0]) {

                System.out.println("Correct Guess! Player" + (pointer));
                System.out.println(cardPicked[2] + " " + cardName[2]);
                playerCashWon[pointer - 1] += bet * 2;
                pot -= bet * 2;
                pointer++;
                cardPull();

            } else {

                System.out.println("Unlucky, Wrong guess Player " + (pointer));
                System.out.println(cardPicked[2] + " " + cardName[2]);
                pot += bet;
                pointer++;
                cardPull();
            }

        } else {

            System.out.println("Wrong Input!");
            higherLower();
        }

    }

    static void switchCard() {

        for (int counter = 0; counter < 3; counter++) {

            switch (cardPicked[counter]) {

                case 1:
                    cardName[counter] = "Ace";
                    break;

                case 11:
                    cardName[counter] = "Joker";
                    break;

                case 12:
                    cardName[counter] = "Queen";
                    break;

                case 13:
                    cardName[counter] = "King";
                    break;

            }

        }
    }

    static void midGuess() {

        Scanner keyboard = new Scanner(System.in);

        for (int currentPlayer = 0; currentPlayer < 2; currentPlayer++) {
            System.out.println("Player " + (currentPlayer + 1));
            System.out.println("Bet on the next card if you think it will lie between the drawn two cards.  ");
            System.out.println("Enter -1 to exit to main menu ");
            System.out.println("Enter 0 to pass:");
            int bet = keyboard.nextInt();
            if (bet >= 0) {
                pot += bet;

                if (bet > 0 && bet <= pot) {

                    if (cardPicked[2] > minCard && cardPicked[2] < maxCard) {
                        System.out.println("Correct Guess! Player" + (pointer));
                        System.out.println(cardPicked[2] + " " + cardName[2]);
                        playerCashWon[pointer - 1] += bet * 2;
                        pot -= bet * 2;
                        pointer++;
                        cardPull();

                    } else {
                        System.out.println("Unlucky, Wrong guess Player " + (pointer));
                        System.out.println(cardPicked[2] + " " + cardName[2]);
                        pot += bet;
                        pointer++;
                        
                        if (pointer == 2) {

                            System.out.println(cardPicked[2]);
                            cardPull();

                        }

                    }

                }

            }
        }
    }
}