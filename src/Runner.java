
public class Runner {
    private String name;
    private String country;
    private int age;
    private int nr;
    
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
        public int getNr(){
            return nr;
        }
}
