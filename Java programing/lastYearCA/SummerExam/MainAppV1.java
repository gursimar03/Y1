package SummerExam;

import java.util.Scanner;



/**
 *
 * @author Your Own Name HERE
 */
public class MainAppV1
{

    /**
     * 
     *   Code to TEST the Activity class in appropriately commented sections of the code
     * 
    */
    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);

//      Q1 Tests
//      Initial tests
       
        Activity a1 = new Activity(34.5, "01/05/2021", "08:17",true);   
        
        a1.setDate("05/05/2021");
        a1.setMap(false);
        
        System.out.println("Date of activity a1: " + a1.getDate());      
        System.out.printf("Map available for activity a1: ", a1.isMap());   
               
//      Tests for Q1a & Q1b & Q1c
        
        a1.setActivityName("MY G");
        System.out.println(a1.getActivityName());
        System.out.println();
        System.out.println(a1);

        Activity a2 = new Activity();
        

       
        
//
//        
//      Tests for Q2a        
    
    System.out.println("Before Duration Increase " + a2.getDuration());
       
              
    a2.increaseDuration(3.2);
    
        
    System.out.println("After Duration Increase " + a2.getDuration());
//
//        
//      Tests for Q2b 
       

                
int key = a1.compareTo(a2);
        
switch (key) {
    case 1: System.out.println("activity one has more duration than the other");
        
        break;

        case -1: System.out.println("activity one has less duration than the other");
        
        break;
        
    case 0: System.out.println("activity one has same duration as the other");
        
    break;
    default:
        break;
}





    }
    
}
