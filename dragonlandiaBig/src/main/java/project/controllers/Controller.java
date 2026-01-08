package project.controllers;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.html.parser.Entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import project.connections.Connection;
import project.connections.Manager;
import project.models.Bosque;
import project.models.Dragon;
import project.models.Hechizo;
import project.models.Mago;
import project.models.Monstruo;
import project.views.BattleView;



/**
 * Controlador principal de la aplicación. Gestiona la interacción entre la vista y
 * el modelo, permitiendo crear magos, monstruos, bosques y realizar batallas.
 * 
 * Esta clase inicia automáticamente el menú de opciones al ser instanciada.
 */
public class Controller {

    private BattleView vista;
    private Connection conn;
    private Manager mang;

        /**
     * Constructor de la clase Controller.
     * 
     * @param vista instancia de la vista encargada de solicitar y mostrar datos al usuario.
     *              Al construirse el controlador, se lanza el menú principal.
     */
    public Controller(Manager mang , BattleView vista) {
        this.mang = mang;
        this.vista = vista;
        menu();
    }
        /**
     * Crea un nuevo mago utilizando los valores proporcionados por la vista.
     * El mago es persistido en la base de datos usando Hibernate.
     */
    // public void crearMago() {
    //     Session session = null;
    //     Mago magoCreado = null;
    //     Transaction tx = null;
    //     try (SessionFactory factory = conn.getFactory()) {
    //         session = factory.getCurrentSession();
    //         tx = session.beginTransaction();
    //         Hechizo bolaFuego = session.find(Hechizo.class, 1);
    //         Hechizo rayo = session.find(Hechizo.class, 2);
    //         Hechizo bolaNieve = session.find(Hechizo.class, 3);
    //         Hechizo atormentacion = session.find(Hechizo.class, 4);
    //         List<Hechizo> conjuros = Arrays.asList(bolaFuego,rayo,bolaNieve,atormentacion);
    //         magoCreado = vista.getValoresmago(conjuros);
    //         session.persist(magoCreado);
    //         tx.commit();

    //     } catch (Exception e) {
    //         System.out.println("Problemas crendo el mago");
    //         tx.rollback();
    //     }
    // }

