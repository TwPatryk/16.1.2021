package pl.camp.it.filmoteka.dataBase.impl;

import org.springframework.stereotype.Component;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.Movie;
import pl.camp.it.filmoteka.model.TvShow;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;

import java.util.ArrayList;
import java.util.List;


@Component
public class ListFilmRepositoryImpl implements IFilmRepository {


    private final List<Film> films = new ArrayList<>();

    public ListFilmRepositoryImpl() {

        films.add(new Movie("The Thing", "John Carpenter", 1982, 110));
        films.add(new Movie("American Psycho", "Mary Harron", 2000, 100));
        films.add(new Movie("Good, Bad and Ugly", "Sergio Leone", 1966, 180));
        films.add(new Movie("Saw", "James Wan", 2004, 100));
        films.add(new Movie("Cube", "Vincenzo Natali", 1997, 90));
        films.add(new TvShow("Breaking Bad", "Vince Gilligan", 2008, 5));
        films.add(new TvShow("House M.D", "David Shore", 2004, 8));
        films.add(new TvShow("Friends", "David Crane, Marta Kauffman", 1994, 10));
        films.add(new TvShow("Queen's Gambit", "Allan Scott, Scott Frank", 2020, 1));
    }

    @Override
    public List<Film> getAllFilms() {
        return this.films;
    }
}
