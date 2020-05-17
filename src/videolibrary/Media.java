//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class Media {
    private final String name;
    private final Util.ageRatingsEnum ageRating;
    private final ArrayList<Util.genresEnum> genres;
    private int year, nViews, nUserRatings;
    private float userRating;
    protected Logger logger;

    public Media(String name, Util.ageRatingsEnum ageRating, ArrayList<Util.genresEnum> genres) {
        logger = Logger.getLogger(Media.class.getName());

        this.name = name;
        this.ageRating = ageRating;
        if (genres == null || genres.size() <= 0) {
            logger.log(Level.WARNING, "Não foi passado um gênero!");
            this.genres = null;
        } else {
            this.genres = new ArrayList<>();
            if (genres.size() > 3)
                logger.log(Level.WARNING, "Mais de 3 gêneros foram passados!");
            this.genres.addAll(genres);
        }
        year = GregorianCalendar.getInstance().get(Calendar.YEAR);
        nViews = 0;
        nUserRatings = 0;
        userRating = 0.0f;
    };

    public Media(Media e) {
        this.name = e.getName();
        this.ageRating = e.ageRating;
        this.genres = (ArrayList<Util.genresEnum>) Util.CopyArray(e.genres);
        this.year = e.getYear();
        this.nViews = e.getNViews();
        this.nUserRatings = e.getNUserRatings();
        this.userRating = e.getUserRating();
    }

    public void incrementViews(){
        nViews++;
    }
    public void addUserRating(float userRating){
        this.nUserRatings++;
        this.userRating = this.userRating + ((userRating-this.userRating)/this.nUserRatings);
    }

    public String getName() {
        return this.name;
    }

    public String getAgeRating() {
        return this.ageRating.getRatingDescription();
    }

    public ArrayList<Util.genresEnum> getGenres() {
        ArrayList<Util.genresEnum> copy = (ArrayList<Util.genresEnum>) Util.CopyArray(this.genres);
        return copy;
    }

    public int getYear() {
        return this.year;
    }

    public int getNViews() {
        return this.nViews;
    }

    public int getNUserRatings() {
        return this.nUserRatings;
    }

    public float getUserRating() {
        return this.userRating;
    }
    public void setYear(int year){
        if(year < 1878)
            logger.log(Level.WARNING, "Não existiam filmes antes de 1878!");
        else if (year > (GregorianCalendar.getInstance().get(Calendar.YEAR)+1))
            logger.log(Level.WARNING, "O filme não pode ter data de lançamento maior que 1 ano a partir deste!");
        this.year = year;
    }
    
}