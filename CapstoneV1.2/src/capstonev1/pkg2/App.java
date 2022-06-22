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
public class App {

    //Data fields
    public int appID;
    public String aFirst, aLast, dob, email, password, phone, address, experience, status;
    static public int appCount = 200;
    public static App[] apps = new App[1000];

    public App() {
        this.appID = appCount;
        this.aFirst = null;
        this.aLast = null;
        this.dob = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.experience = null;
        this.status = "Pending";
        appCount++;
    }

    public App(String aFirst, String aLast, String dob, String email, String password,
            String phone, String address, String experience) {
        this.appID = appCount;
        this.aFirst = aFirst;
        this.aLast = aLast;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.experience = experience;
        this.status = "";
        appCount++;
    }

    public int getAppID() {
        return this.appID;
    }

    public String getAFirst() {
        return aFirst;
    }

    public String getALast() {
        return aLast;
    }

    public String getDOB() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getExperience() {
        return experience;
    }
    public String getStatus(){
        return status;
    }

    //SETTERS
    public void setAppID(int appID) {
        this.appID = appID;
    }

    public void setAFirst(String first) {
        this.aFirst = first;
    }

    public void setALast(String last) {
        this.aLast = last;
    }

    public void setDOB(String birthDate) {
        this.dob = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.phone = address;
    }

    public void setExperience(String exp) {
        this.experience = exp;
    }
    public void setStatus(String stat){
        this.status = stat;
    }
  public boolean isUserValid(String x){
        boolean emailCheck = false;
        int i = 0;
        for(i=0;i<apps.length;i++){
            if (apps[i].email != x){
                i++;
            }
            else{
                emailCheck = true;
            }
            
        }
        return emailCheck;
    }
    public boolean isPasswordValid(String y){
        boolean passwordCheck = false;
        int i = 0;
        for(i=0;i<apps.length;i++){
            if (apps[i].password != y){
                i++;
            }
            else{
                passwordCheck = true;
            }
            
        }
        return passwordCheck;
    }
}

