package pl.camp.it.filmoteka.dataBase.impl;

import org.springframework.stereotype.Component;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.Movie;
import pl.camp.it.filmoteka.model.TvShow;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static com.sun.jmx.snmp.ThreadContext.contains;
import static com.sun.jmx.snmp.ThreadContext.get;



public class ListFilmRepositoryImpl implements IFilmRepository {


    private final List<Film> films = new ArrayList<>();

    public ListFilmRepositoryImpl() {
/*
        films.add(new Movie("The Thing", "John Carpenter", 1982, "110min", Film.Category.MOVIE));
        films.add(new Movie("American Psycho", "Mary Harron", 2000, "100min", Film.Category.MOVIE));
        films.add(new Movie("Good, Bad and Ugly", "Sergio Leone", 1966, "100min", Film.Category.MOVIE));
        films.add(new Movie("Saw", "James Wan", 2004, "100min", Film.Category.MOVIE));
        films.add(new Movie("Cube", "Vincenzo Natali", 1997, "90min", Film.Category.MOVIE));
        films.add(new TvShow("Breaking Bad", "Vince Gilligan", 2008, "5 seasons", Film.Category.TVSHOW));
        films.add(new TvShow("House M.D", "David Shore", 2004, "8 seasons", Film.Category.TVSHOW));
        films.add(new TvShow("Friends", "David Crane, Marta Kauffman", 1994, "10 seasons", Film.Category.TVSHOW));
        films.add(new TvShow("Queen's Gambit", "Allan Scott, Scott Frank", 2020, "1 season", Film.Category.TVSHOW));

 */
    }

    @Override
    public List<Film> getAllFilms() {
        return this.films;
    }

    @Override
    public List<Film> getFilmsByCategory(Film.Category category) {
        // TODO do zrobienia
        return null;
    }

    @Override
    public Film getFilmByTitle(String title) {
        for (Film film : this.films) {
            if (film.getTitle().equals(title)) {
                return film;
            }
        }
        return null;
    }

    @Override
    public void addFilm(Film film) {
        this.films.add(film);
    }

    @Override
    public void updateFilm(Film film) {
        //TODO do zrobienia
    }
}