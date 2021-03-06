package pl.camp.it.filmoteka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public User authenticate(User user) {
        User userFromDataBase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDataBase != null && userFromDataBase.getPass().equals(user.getPass())) {
            return userFromDataBase;
        }
        return null;
    }
}
