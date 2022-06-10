/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstonev1.pkg2;

/**
 *
 * @author Vanessa Quinteros
 */
public class Attendance {
    public int eventID,volunteerID;
    public Attendance(){
        this.eventID = 0;
        this.volunteerID = 0;
    }
     public Attendance(int eID,int vID){
        this.eventID = eID;
        this.volunteerID = vID;
    }
    //GETTERS
    public int getEventID(){
        return eventID;
    }
    public int getVolunteerID(){
        return volunteerID;
    }
    
    //SETTERS
    public void setEventID(int e){
        this.eventID = e;
    }
    public void setVolunteerID(int v){
        this.volunteerID = v;
    }
    
}