        public void crearMago() {
 
        Mago magoCreado = null;
        
        try (EntityManagerFactory emf = mang.getEMF()) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Hechizo bolaFuego = em.find(Hechizo.class, 1);
            Hechizo rayo = em.find(Hechizo.class, 2);
            Hechizo bolaNieve = em.find(Hechizo.class, 3);
            Hechizo atormentacion = em.find(Hechizo.class, 4);
            List<Hechizo> conjuros = Arrays.asList(bolaFuego,rayo,bolaNieve,atormentacion);
            magoCreado = vista.getValoresmago(conjuros);
            em.persist(magoCreado);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Problemas crendo el mago");
            
        }
    }
        /**
     * Crea un nuevo monstruo utilizando los valores proporcionados por la vista.
     * El monstruo es persistido en la base de datos usando Hibernate.
     */
    // public void crearMostruo() {
    //     Session session = null;
    //     Monstruo monstruoCreado = vista.getValoresMonstruo();
    //     Transaction tx = null;
    //     try (SessionFactory factory = conn.getFactory()) {
    //         session = factory.getCurrentSession();
    //         tx = session.beginTransaction();
    //         session.persist(monstruoCreado);
    //         tx.commit();

    //     } catch (Exception e) {
    //         System.out.println("Problemas crendo el mago");
    //         tx.rollback();
    //     }
    // }

        public void crearMostruo() {
       
        Monstruo monstruoCreado = vista.getValoresMonstruo();
        EntityManager em = null;
        try (EntityManagerFactory emf = mang.getEMF()) { 
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(monstruoCreado);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Problemas creando el monstruo");

            
        }
    }


    // public void ataqueMago() {
    // Session session = null;
    // Mago mago = null;
    // Monstruo monstruo = null;
    // try (SessionFactory factory = new
    // Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {

    // session = factory.getCurrentSession();
    // Transaction tx = session.beginTransaction();
    // mago = session.find(Mago.class, 1);
    // monstruo = session.find(Monstruo.class, 1);
    // tx.commit();
    // } catch (Exception e) {
    // System.out.println("Problemas ataque el mago");
    // }
    // if (monstruo.getVida()<=0) {
    // System.out.println("el monstruo murio");
    // }else{

    // mago.ataque(monstruo);
    // }
    // System.out.println(monstruo.getVida());
    // }

    // public void ataqueMonstruo() {
    // Session session = null;
    // Mago mago = null;
    // Monstruo monstruo = null;
    // try (SessionFactory factory = new
    // Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {

    // session = factory.getCurrentSession();
    // Transaction tx = session.beginTransaction();
    // mago = session.find(Mago.class, 1);
    // monstruo = session.find(Monstruo.class, 1);
    // tx.commit();
    // } catch (Exception e) {
    // System.out.println("Problemas ataque el monstruo");
    // }
    // System.out.println(monstruo.getNombre());
    // if (mago.getVida() <= 0) {
    // System.out.println("el mago murio");
    // }else{
    // monstruo.atacar(mago);
    // }
    // System.out.println(mago.getVida());
    // }


       /**
     * Cambia el monstruo jefe de un bosque. Obtiene el bosque con ID 1 y asigna
     * como nuevo jefe al monstruo con ID 2. Luego persiste el cambio en la base
     * de datos.
     */
    public void cambiarMonstruo() {
        Session session = null;
        Bosque bosque = null;
        Monstruo monstruo = null;
        Transaction tx = null;
        try (SessionFactory factory = conn.getFactory()) {

            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            bosque = session.find(Bosque.class, 1);
            monstruo = session.find(Monstruo.class, 2);
            bosque.setMonstruoJefe(monstruo);
            System.out.println("El nuevo jefe del bosque es " + monstruo.getNombre());

            tx.commit();
        } catch (Exception e) {
            System.out.println("Problemas ataque el monstruo");
            tx.rollback();
        }

    }
    /**
     * Ejecuta una batalla entre un mago y un monstruo cargados desde la base de datos
     * (por defecto los de ID 1). Ambos atacan por turnos hasta que uno muere.
     * 
     * @return un mensaje indicando quién murió primero ("monstruo murio" o "mago murio").
     */
    public String hacerBatalla() {
        Session session = null;
        Monstruo monstruo = null;
        Mago mago = null;
        Transaction tx = null;

        try (SessionFactory factory = conn.getFactory()) {

            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            mago = session.find(Mago.class, 1);
            monstruo = session.find(Monstruo.class, 1);
            while (monstruo.getVida() > 0 && mago.getVida() > 0) {
                monstruo.atacar(mago);
                session.merge(mago);
                mago.ataque(monstruo);
                session.merge(monstruo);
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("Problemas batalla" + e.getMessage());
            tx.rollback();
        }

        String frase = "";
        if (monstruo.getVida() <= 0) {
            frase = "monstruo murio";
        } else if (mago.getVida() <= 0) {
            frase = "mago murio";
        }

        return frase;
    }

    /**
     * Crea un nuevo bosque. Obtiene tres monstruos de la base de datos y utiliza la vista
     * para construir un objeto Bosque que luego se persiste.
     */
    public void crearBosque() {
        Session session = null;
        Transaction tx = null;
        try (SessionFactory factory = conn.getFactory()) {
            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            Monstruo jefe = session.find(Monstruo.class, 1);
            Monstruo m1 = session.find(Monstruo.class, 2);
            Monstruo m2 = session.find(Monstruo.class, 3);
            Dragon dragon = session.find(Dragon.class, 1);
            List<Monstruo> monstruos = Arrays.asList(jefe,m1,m2);
            Bosque bosque = vista.getValoresBosque(jefe, monstruos,dragon);
            session.persist(bosque);
            tx.commit();

        } catch (Exception e) {
            System.out.println("Problemas crendo el bosque");
            tx.rollback();
        }

    }


    public void crearDragon(){
        Session session = null;
        Transaction tx = null;
        try(SessionFactory factory = conn.getFactory()){
            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            Dragon dragon = vista.getValoresDragon();
            session.persist(dragon);
            tx.commit();
        }catch(Exception e){
            System.out.println("Problemas creando dragon");
            tx.rollback();
        }

    }
     /**
     * Muestra un menú interactivo en consola que permite al usuario elegir acciones
     * como crear magos, monstruos, iniciar batallas o modificar el bosque. Las acciones
     * se ejecutan en un bucle hasta que el usuario ingresa la opción para salir.
     */
    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean it = true;
        while (it) {
            System.out.println("1-Crear mago \n2-Crear monstruo \n3-hacer batalla \n4-cambiar mostruo \n5-Crear bosque"+
                " \n6-Crear dragon"
            );
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc) {
                case 1:
                    crearMago();
                    break;
                case 2:
                    crearMostruo();
                case 3:
                    String frase = hacerBatalla();
                    vista.mostrarGanador(frase);
                    break;

                case 4:
                    cambiarMonstruo();
                    break;
                case 5:
                    crearBosque();
                    break;
                case 6:
                    crearDragon();
                    break;
                case 7:
                    it = false;
                default:
                    break;
            }

        }
    }

}
