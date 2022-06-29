
package capstonev1.pkg2;

/**
 *
 * @author Vanessa Quinteros
 */
public class Shifts {
    public int volID,taskID;
    public String timein, timeout, shiftDate;
    public static int count = 1;
    
    public Shifts(){
    this.timein = null;
    this.timeout = null;
    this.shiftDate = null;
    count++;
    }
    
    public Shifts(int vol, String in, String out, String date, int tID){
        this.volID = vol;
        this.timein = in;
        this.timeout = out;
        this.shiftDate = date;
        this.taskID = tID;
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
        return this.volID + " " + this.timein + " " + this.timeout + " " + this.shiftDate;
    }

    public void setTaskID(int h) {
       this.taskID = h;
    }
}