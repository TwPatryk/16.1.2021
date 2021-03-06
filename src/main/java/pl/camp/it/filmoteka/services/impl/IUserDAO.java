package pl.camp.it.filmoteka.services.impl;

import pl.camp.it.filmoteka.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void updateUser(User user);
    void persistUser(User user);
}
