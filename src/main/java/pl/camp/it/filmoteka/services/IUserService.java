package pl.camp.it.filmoteka.services;

import pl.camp.it.filmoteka.model.User;

public interface IUserService {
    User authenticate(User user);
    User updateUserData(User user);
}
