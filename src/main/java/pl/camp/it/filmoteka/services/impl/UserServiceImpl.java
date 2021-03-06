package pl.camp.it.filmoteka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmoteka.model.User;
import pl.camp.it.filmoteka.services.IUserService;
import pl.camp.it.filmoteka.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public User authenticate(User user) {
        User userFromDataBase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDataBase != null && userFromDataBase.getPass().equals(user.getPass())) {
            return userFromDataBase;
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        User currentUser = this.sessionObject.getUser();
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        this.userDAO.updateUser(currentUser);
        return currentUser;
    }
}
