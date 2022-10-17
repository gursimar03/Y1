package SummerExam;

/**
 *
 * @author Your Own Name HERE
 */
public class Activity{
    
    private String activityName;
    private double duration;//in minutes
    private String date;//use format DD/MM/YYYY
    private String time;//use HH:MM
    private boolean map;//map is true if the activity was started by the user with GPS connected to their phone and false if it was automatically registered 

    public Activity(double duration, String date, String time,boolean map)
    {
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.map = map;
    }

    public Activity(double duration, String date, String time,boolean map,String activityName)
    {
        this.activityName = activityName;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.map = map;
    }

    public Activity(double duration, String date, String time,String activityName)
    {
        this.activityName = activityName;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.map = false;
    }

    public Activity()
    {
        this.activityName = null;
        this.duration = 0;
        this.date = null;
        this.time = null;
        this.map = false;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getDuration() {
        return duration;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getTime() {
        return time;
    }
    
    public boolean isMap() {
        return map;
    }
    
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMap(boolean map) {
        this.map = map;
    }
    
    @Override
    public String toString()
    {
        return "Activity{" +"Activity Name :"+ this.activityName + "Duration:" + this.duration + "; Date: "+this.date+"; Time: "+this.time+"; Map: " + this.map + '}';
    }
    

    public void increaseDuration(double increaseDurationBy){

        this.duration += increaseDurationBy;
    }
    
    
    public int compareTo(Activity other){

        if (this.duration > other.getDuration()) {
            
            return 1;


        } else if(this.duration > other.getDuration()) {
            return -1;
        }
        else{
            return 0;
        }

    }

}
