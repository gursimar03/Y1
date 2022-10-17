package SummerExam;

import java.util.ArrayList;



/**
 *
 * @author Your Own Name HERE
 */
public class FitnessTracker {
    private String trackerType;
    private String ownerName;
    private ArrayList<Activity> activities;
    
    public FitnessTracker()
    {
        this.trackerType = "";
        this.ownerName = "";
    }
    public FitnessTracker(String trackerType,String ownerName, ArrayList<Activity> activities)
    {
        this.trackerType = trackerType;
        this.ownerName = ownerName;
        this.activities = activities;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }
    public String getTrackerType() {
        return trackerType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
    public void setTrackerType(String trackerType) {
        this.trackerType = trackerType;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    @Override
    public String toString()
    {
        return "Fitness Tracker{" + "Type=" + trackerType + ", Owner's Name=" + ownerName + '}';
    }
    
    public void addActivity(Activity newActivity){
        this.activities.add(newActivity);
    }

    public  void displayAllActivities(){

        for (Activity activity : this.activities) {
            System.out.println(activity.getActivityName());
        }
    }

    public void displayLongerActivities(double isLongerThanDuration){

        for (Activity activity : this.activities) {

            if(activity.getDuration() > isLongerThanDuration)
                System.out.println(activity);

        }
    }

    public int countMaps(){
        
        int countedMaps = 0;

        for (Activity activity : this.activities) {
            if(activity.isMap()){
                countedMaps++;
            }
        }

        return countedMaps;
    }

    public double calculateAverageDurations(){

        double total = 0;
        double avg = 0;

        for (Activity activity : this.activities) {
            total += activity.getDuration();
        }

        int size = activities.size();

        avg = total/ size;


        return avg;
        
    }

    public void displayByDuration(){

        


    }
}
