package pl.camp.it.filmoteka.controllers;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.services.IFilmService;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IFilmService filmService;

    @RequestMapping(value = "/addFilm", method = RequestMethod.GET)
    public String addFilmForm(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("film", new Film());
        return "addFilm";
    }

    @RequestMapping(value = "/addFilm", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute Film film) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        IFilmService.AddFilmResult result =  this.filmService.addFilm(film);
        if (result == IFilmService.AddFilmResult.FILM_ADDED) {
            this.sessionObject.setInfo("Dodano Film!");
        } else {
            this.sessionObject.setInfo("Film znajduje się już w bazie!");
            return null;
            }
        return "redirect:/addFilm";
    }

    @RequestMapping(value = "editFilm/{id}", method = RequestMethod.GET)
    public String editFilmPage (@PathVariable int id, Model model) {
        Film film = this.filmService.getFilmById(id);
        model.addAttribute("film", film);
        model.addAttribute("user", this.sessionObject.getUser());
        return "editFilm";
    }
    @RequestMapping(value = "/editFilm/{id}", method = RequestMethod.POST)
    public String editFilm (@ModelAttribute Film film, @PathVariable int id) {
        film.setId(id);
        this.filmService.updateFilm(film);

        return "redirect:/main";
    }
}
