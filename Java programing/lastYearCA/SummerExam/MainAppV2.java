
package SummerExam;

import java.util.ArrayList;

/**
 *
 * @author Your Own Name HERE
 */
    public class MainAppV2
{
    public static void main(String[] args)
    {
       
        Activity a1 = new Activity(34.5, "01/05/2021", "08:17",true);  
        Activity a2 = new Activity(68.1, "28/04/2021", "12:43",false);  
        Activity a3 = new Activity(23.2, "01/05/2021", "20:34",true);  
        Activity a4 = new Activity(17.8, "05/05/2021", "11:00",true);  
        
        
        
//  Tests for Question 3 (a) & (b) & (c) 
        
        
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(a1);
        activities.add(a2);
        activities.add(a3);
        activities.add(a4);
        FitnessTracker f1 = new FitnessTracker("trackerType", "ownerName", activities);
        
        
//   Test for Question 3 (e)  
        f1.displayAllActivities();
  
        
//        
//
//   Test for Question 4 (a)
//
        f1.displayLongerActivities(2);

  
//
//   Test for Question 4 (b)
//
       int counted =  f1.countMaps();
        System.out.println(counted);
//
//   Test for Question 4 (c)
//
        double avg = f1.calculateAverageDurations();

//
//   Test for Question 4 (d)
//
        
        
       
//
//   Test for Question 4 (e)
//



  
    }
    

}
    

