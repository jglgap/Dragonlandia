package project;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import project.connections.Connection;
import project.connections.Manager;
import project.controllers.Controller;
import project.controllers.MainController;
import project.models.Mago;
import project.views.BattleView;

public class Main {

    public static void main(String[] args) {
        
        MainController controller = new MainController(Manager.getInstance(),new BattleView());
    }
}