//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.util.ArrayList;
import java.util.logging.Level;

public abstract class Video extends Media {

    private static int idCounter = 0;

    final Director director;
    private final ArrayList<Actor> actor;
    private final int ID;

    private int length;

    public Video(String name, Director director, ArrayList<Util.genresEnum> genres, Util.ageRatingsEnum ageRating,
            ArrayList<Actor> actor, int length) {
        super(name, ageRating, genres);
        idCounter++;
        this.ID = idCounter;
        this.length = length;
        this.director = director;
    
        {
            if (actor == null || actor.size() <= 0) {
                logger.log(Level.WARNING, "Não foi passado um Ator!");
                this.actor = null;
            } else {
                this.actor = new ArrayList<>();
                if (actor.size() > 3)
                    logger.log(Level.WARNING, "Mais de 3 gêneros foram passados!");
                this.actor.addAll(actor);
            }
        }
    
    }
    public Video(Video e){
        super(e);
        this.ID = e.ID;
        this.length = e.length;
        actor = (ArrayList<Actor>) Util.CopyArray(e.actor);
        director = new Director(e.director);

    }

    public Director getDirector() {
        return this.director;
    }

    public ArrayList<Actor> getActor() {
        ArrayList<Actor> copy = (ArrayList<Actor>) Util.CopyArray(this.actor);
        return copy;
    }

    public int getID() {
        return this.ID;
    }

    public int getLength() {
        if(length < 0)
        {
            logger.log(Level.WARNING, "Tentando acessar uma duração não definida!");
        }
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}