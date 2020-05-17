//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.util.ArrayList;


public class Movie extends Video {

    public Movie(String name, Director director, ArrayList<Util.genresEnum> genres, Util.ageRatingsEnum ageRating,
                 ArrayList<Actor> actor, int length) {
        super(name, director, genres, ageRating, actor, length);
    }
    public Movie(Movie e){
        super(e);

    }
    
}