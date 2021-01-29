package pl.camp.it.filmoteka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.filmoteka.dataBase.IUserRepository;
import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.model.view.ChangePassData;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("userModel", new User());
        String info = this.sessionObject.getInfo();
        if(info != null) {
            model.addAttribute("info", info);
        }
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String authentication(@ModelAttribute User user) {

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
            String info = this.sessionObject.getInfo();
            if(info != null) {
                model.addAttribute("info", info);
            }
            return "edit";
        }
        return "redirect:/login";
    }
    @RequestMapping(value="/changeData", method= RequestMethod.POST)
    public String changeData(@ModelAttribute User user) {
        user.setLogin(this.sessionObject.getUser().getLogin());
        User updatedUser = this.userRepository.updateUserData(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/edit";
    }
    @RequestMapping(value="/changePass", method= RequestMethod.POST)
    public String changePass(@ModelAttribute ChangePassData changePassData, Model model) {

        if(!changePassData.getNewPass().equals(changePassData.getRepeatedNewPass())) {
            this.sessionObject.setInfo("Nieprawidłowo powtórzone hasło!");
            return "redirect:/edit";
        }
        User user = new User();
        user.setPass(changePassData.getPass());
        user.setLogin(this.sessionObject.getUser().getLogin());

        User authenticatedUser = this.userRepository.authenticate(user);

        if(authenticatedUser == null) {
            this.sessionObject.setInfo("Nieprawidłowe hasło !");
            return"redirect:/edit";
        }
        user.setPass(changePassData.getNewPass());
        User updatedUser = this.userRepository.updateUserPass(user);
        this.sessionObject.setUser(updatedUser);

        return "redirect:/edit";
    }
}
