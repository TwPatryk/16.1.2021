package pl.camp.it.filmoteka.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RateAndComment {
    private List<Film> rate = new ArrayList<>();
    private List<Film> comment = new ArrayList<>();

    public List<Film> getRate() {
        return rate;
    }

    public void setRate(List<Film> rate) {
        this.rate = rate;
    }

    public List<Film> getComment() {
        return comment;
    }

    public void setComment(List<Film> comment) {
        this.comment = comment;
    }
}
