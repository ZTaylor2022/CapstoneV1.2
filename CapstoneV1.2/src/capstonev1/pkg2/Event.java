package capstonev1.pkg2;

public class Event {
    public int eventID;
    public String location,mileage,task;
    public int volunteerID, maxVolunteers;
    static public int eventCount = 500;
    public final static int MAXEVENTS = 500;
    public static Event[] events = new Event[MAXEVENTS];
    
    public Event(){
        this.eventID = eventCount;
        this.location = null;
        this.mileage = null;
        this.task = null;
        this.maxVolunteers = 0;
        eventCount++;
    }
    public Event(String loc,String mile,String t,int maxV){
        this.eventID = eventCount;
        this.location = loc;
        this.mileage = mile;
        this.task = t;
        this.maxVolunteers = maxV;
        eventCount++;
    }
     //GETTERS
    public int getEventID(){
        return eventID;
    }
    public String getLocation(){
        return location;
    }
    public String getMileage(){
        return mileage;
    }
    public String getTask(){
        return task;
    }
    public int getMaxVolunteers(){
        return maxVolunteers;
    }
    public int getVolunteerID(){
        return volunteerID;
    }
    
    
    //SETTERS
    public void setEventID(int eID){
        this.eventID = eID;
    }
    public void setLocation(String locat){
        this.location = locat;
    }
    public void setMileage(String miles){
        this.mileage = miles;
    }
    public void setTask(String tas){
        this.task = tas;
    }
    public void setMaxVolunteers(int mxV){
        this.maxVolunteers = mxV;
    }
    public void setVolunteerID(int vID){
        this.volunteerID = vID;
    }
    public String toString(){
        return this.eventID + " " + this.location + " " + this.mileage + " " + this.task + " " + this.maxVolunteers;
    }
    
}