
public class Runner {
    private String name;
    private String country;
    private int age;
    private int nr;
    private double time;
    
    Runner(String name, String country, int age, int nr){
        this.name = name;
        this.country = country;
        this.age = age;
        this.nr = nr;
    }
        public String getName(){
            return name;
        }
        public String getCountry(){
            return country;
        }
        public int getAge(){
            return age;
        }
        public int getSNr(){
            return nr;
        }
        public void setTime(double t){
            time = t;
        }
        public double getTime(){
            return time;
        }
}
