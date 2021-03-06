package pl.camp.it.filmoteka.services;

import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.model.view.ChangePassData;
import pl.camp.it.filmoteka.model.view.UserRegistrationData;

public interface IUserService {
    User authenticate(User user);
    User updateUserData(User user);
    User updateUserPass(ChangePassData changePassData);
    boolean registerUser(UserRegistrationData userRegistrationData);
}
