package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Entity;
import java.util.List;


@Entity
public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(80), lastName VARCHAR(80), age TINYINT)";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.clear();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS User";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.clear();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
            session.clear();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User();
            session.load(user, id);
            session.delete(user);
            session.getTransaction().commit();
            session.clear();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            session.clear();
        }
    }
}
