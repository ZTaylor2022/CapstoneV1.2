
package capstonev1.pkg2;

/**
 *
 * @author Vanessa Quinteros
 */
public class Shifts {
    public int volID,taskID,animalID;
    public String timein, timeout, shiftDate;
    public double totalHours;
    public static int count = 1;
    
    public Shifts(){
    this.timein = null;
    this.timeout = null;
    this.shiftDate = null;
    count++;
    }
    
    public Shifts(int vol, String in, String out, String date, double totalHours, int tID, int aID){
        this.volID = vol;
        this.timein = in;
        this.timeout = out;
        this.shiftDate = date;
        this.totalHours = totalHours;
        this.taskID = tID;
        this.animalID = aID;
        count++;
    }
    
    public int getVolID(){
        return volID;
    }
    public String getTimeIn(){
        return timein;
    }
    public String getTimeOut(){
        return timeout;
    }
    public String getDate(){
        return shiftDate;
    }
    public int getTaskID(){
        return taskID;
    }
    public int getAnimalID(){
        return animalID;
    }
    public double getHours(){
        return totalHours;
    }
    
    
    public void setVolID(int e) {
        this.volID = e;
    }
    public void setTimeIn(String i){
        this.timein = i;
    }
    public void setTimeOut(String o){
        this.timeout = o;
    }
    public void setDate(String d){
        this.shiftDate = d;
    }
    @Override
    public String toString(){
        return this.volID + " " + this.timein + " " + this.timeout + " " + this.shiftDate + " " + this.totalHours;
    }

    public void setTaskID(int h) {
       this.taskID = h;
    }
    public void setAniamlID(int a) {
        this.animalID = a;
    }
    public void setHours(double h){
        this.totalHours = h;
    }
}

