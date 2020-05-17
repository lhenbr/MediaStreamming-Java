//Lucas Henrique Braga Martins 11275126
package videolibrary;

public class Director extends Person {

    public Director(String name, String country, String birth) {
        super(name, country, birth);
    }
    public Director(Director e){
        super(e);
    }
}