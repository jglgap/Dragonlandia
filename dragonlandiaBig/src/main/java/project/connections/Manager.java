package project.connections;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Manager {
    private static Manager instance;
    private EntityManagerFactory emf;
    

    private Manager(){
        this.emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        
    }


    public static synchronized Manager getInstance(){
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }


    public EntityManagerFactory getEMF(){
        return emf;
    }
}
