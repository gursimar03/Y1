import java.util.Scanner;
import java.util.Random;
public class game2
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println("Enter Player's Name:");
        String name=sc.nextLine();
        name=name.toUpperCase();
        
        System.out.println(" ");
        System.out.println("*** "+name+" GET READY ***");
        System.out.println(" ");
        System.out.println(" ");
        
        int point=0;
        int correctGuess=0;
        int incorrectGuess=0;
        int choice=1;
        while(choice==1)
        {
            
            //LEFT CARD
            int suitNum1;
            String suitName1;
            suitNum1= rand.nextInt(4);

            switch (suitNum1)
            {
                case 0:
                    suitName1= "hearts";
                    break;
                case 1:
                    suitName1 = "clubs";
                    break;
                case 2:
                    suitName1 = "diamonds";
                    break;
                default:
                    suitName1 = "spades";
                    break;
            }
            
            int faceNum1;
            String faceName1;
            faceNum1= rand.nextInt(13) + 1;  

            switch (faceNum1)
            {
                case 1:
                    faceName1 = "A";
                    break;
                case 11:
                    faceName1 = "J";
                    break;
                case 12:
                    faceName1 = "Q";
                    break;
                case 13:
                    faceName1 = "K";
                    break;
                default:
                    faceName1 = Integer.toString(faceNum1);
                    break;
            }
            
            String cardName1 = faceName1+ " of " +suitName1 ;
            
            //RIGHT CARD
            int suitNum3;
            String suitName3;
            suitNum3= rand.nextInt(4);

            switch (suitNum3)
            {
                case 0:
                    suitName3= "hearts";
                    break;
                case 1:
                    suitName3 = "clubs";
                    break;
                case 2:
                    suitName3 = "diamonds";
                    break;
                default:
                    suitName3 = "spades";
                    break;
            }
            
            int faceNum3;
            String faceName3;
            faceNum3= rand.nextInt(13) + 1;  

            switch (faceNum3)
            {
                case 1:
                    faceName3 = "A";
                    break;
                case 11:
                    faceName3 = "J";
                    break;
                case 12:
                    faceName3 = "Q";
                    break;
                case 13:
                    faceName3 = "K";
                    break;
                default:
                    faceName3 = Integer.toString(faceNum3);
                    break;
            }
            
            String cardName3 = faceName3+ " of " +suitName3;
            
            //MIDDLE CARD
            int suitNum2;
            String suitName2;
            suitNum2= rand.nextInt(4);

            switch (suitNum2)
            {
                case 0:
                    suitName2= "hearts";
                    break;
                case 1:
                    suitName2 = "clubs";
                    break;
                case 2:
                    suitName2 = "diamonds";
                    break;
                default:
                    suitName2 = "spades";
                    break;
            }
            
            int faceNum2;
            String faceName2;
            faceNum2= rand.nextInt(13) + 1;  

            switch (faceNum2)
            {
                case 1:
                    faceName2 = "A";
                    break;
                case 11:
                    faceName2 = "J";
                    break;
                case 12:
                    faceName2 = "Q";
                    break;
                case 13:
                    faceName2 = "K";
                    break;
                default:
                    faceName2 = Integer.toString(faceNum2);
                    break;
            }
            
            String cardName2 = faceName2+ " of " +suitName2;
           
            System.out.println("LEFT CARD : "+cardName1);   
            System.out.println("RIGHT CARD : "+cardName3);
            System.out.println(" ");   
            System.out.println("Do you want to bet for MIDDLE CARD ???");
            System.out.println("Enter 'Y' for YES");  
            System.out.println("Enter 'N' for NO "); 
            System.out.println(" ");
            
            String yesNo=sc.nextLine();

            if(yesNo.equalsIgnoreCase("Y"))
            {    
                if((faceNum1<faceNum2&&faceNum2<faceNum3)||(faceNum3<faceNum2&&faceNum2<faceNum1))
                {
                    System.out.println("MIDDLE CARD : "+cardName2);
                    System.out.println("HURRAH!!!IT'S A CORRECT GUESS");
                    System.out.println(name+" YOU WON TWO POINTS");
                    System.out.println(" ");
                    correctGuess++;
                    point=point+2;
                    yesNo=" ";
                }
                else
                { 
                    System.out.println("MIDDLE CARD : "+cardName2);
                    System.out.println("OHH!!!IT'S AN INCORRECT GUESS");
                    System.out.println(name+" YOU LOST A POINT");
                    System.out.println(" ");
                    incorrectGuess++;
                    point=point-1;
                    yesNo=" ";
                }
            }
            System.out.println(" ");
            System.out.println(" PRESS 1 TO CONTINUE...");
            System.out.println(" PRESS 0 TO STOP!!!");
            int c=sc.nextInt();
            sc.nextLine(); 
            choice=c;
            System.out.println(" ");

            if (choice==0)
            {
                System.out.println("***THANK YOU FOR PLAYING***");
                System.out.println("***************************");
                System.out.println(name+"'S POINTS : "+point);

                System.out.println("CORRECT GUESS : "+correctGuess);
                System.out.println("INCORRECT GUESS : "+incorrectGuess);

            }

            
        }
        sc.close();
    }
}

    
   
            
   
    


    