package pl.camp.it.filmoteka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.camp.it.filmoteka.dao.IFilmDAO;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.services.IFilmService;
import pl.camp.it.filmoteka.session.SessionObject;
import pl.camp.it.filmoteka.utils.FilterUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FilmServiceImpl implements IFilmService {

    @Resource
    SessionObject sessionObject;

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

    @Override
    public List<Film> getFilmsByCategoryWithFilter(String category) {
        switch (category) {
            case "movie":
                 return FilterUtils.filterFilms(this.filmDAO.getFilmsByCategory(Film.Category.MOVIE),
                                this.sessionObject.getFilter());

            case "tvshow":
                return FilterUtils.filterFilms(this.filmDAO.getFilmsByCategory(Film.Category.TVSHOW),
                        this.sessionObject.getFilter());

            default:
                return FilterUtils.filterFilms(this.filmDAO.getAllFilms(),
                        this.sessionObject.getFilter());
        }
    }
}
