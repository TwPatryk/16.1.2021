package pl.camp.it.filmoteka.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.RateAndComment;
import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class RateAndCommentController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IFilmRepository filmRepository;

    @Autowired
    RateAndComment rateAndComment;

    @RequestMapping(value = "/rate/{title}", method = RequestMethod.GET)
    public String rateFilm(@PathVariable String title, Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("info", this.sessionObject.getInfo());
        Film film = this.filmRepository.getFilmByTitle(title);
        model.addAttribute("film", film);
        return "editRateAndComment";
    }


    @RequestMapping(value = "/comment/{title}", method = RequestMethod.GET)
    public String commentFilm(@PathVariable String title) {
        Film film = this.filmRepository.getFilmByTitle(title);
        rateAndComment.getRate().add(film);
        System.out.println(title);
        return"redirect:/main";
    }


}
