//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

    private final String name;
    private final String country;
    private final LocalDate birth;
    private int age;

    public Person(String name, String country, String birth) {
        this.name = name;
        this.country = country;
        this.birth = LocalDate.parse(birth);
        updateAge();

    }
    public Person(Person e){
        this.name = e.getName();
        this.country = e.getCountry();
        this.birth = LocalDate.parse(e.getBirth());
        updateAge();
    }

    public void updateAge() {
        LocalDate curDate = LocalDate.now();
        Period diff = Period.between(this.birth, curDate);
        age = diff.getYears();
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public String getBirth() {
        return this.birth.toString();
    }

    public int getAge() {
        updateAge();
        return this.age;
    }

}