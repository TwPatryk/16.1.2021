package pl.camp.it.filmoteka.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.filmoteka.model.User;


@Component
@SessionScope
public class SessionObject {

    private User user = null;
    private String info = null;

    public boolean isLogged() {
        return !(this.user == null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInfo() {
        String result = this.info;
        this.info = null;
        return result;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
