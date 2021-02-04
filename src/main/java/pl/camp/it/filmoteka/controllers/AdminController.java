package pl.camp.it.filmoteka.controllers;

import com.mysql.cj.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addFilm", method = RequestMethod.GET)
    public String addFilmForm(Model model) {
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("film", new Film());
        return "addFilm";
    }

    @RequestMapping(value = "/addFilm", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute Film film) {
        System.out.println(film.getTitle());
        System.out.println(film.getDirector());
        System.out.println(film.getProductionYear());
        System.out.println(film.getLength());
        System.out.println(film.getCategory());
        return "redirect:/addFilm";
    }
}
