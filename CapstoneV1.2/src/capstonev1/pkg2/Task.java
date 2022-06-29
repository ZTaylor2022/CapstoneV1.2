package capstonev1.pkg2;

public class Task {
    public int taskID;
    public String location,mileage,description;
    public int maxVolunteers;
    static public int count = 500;
  //  public final static int MAXEVENTS = 500;
    ///public static Event[] events = new Event[MAXEVENTS];
    
    public Task(){
        this.taskID = count;
        this.description = null;
        this.location = null;
        this.mileage = null;
        this.maxVolunteers = 0;
        count++;
    }
    public Task(String des, String loc,String mile, int maxV){
        this.taskID = count;
        this.description = des;
        this.location = loc;
        this.mileage = mile;
        this.maxVolunteers = maxV;
        count++;
    }
     //GETTERS
    public int getTaskID(){
        return taskID;
    }
    public String getLocation(){
        return location;
    }
    public String getMileage(){
        return mileage;
    }
    public String getDescription(){
        return description;
    }
    public int getMaxVolunteers(){
        return maxVolunteers;
    }
//    public int getVolunteerID(){
//        return volunteerID;
//    }
    
    
    //SETTERS
    public void setTaskID(int id){
        this.taskID = id;
    }
    public void setLocation(String locat){
        this.location = locat;
    }
    public void setMileage(String miles){
        this.mileage = miles;
    }
    public void setDescription(String des){
        this.description = des;
    }
    public void setMaxVolunteers(int mxV){
        this.maxVolunteers = mxV;
    }
//    public void setVolunteerID(int vID){
//        this.volunteerID = vID;
//    }
    public String toString(){
        return this.taskID + " " + this.description + " " + this.location + " " + this.mileage + " " + this.maxVolunteers;
    }
    
}