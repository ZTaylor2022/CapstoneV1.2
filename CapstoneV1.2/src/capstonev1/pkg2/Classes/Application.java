/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstonev1.pkg2.Classes;

/**
 *
 * @author Vanessa Quinteros
 */
public class Application {
    public int appID;
    public String aFirst,aLast,dob,email,phone,address,experience;
    protected final static int MAXAPPS = 500;
    public static Application [] apps = new Application[MAXAPPS];
    
    public Application(){
        this.appID = 0;
        this.aFirst = null;
        this.aLast = null;
        this.dob = null;
        this.email = null;
        this.phone = null;
        this.address = null;
        this.experience = null;
    }
        public Application(int appID,String aFirst,String aLast,String dob,String email,String phone,String address,String exp){
        this.appID = appID;
        this.aFirst = aFirst;
        this.aLast = aLast;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.experience = exp;
    }
            //GETTERS
    public int getAppID(){
        return appID;
    }
    
    public String getAFirst(){
        return aFirst;
    }
    public String getALast(){
        return aLast;
    }
    public String getDOB(){
        return dob;
    }
    public String getExperience(){
        return experience;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    
    //SETTERS
    public void setAppID(int appID){
        this.appID = appID;
    }
    public void setAFirst(String first){
        this.aFirst = first;
    }
    public void setALast(String last){
        this.aLast = last;
    }
    public void setDOB(String birthDate){
        this.dob = birthDate;
    }
    public void setExperience(String exp){
        this.experience = exp;
    }
    public void setPhone(String stat){
        this.phone = stat;
    }
    public void setEmail(String spec){
        this.email = spec;
    }
    public void setAddress(String vID){
        this.address = vID;
    }
}
