package pl.camp.it.filmoteka.model;

public class Movie extends Film {

    public Movie() {
    }

    public Movie(int id, String title, String director, int productionYear, String length, Category category) {
        super(id, title, director, productionYear, length, category);
    }

}
