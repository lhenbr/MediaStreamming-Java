//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Person> personList;
    private static ArrayList<Media> mediaList;
    //private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        mediaList = new ArrayList<>();
        personList = new ArrayList<>();

        readPerson(personList);
        readMedia(mediaList);
        printActors(personList);
        printSeries(mediaList);
        //exemplo de copia

        Series tempSerie = (Series)mediaList.get(3);
        ArrayList tempEpisodes = tempSerie.getEpisodes();
        Episode tempEpisode = ((Episode)((ArrayList)tempEpisodes.get(0)).get(0));
        Episode episodeCopy = new Episode(tempEpisode);
        {
            System.out.println("O nome do episodio copiado é" + episodeCopy.getName());
            System.out.println("e seu diretor é " + episodeCopy.getDirector().getName());
            System.out.println("");
        }


        Movie movieCopy = new Movie((Movie) (mediaList.get(1)));
        {
            System.out.println("O nome do file copiado é "+movieCopy.getName());
            System.out.println("e seu diretor é " + movieCopy.getDirector().getName());
            System.out.println("");
        }
    }

    private static void readMedia(ArrayList<Media> mediaList) throws FileNotFoundException, ParseException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/media.in"));
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        int movieOrSerie;
        String auxName;
        int nGenres,nActors;
        Director auxDirector;
        Util.ageRatingsEnum auxAgeRating;
        ArrayList<Util.genresEnum> auxGenres;
        int actorIndex;
        ArrayList<Actor> auxActors;
        Util.genresEnum genre;

        do{
            movieOrSerie = Integer.parseInt(sc.nextLine());
            if(movieOrSerie == 1){
                auxName = sc.nextLine();
                auxDirector = (Director)personList.get(Integer.parseInt(sc.nextLine()));
                auxAgeRating = Util.ageRatingsEnum.valueOf(sc.nextLine());
                nGenres = Integer.parseInt(sc.nextLine());
                if(nGenres == 0){
                    auxGenres = null;
                }else{
                    auxGenres = new ArrayList<>();
                    for(int i=0;i<nGenres;++i) {
                        auxGenres.add(Util.genresEnum.valueOf(sc.nextLine()));
                    }
                }
                nActors = Integer.parseInt(sc.nextLine());
                if(nActors == 0){
                    auxActors = null;
                }else{
                    auxActors = new ArrayList<Actor>();
                    for(int i=0;i<nActors;i++) {
                        actorIndex = Integer.parseInt(sc.nextLine());
                        auxActors.add((Actor)(personList.get(actorIndex)));
                    }
                }
                Movie auxMovie = new Movie(auxName,auxDirector,auxGenres,auxAgeRating,auxActors,-1);
                mediaList.add(auxMovie);
            }else if(movieOrSerie == 2){
                //declaração de variaveis uteis somente para ler serie
                int nTemporadas,nEpisodes,nActorsEpisode;
                Episode auxEpisode;
                String auxEpisodeName;
                Director auxEpisodeDirector;
                ArrayList actorsInEpisode;


                auxName = sc.nextLine();
                auxAgeRating = Util.ageRatingsEnum.valueOf(sc.nextLine());
                nGenres = Integer.parseInt(sc.nextLine());
                if(nGenres == 0){
                    auxGenres = null;
                }else{
                    auxGenres = new ArrayList<Util.genresEnum>();
                    for(int i=0;i<nGenres;i++) {
                        auxGenres.add(Util.genresEnum.valueOf(sc.nextLine()));
                    }
                }
                Series auxSerie = new Series(auxName,auxAgeRating,auxGenres);
                nTemporadas = Integer.parseInt(sc.nextLine());
                for(int i=0;i<nTemporadas;i++){
                    nEpisodes= Integer.parseInt(sc.nextLine());
                    for(int j =0;j<nEpisodes;j++){
                        auxEpisodeName = sc.nextLine();
                        auxEpisodeDirector = (Director)personList.get(Integer.parseInt(sc.nextLine()));
                        nActorsEpisode = Integer.parseInt(sc.nextLine());
                        if(nActorsEpisode == 0){
                            actorsInEpisode = null;
                        }else{
                            actorsInEpisode = new ArrayList<Actor>();
                            for(int k=0;k<nActorsEpisode;k++) {
                                actorsInEpisode.add(personList.get(Integer.parseInt(sc.nextLine())));
                            }
                        }
                        auxEpisode = new Episode(auxEpisodeName,auxEpisodeDirector,auxGenres,auxAgeRating,actorsInEpisode,-1,j);
                        auxSerie.addEpisode(i,auxEpisode);
                    }
                }
                mediaList.add(auxSerie);
            }
        }while(sc.hasNextLine());
        for(Media media : mediaList){
            System.out.println("Name: " + media.getName());
            System.out.println("Age rating: " + media.getAgeRating());
            if(media.getGenres()!=null) System.out.println("Genres: "+ media.getGenres());
            else System.out.println("Genres not found!");
            System.out.println("");
        }
    }

    private static void readPerson(ArrayList<Person> personList) throws FileNotFoundException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/person.in"));
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        int directorOrActor;
        String auxName, auxCountry, auxBirth;
        do{
            directorOrActor = Integer.parseInt(sc.nextLine());
            auxName= sc.nextLine();
            auxCountry= sc.nextLine();
            auxBirth= sc.nextLine();
            if(directorOrActor == 1) {
                Director auxDirector = new Director(auxName, auxCountry, auxBirth);
                personList.add(auxDirector);
            }
            else if(directorOrActor == 2){
                Actor auxActor = new Actor(auxName,auxCountry,auxBirth);
                personList.add(auxActor);
            }
        }while(sc.hasNextLine());

        for (Person person: personList)
        {
            System.out.println("videolibrary.Person name: " + person.getName());
            System.out.println("videolibrary.Person country: " + person.getCountry());
            System.out.println("videolibrary.Person birth date: "+ person.getBirth());
            System.out.println("videolibrary.Person age: "+ person.getAge());
            System.out.println("");
        }
    }
    private static void printSeries(ArrayList<Media> mediaList){
        for (Media aux : mediaList){
            if(aux instanceof Series){
                printSerie((Series)aux);
            }
        }
    }
    private static void printActors(ArrayList<Person> personList){
        for (Person aux : personList){
            if(aux instanceof Actor){
                printActor((Actor)aux);

            }
        }
    }
    private static void printSerie(Series aux){
        ArrayList episodes = aux.getEpisodes();
        ArrayList temp;
        System.out.println(aux.getName());
        System.out.println("This show has "+episodes.size()+" Seasons");
        for(int i=0;i < episodes.size();i++){
            temp = (ArrayList)episodes.get(i);
            System.out.println("The season "+i+ " has " +temp.size() + " episodes");
        }
        System.out.println("");
    }
    private static void printActor(Actor aux){
        System.out.println(aux.getName());
        System.out.println("");
    }
}
