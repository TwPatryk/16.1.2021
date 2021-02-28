package pl.camp.it.filmoteka.model;

public class TvShow extends Film {
    public TvShow() {
    }

    public TvShow(int id, String title, String director, int productionYear, String length, Category category) {
        super(id, title, director, productionYear, length, category);
    }

}
