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
        Film filmFromDB = this.filmRepository.getFilmByTitle(film.getTitle());

        if(filmFromDB != null) {
            this.sessionObject.setInfo("Film znajduje się już w bazie!");
        } else {
            if(film.getTitle().equals("") || film.getDirector().equals("") || film.getLength().equals("") ||
                    film.getLength().equals("")) {
                this.sessionObject.setInfo("Uzupełnij formularz");
            } else {
                this.filmRepository.addFilm(film);
                this.sessionObject.setInfo("Dodano Film!");
            }
        }
        return "redirect:/addFilm";
    }
    @RequestMapping(value = "editFilm/{title}", method = RequestMethod.GET)
    public String editFilmPage (@PathVariable String title, Model model) {
        Film film = this.filmRepository.getFilmByTitle(title);
        model.addAttribute("film", film);
        model.addAttribute("user", this.sessionObject.getUser());
        return "editFilm";
    }
    @RequestMapping(value = "/editFilm/{title}", method = RequestMethod.POST)
    public String editFilm (@ModelAttribute Film film, @PathVariable String title) {
        film.setTitle(title);
        this.filmRepository.updateFilm(film);

        return "redirect:/main";
    }
}
