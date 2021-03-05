package pl.camp.it.filmoteka.services;

import pl.camp.it.filmoteka.model.Film;

import java.util.List;

public interface IFilmService {
    AddFilmResult addFilm(Film film);
    Film getFilmByTitle(String title);
    Film getFilmById(int id);
    void updateFilm(Film film);
    List<Film> getFilmsByCategoryWithFilter(String category);

    enum AddFilmResult {
        FILM_ADDED
    }
}
