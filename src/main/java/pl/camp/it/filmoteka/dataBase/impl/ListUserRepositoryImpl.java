package pl.camp.it.filmoteka.dataBase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.filmoteka.dataBase.IUserRepository;
import pl.camp.it.filmoteka.model.User;

import java.util.ArrayList;
import java.util.List;


public class ListUserRepositoryImpl implements IUserRepository {

    private final List<User> userList = new ArrayList<>();

    public ListUserRepositoryImpl() {
        //this.userList.add(new User("Patryk", "Tworek, ","admin", "admin", User.Role.ADMIN));
        //this.userList.add(new User("Jan", "Kowalski, ","jan", "jan", User.Role.USER));
    }

    @Override
    public User authenticate(User user) {
        for(User userFromDatabase : this.userList) {
            if (userFromDatabase.getLogin().equals(user.getLogin())) {
                if(userFromDatabase.getPass().equals(user.getPass())) {
                    return userFromDatabase;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        for(User userFromDB : this.userList) {
            if(userFromDB.getLogin().equals(user.getLogin())) {
                userFromDB.setName(user.getName());
                userFromDB.setSurname(user.getSurname());
            }
        }
        return null;
    }

    @Override
    public User updateUserPass(User user) {
        for(User userFromDB : this.userList) {
            if(userFromDB.getLogin().equals(user.getLogin())) {
                userFromDB.setPass(user.getPass());
                return userFromDB;
            }
        }
        return null;
    }

    @Override
    public boolean checkIfLoginExist(String login) {
        for(User userFromDB : this.userList) {
            if(userFromDB.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        this.userList.add(user);
    }
}
