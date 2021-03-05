package pl.camp.it.filmoteka.services;

import pl.camp.it.filmoteka.model.Film;

public interface IFilmService {
    AddFilmResult addFilm(Film film);
    Film getFilmByTitle(String title);
    Film getFilmById(int id);
    void updateFilm(Film film);

    enum AddFilmResult {
        FILM_ADDED
    }
}
