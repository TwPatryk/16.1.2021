package pl.camp.it.filmoteka.model;

public class Movie extends Film {

    public Movie() {
    }

    public Movie(String title, String director, int productionYear, String length, Category category) {
        super(title, director, productionYear, length, category);
    }
}
