package pl.camp.it.filmoteka.model;

public class Movie extends Film {
    private int length;

    public Movie(String title, String director, int productionYear, int length) {
        super(title, director, productionYear);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
