package pl.camp.it.filmoteka.dataBase;

import pl.camp.it.filmoteka.model.User;

public interface IUserRepository {
    User authenticate(User user);
    User updateUserData(User user);
    User updateUserPass(User user);
}
