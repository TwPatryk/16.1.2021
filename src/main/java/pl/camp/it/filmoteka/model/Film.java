package pl.camp.it.filmoteka.model;

public class Film {
    private String title;
    private String director;
    private int productionYear;

    public Film() {
    }

    public Film(String title, String director, int productionYear) {
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
}
