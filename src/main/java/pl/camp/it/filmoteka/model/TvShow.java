package pl.camp.it.filmoteka.model;

public class TvShow extends Film {
    public TvShow() {
    }

    public TvShow(String title, String director, int productionYear, String length, Category category) {
        super(title, director, productionYear, length, category);
    }

}
