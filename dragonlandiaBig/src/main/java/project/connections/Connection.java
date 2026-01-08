package project.connections;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {


    private static Connection instance;
    private SessionFactory factory;

    private Connection(){
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public synchronized static Connection getInstance(){
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public SessionFactory getFactory() {
        return factory;
    }


    
}
