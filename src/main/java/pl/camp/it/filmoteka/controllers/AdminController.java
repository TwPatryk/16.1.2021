package pl.camp.it.filmoteka.controllers;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IFilmRepository filmRepository;

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
        Film filmFromDB = this.filmRepository.getFilmByTitle(film.getTitle());

        if(filmFromDB != null) {
            this.sessionObject.setInfo("Film znajduje się już w bazie!");
        } else {
            this.filmRepository.addFilm(film);
            this.sessionObject.setInfo("Dodano Film!");


        }
        return "redirect:/addFilm";
    }
}
