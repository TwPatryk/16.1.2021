package pl.camp.it.filmoteka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.camp.it.filmoteka.dao.IFilmDAO;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.services.IFilmService;


@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    IFilmDAO filmDAO;

    @Override
    public AddFilmResult addFilm(Film film) {
        Film filmFromDB = this.filmDAO.getFilmByTitle(film.getTitle());
        if(filmFromDB == null) {
            this.filmDAO.persistFilm(film);
            return AddFilmResult.FILM_ADDED;
            
        } else {
            film.setId(filmFromDB.getId());
            this.filmDAO.updateFilm(film);

        }
        return null;
    }

    @Override
    public Film getFilmByTitle(String title) {
        return this.filmDAO.getFilmByTitle(title);
    }

    @Override
    public Film getFilmById(int id) {
        return this.filmDAO.getFilmById(id);
    }

    @Override
    public void updateFilm(Film film) {
        this.filmDAO.updateFilm(film);
    }
}
