//Lucas Henrique Braga Martins 11275126
package videolibrary;

import videolibrary.Actor;
import videolibrary.Director;

import java.util.ArrayList;



public class Episode extends Video {
    private final int number;

    public Episode(String name, Director director, ArrayList<Util.genresEnum> genres, Util.ageRatingsEnum ageRating,
                   ArrayList<Actor> actor, int length, int number) {
        super(name, director, genres, ageRating, actor, length);
        this.number = number;
    }
    public Episode(Episode e){
        super(e);
        this.number = e.getNumber();

    }

    public int getNumber() {
        return this.number;
    }

}