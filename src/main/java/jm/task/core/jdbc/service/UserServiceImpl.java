package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl usrDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        usrDao.createUsersTable();
    }

    public void dropUsersTable() {
        usrDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usrDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usrDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return usrDao.getAllUsers();
    }

    public void cleanUsersTable() {
        usrDao.cleanUsersTable();
    }
}