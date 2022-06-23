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
public class Volunteer {
    public int volunteerID;
    public String title,specialization;
    public double hours;
    public int appID;
    static public int volunteerCount = 1;
    public final static int MAXVOLUNTEERS = 500;
    public static Volunteer[] volunteers = new Volunteer[MAXVOLUNTEERS];
    
    public Volunteer(){
        this.volunteerID = volunteerCount;
        this.title = null;
        this.specialization = null;
        this.hours = 0.0;
        this.appID = 0;
        volunteerCount++;
    }
    
     public Volunteer(String t,String spec,double hour,int aID){
        this.volunteerID = volunteerCount;
        this.title = t;
        this.specialization = spec;
        this.hours = hour;
        this.appID = aID;
        volunteerCount++;
        }
     //GETTERS
    public int getVolunteerID(){
        return volunteerID;
    }
    public String getTitle(){
        return title;
    }
    public String getSpecialization(){
        return specialization;
    }
    public double getHours(){
        return hours;
    }
    public int getAppID(){
        return appID;
    }
    //SETTERS
    public void setVolunteerID(int v){
        this.volunteerID = v;
    }
    public void setTitle(String f){
        this.title = f;
    }
    public void setSpecialization(String l){
        this.specialization = l;
    }
    public void setHours(double p){
        this.hours = p;
    }
    public void setAppID(int a){
        this.appID = a;
    }
    @Override
    public String toString(){
        return this.volunteerID + " " + this.title + " " + this.specialization + " " + this.hours + " " + this.appID;
    }
}
