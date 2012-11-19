
public class Runner {
    private String name;
    private String country;
    private int age;
    
    Runner(String name, String country, int age){
        this.name = name;
        this.country = country;
        this.age = age;
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
}
