package pl.camp.it.filmoteka.model;

public class TvShow extends Film {

    private int NumberOfSeasons;

    public TvShow() {
    }

    public TvShow(int numberOfSeasons) {
        NumberOfSeasons = numberOfSeasons;
    }

    public TvShow(String title, String director, int productionYear, int numberOfSeasons) {
        super(title, director, productionYear);
        NumberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return NumberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        NumberOfSeasons = numberOfSeasons;
    }
}
