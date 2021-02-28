package pl.camp.it.filmoteka.model;

public class Film {
    private int id;
    private String title;
    private String director;
    private int productionYear;
    private String length;
    private Category category;

    public Film() {
    }

    public Film(int id, String title, String director, int productionYear, String length, Category category) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.length = length;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", productionYear=" + productionYear +
                ", length='" + length + '\'' +
                ", category=" + category +
                '}';
    }

    public enum Category {
        MOVIE,
        TVSHOW;
    }
}
