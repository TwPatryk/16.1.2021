package pl.camp.it.filmoteka.dao;

import pl.camp.it.filmoteka.model.Film;

public interface IFilmDAO {
    Film getFilmByTitle(String title);
    void updateFilm(Film film);
    void persistFilm(Film film);
    Film getFilmById(int id);

}
