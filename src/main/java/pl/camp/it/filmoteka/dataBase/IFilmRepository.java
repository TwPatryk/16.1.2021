package pl.camp.it.filmoteka.dataBase;

import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.Movie;
import pl.camp.it.filmoteka.model.TvShow;

import java.util.List;

public interface IFilmRepository {

    List<Film> getAllFilms();
    List<Film> getFilmsByCategory(Film.Category category);
    Film getFilmByTitle(String title);
    void addFilm(Film film);
    void updateFilm(Film film);


}
