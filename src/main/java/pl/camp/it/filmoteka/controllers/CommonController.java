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
import pl.camp.it.filmoteka.utils.FilterUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IFilmRepository filmRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String commonRedirect() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model, @RequestParam(defaultValue = "none") String category) {
        if (sessionObject.isLogged()) {
            switch (category) {
                case "movie":
                    model.addAttribute("films",
                            FilterUtils.filterFilms(this.filmRepository.getFilmsByCategory(Film.Category.MOVIE),
                                    this.sessionObject.getFilter()));
                    break;
                case "tvshow":
                    model.addAttribute("films",
                            FilterUtils.filterFilms(this.filmRepository.getFilmsByCategory(Film.Category.TVSHOW),
                                    this.sessionObject.getFilter()));
                    break;
                default:
                    model.addAttribute("films",
                            FilterUtils.filterFilms(this.filmRepository.getAllFilms(),
                                    this.sessionObject.getFilter()));
                    break;
            }
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("filter", this.sessionObject.getFilter());
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

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter) {
        if (sessionObject.isLogged()) {
            this.sessionObject.setFilter(filter);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }


    /*

    //TODO nie wiem czy zadziała
    @RequestMapping(value = "/rateAndComment", method = RequestMethod.GET)
    public String rateAndCommentForm(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "rateAndComment";
    }



    @RequestMapping(value = "/rateAndComment", method = RequestMethod.POST)
    public String rateAndCommentForm(@RequestParam Model model, Film film, RateAndComment rateAndComment) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        Film filmFromDB = this.filmRepository.getFilmByTitle(film.getTitle());
        List<RateAndComment> rateAndCommentList = new ArrayList<>();
        model.addAttribute("rateAndComm",new RateAndComment(filmFromDB.getTitle(), filmFromDB.getDirector(),
                filmFromDB.getProductionYear(), filmFromDB.getLength(), filmFromDB.getCategory(), rateAndComment.getRate(),
                rateAndComment.getComment()));

        //model.addAttribute("user", this.sessionObject.getUser());
        //model.addAttribute("info", this.sessionObject.getInfo());
        //model.addAttribute("films", new RateAndComment(film.getTitle(), film.getDirector(), film.getProductionYear(),
         //       film.getLength(), film.getCategory(), rateAndComment.getRate(), rateAndComment.getComment()));
        return "rateAndComment";
    }

     */
}
