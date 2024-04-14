package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;
import java.util.Properties;


public class Util {

    // JDBC CONNECTOR

//    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "9211218719Mm";
//    private static Connection connection;
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return connection;
//    }

    // HIBERNATE CONNECTOR


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "9211218719Mm");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
