/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstonev1.pkg2;

/**
 *
 * @author david
 */
public class Animal {
    public int animalID;
    public String name, species, breed, gender, status;
    public int age;
    public double weight;
    
    public Animal(){
        this.animalID = 0;
        this.name = null;
        this.species = null;
        this.breed = null;
        this.age = 0;
        this.gender = null;
        this.weight = 0;
        this.status = null;
        
    }
    
    public Animal(int aID, String n, String s, String b, int a, String g, double w, String stat){
        this.animalID = aID;
        this.name = n;
        this.species = s;
        this.breed = b;
        this.age = a;
        this.gender = g;
        this.weight = w;
        this.status = stat;
    }
    
    public int getAnimalID(){
        return animalID;
    }
    public String getName(){
        return name;
    }
    public String getSpecies(){
        return species;
    }
    public String getBreed(){
        return breed;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public double getWeight(){
        return weight;
    }
    public String getStatus(){
        return status;
    }
    
    public void setAnimalID(int a){
        this.animalID = a;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setSpecies(String s){
        this.species = s;
    }
    public void setBreed(String b){
        this.breed = b;
    }
    public void setAge(int ag){
        this.age = ag;
    }
    public void setGender(String g){
        this.gender = g;
    }
    public void setWeight(double w){
        this.weight = w;
    }
    public void setStatus(String st){
        this.status = st;
    }
}
