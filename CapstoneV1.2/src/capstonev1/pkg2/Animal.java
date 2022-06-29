package capstonev1.pkg2;

public class Animal {
    public int animalID;
    public String name, species, breed, gender, status;
    static public int animalCount = 500;
    public int age;
    public int weight;
    
    public Animal(){
        this.animalID = animalCount;
        this.name = null;
        this.species = null;
        this.breed = null;
        this.age = 0;
        this.gender = null;
        this.weight = 0;
        this.status = null;
        animalCount++;
    }
    
    public Animal(String n, String s, String b, int a, String g, int w, String stat){
        this.animalID = animalCount;
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
    
    public void setAnimalID(int aID){
        this.animalID = aID;
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
    public void setWeight(int w){
        this.weight = w;
    }
    public void setStatus(String st){
        this.status = st;
    }
    public String toString(){
        return this.animalID + " " 
                + this.name + " " 
                + this.species + " " 
                + this.breed + " " 
                + this.age + " " 
                + this.gender + " " 
                + this.weight + " " 
                + this.status;
    }
}
