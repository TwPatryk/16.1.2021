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

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String commonRedirect() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
        public String main (Model model,@RequestParam(defaultValue = "none") String category){
        if(sessionObject.isLogged()) {
            switch (category) {
                case "movie":
                    model.addAttribute("films", this.filmRepository.getMovies());
                    break;
                case "tvshow":
                    model.addAttribute("films", this.filmRepository.getTvShows());
                    break;
                default:
                    model.addAttribute("films", this.filmRepository.getAllFilms());
                    break;
            }
            model.addAttribute("user", this.sessionObject.getUser());
            return "main";
        } else  {
            return "redirect:/login";
            }
        }
    /*@RequestMapping(value = "/database", method = RequestMethod.GET)
    public String database (Model model){

        model.addAttribute("films", this.filmRepository.getAllFilms());

        return "database";
    }   */

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
