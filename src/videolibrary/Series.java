//Lucas Henrique Braga Martins 11275126
package videolibrary;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;

public class Series extends Media {

	private ArrayList<ArrayList<Episode>> episodes;


	public Series(String name, Util.ageRatingsEnum ageRating, ArrayList<Util.genresEnum> genres) {
		super(name, ageRating, genres);
	}

	public void incrementViews(int episode, int season) {
		episodes.get(season).get(episode).incrementViews();
	}

	public void addUserRating(float userRating, int episode, int season) {
		this.episodes.get(season).get(episode).addUserRating(userRating);
	}

	public ArrayList getEpisodes() {
		ArrayList<ArrayList<Episode>> copy = new ArrayList<>();
		for(int i = 0;i<episodes.size();i++){
			ArrayList copy1 = new ArrayList<>();
			copy1.addAll(episodes.get(i));
			copy.add(copy1);
		}
		return copy;
	}

	public void addEpisode(int season, Episode episode) throws ParseException {
		// se a serie não foi iniciada ,cria a primeira temporada
		if (this.episodes == null)
			this.episodes = new ArrayList<>();

		// se necessario, cria uma nova temporada
		while(this.episodes.size() <= season) {
			this.episodes.add(new ArrayList<>());
		}
		// verifica se já existe o episodio na temporada
		if(episodes.get(season).size() > 0){
			for (Episode nepisode : episodes.get(season)) {
				if (nepisode.getNumber() == episode.getNumber())
					logger.log(Level.WARNING, "Já existe este episodio na temporada");
				return;
			}
		}
		//insere o episodio
		this.episodes.get(season).add(episode);

	}
}

