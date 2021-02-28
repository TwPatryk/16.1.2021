package pl.camp.it.filmoteka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.dataBase.IUserRepository;
import pl.camp.it.filmoteka.model.Film;
import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.model.view.ChangePassData;
import pl.camp.it.filmoteka.model.view.UserRegistrationData;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IFilmRepository filmRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String authentication(@ModelAttribute User user) {

        Pattern regexPattern = Pattern.compile(".{3}.*");
        Matcher loginMatcher = regexPattern.matcher(user.getLogin());
        Matcher passMatcher = regexPattern.matcher(user.getPass());

        if(!loginMatcher.matches() || !passMatcher.matches()) {
            this.sessionObject.setInfo("Nieprawidłowe dane! (regexp)");
            return"redirect:/login";
        }

        this.sessionObject.setUser(this.userRepository.authenticate(user));

        if(this.sessionObject.getUser() != null) {
            return"redirect:/main";
        } else {
            this.sessionObject.setInfo("Nieprawidłowe dane!");
            return"redirect:/login";
        }
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/login";
    }
    @RequestMapping(value="/edit", method= RequestMethod.GET)
    public String edit(Model model) {
        if(this.sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("passModel", new ChangePassData());
            model.addAttribute("info", this.sessionObject.getInfo());
            return "edit";
        }
        return "redirect:/login";
    }
    @RequestMapping(value="/changeData", method= RequestMethod.POST)
    public String changeData(@ModelAttribute User user) {

        Pattern regexPattern = Pattern.compile("[A-Z]{1}[A-Za-z]*");
        Matcher nameMatcher = regexPattern.matcher(user.getName());
        Matcher surnameMatcher = regexPattern.matcher(user.getSurname());

        if(!nameMatcher.matches() || !surnameMatcher.matches()) {
            this.sessionObject.setInfo("Nieprawidłowe dane !!");
            return "redirect:/edit";
        }

        user.setLogin(this.sessionObject.getUser().getLogin());
        User updatedUser = this.userRepository.updateUserData(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/edit";
    }
    @RequestMapping(value="/changePass", method= RequestMethod.POST)
    public String changePass(@ModelAttribute ChangePassData changePassData, Model model) {


        Pattern regexPattern = Pattern.compile(".{3}.*");
        Matcher currentPassMatcher = regexPattern.matcher(changePassData.getPass());
        Matcher newPassMatcher = regexPattern.matcher(changePassData.getNewPass());

        if(!changePassData.getNewPass().equals(changePassData.getRepeatedNewPass())) {
            this.sessionObject.setInfo("Nieprawidłowo powtórzone hasło!");
            return "redirect:/edit";
        }
        if(!currentPassMatcher.matches() || !newPassMatcher.matches()) {
            this.sessionObject.setInfo("Nieprawidłowe hasło !!");
            return "redirect:/edit";
        }
        User user = new User();
        user.setPass(changePassData.getPass());
        user.setLogin(this.sessionObject.getUser().getLogin());

        User authenticatedUser = this.userRepository.authenticate(user);

        if(authenticatedUser == null || !currentPassMatcher.matches() || !newPassMatcher.matches()) {
            this.sessionObject.setInfo("Nieprawidłowe hasło !");
            return"redirect:/edit";
        }
        user.setPass(changePassData.getNewPass());
        User updatedUser = this.userRepository.updateUserPass(user);
        this.sessionObject.setUser(updatedUser);

        return "redirect:/edit";
    }


    @RequestMapping(value= "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registerModel", new UserRegistrationData());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "register";
    }
    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public String processRegister(@ModelAttribute UserRegistrationData userRegistrationData) {
        if(!userRegistrationData.getPass().equals(userRegistrationData.getRepeatedPass())) {
            this.sessionObject.setInfo("Nieprawidłowo powtórzone hasła");
            return "redirect:/register";
        }
        boolean checkResult = this.userRepository.checkIfLoginExist(userRegistrationData.getLogin());

        if(checkResult) {
            this.sessionObject.setInfo("Login zajęty");
            return "redirect:/register";
        }

        User user = new User(0,userRegistrationData.getName(),
                             userRegistrationData.getSurname(),
                             userRegistrationData.getLogin(),
                             userRegistrationData.getPass(),
                             User.Role.USER);

        this.userRepository.addUser(user);

        this.sessionObject.setInfo("Rejestracja udana!");
        return "redirect:/login";
    }
}
