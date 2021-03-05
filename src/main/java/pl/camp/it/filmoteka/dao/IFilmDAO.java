package pl.camp.it.filmoteka.dao;

import pl.camp.it.filmoteka.model.Film;

import java.util.List;

public interface IFilmDAO {
    Film getFilmByTitle(String title);
    void updateFilm(Film film);
    void persistFilm(Film film);
    Film getFilmById(int id);
    List<Film> getFilmsByCategory(Film.Category category);
    List<Film> getAllFilms();

}
