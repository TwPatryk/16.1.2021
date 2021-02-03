package pl.camp.it.filmoteka.utils;

import pl.camp.it.filmoteka.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {
    public static List<Film> filterFilms(List<Film> films, String filter) {

        if(filter == null) {
            return films;
        }
        List<Film> filteredFilms = new ArrayList<>();

        for(Film film : films) {
            if (film.getTitle().toUpperCase().contains(filter.toUpperCase()) ||
                    film.getDirector().toUpperCase().contains(filter.toUpperCase())) {
                filteredFilms.add(film);
            }
        }
        return filteredFilms;
    }
}
