package pl.camp.it.filmoteka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.Movie;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IFilmRepository filmRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
        public String main (Model model){
        if(sessionObject.isLogged()) {
            model.addAttribute("films", this.filmRepository.getAllFilms());
            model.addAttribute("user", this.sessionObject.getUser());
            return "main";
        } else {
            return "redirect:/login";
            }
        }
    /*@RequestMapping(value = "/database", method = RequestMethod.GET)
    public String database (Model model){

        model.addAttribute("films", this.filmRepository.getAllFilms());

        return "database";
    }   */
    @RequestMapping(value="/movies", method= RequestMethod.GET)
    public String movies(Model model) {
        if(sessionObject.isLogged()) {
            model.addAttribute("films", this.filmRepository.getMovies());
            model.addAttribute("user", this.sessionObject.getUser());
            return "main";
        } else {
            return "redirect:/login";
        }

    }
    @RequestMapping(value="/tv-shows", method= RequestMethod.GET)
    public String tvShows(Model model) {
        if(sessionObject.isLogged()) {
            model.addAttribute("films", this.filmRepository.getTvShows());
            model.addAttribute("user", this.sessionObject.getUser());
            return "main";
        } else {
            return "redirect:/login";
        }

    }
    @RequestMapping(value= "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter,
                        Model model) {
        if(sessionObject.isLogged()) {
            model.addAttribute("films", this.filmRepository.getFilmsByFilter(filter));
            model.addAttribute("user", this.sessionObject.getUser());
            return"main";
        } else {
            return "redirect:/login";
        }

    }
}
