package project.controllers;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.Main;
import project.connections.Manager;
import project.models.Bosque;
import project.models.Dragon;
import project.models.Hechizo;
import project.models.Mago;
import project.models.Monstruo;
import project.views.BattleView;

public class MainController {

    private BattleView vista;
    private Manager mang;
    MagoController magoController = null;
    MostruoController mostruoController = null;
    DragonController dragonController = null;
    BosqueController bosqueController = null;
    HechizoController hechizoController = null;

    public MainController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
        crearHechizosBase();
        this.magoController = new MagoController(mang, vista);
        this.mostruoController = new MostruoController(mang, vista);
        this.dragonController = new DragonController(mang, vista);
        this.bosqueController = new BosqueController(mang, vista);
        this.hechizoController = new HechizoController(mang, vista);
        menu();

    }

    public void crearHechizosBase() {
        EntityTransaction tx = null;
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            Long count = em.createQuery("SELECT COUNT(h) FROM Hechizo h", Long.class)
                    .getSingleResult();
            if (count != 0) {
                System.out.println("Ya estan creados los hechizos iniciales");
                tx.commit();
            } else {
                Hechizo bolaFuego = new Hechizo("Bola de fuego", "Llamarada en forma de bola abrasadora");
                Hechizo rayo = new Hechizo("Rayo", "Invocacion de un rayo de los dioses");
                Hechizo bolaNieve = new Hechizo("Bola de nieve",
                        "Una bola de nieve que congela hasta la muerte al enemigo");
                Hechizo atormentacion = new Hechizo("Atormentacion", "Una nublacion en la mente del enemigo");
                em.persist(bolaFuego);
                em.persist(rayo);
                em.persist(bolaNieve);
                em.persist(atormentacion);
                tx.commit();
            }

        } catch (Exception e) {
            System.out.println("Error al crear los hechizos base");
            tx.rollback();
        }

    }

    private boolean equipoMagoVivo(Mago mago1, Mago mago2, Dragon dragon) {
        return (mago1 != null && mago1.getVida() > 0) ||
                (mago2 != null && mago2.getVida() > 0) ||
                (dragon != null && dragon.getResistencia() > 0);
    }

    private boolean equipoMonstruoVivo(Monstruo m1, Monstruo m2, Monstruo m3) {
        return (m1 != null && m1.getVida() > 0) ||
                (m2 != null && m2.getVida() > 0) ||
                (m3 != null && m3.getVida() > 0);
    }

    public void hacerBatalla() {
        Mago mago1 = null;
        Mago mago2 = null;
        Bosque bosque = null;
        Monstruo m1 = null;
        Monstruo m2 = null;
        Monstruo m3 = null;
        Dragon dragon = null;
        Hechizo hechizo1 = null;
        Hechizo hechizo2 = null;
        Hechizo hechizo3 = null;
        Hechizo hechizo4 = null;

        EntityTransaction tx = null;
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            mago1 = em.find(Mago.class, 1);
            mago2 = em.find(Mago.class, 2);
            bosque = em.find(Bosque.class, 1);
            m1 = bosque.getListaMonstruos().get(0);
            m2 = bosque.getListaMonstruos().get(1);
            m3 = bosque.getListaMonstruos().get(2);
            dragon = bosque.getDragon();
            hechizo1 = em.find(Hechizo.class, 1);
            hechizo2 = em.find(Hechizo.class, 2);
            hechizo3 = em.find(Hechizo.class, 3);
            hechizo4 = em.find(Hechizo.class, 4);
            while (equipoMagoVivo(mago1, mago2, dragon) && equipoMonstruoVivo(m1, m2, m3)) {
                mago1.ataque(m3);
                em.merge(m3);
                mago2.ataque(m1);
                em.merge(m3);
                dragon.exhalar(m2);
                em.merge(m2);
                m1.atacar(mago2);
                em.merge(mago2);
                m2.atacar(mago1);
                em.merge(mago1);
                m3.atacar(mago2);
                em.merge(mago2);
                mago1.lanzarHechizo(m1, hechizo1);
                em.merge(m1);
                mago2.lanzarHechizo(m3, hechizo2);
                em.merge(m3);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("error haciendo batalla" + e.getMessage());
        }
        if (!equipoMagoVivo(mago1, mago2, dragon)) {
            System.out.println("¡Los monstruos han ganado!");
        } else if (!equipoMonstruoVivo(m1, m2, m3)) {
            System.out.println("¡Los magos y el dragón han ganado!");
        } else {
            System.out.println("Empate inesperado..."); // casi imposible
        }
    }

    public void menuMago() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones del mago");
        System.out.println("1.-Crear mago \n2-Editar mago \n3-Borrar mago \n4-Mostrar mago");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1:
                magoController.crearMago();
                break;
            case 2:
                magoController.actualizarMago();
                break;
            case 3:
                magoController.eliminarmago();
                break;
            case 4:
                System.out.println(magoController.buscarMago());
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void menuMonstruo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones del monstruo");
        System.out.println("1.-Crear monstruo \n2-Editar monstruo \n3-Borrar monstruo \n4-Mostrar monstruo");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1:
                mostruoController.crearMostruo();
                break;
            case 2:
                mostruoController.actualizarMonstruo();
                break;
            case 3:
                mostruoController.eliminarMonstruo();
                break;
            case 4:
                mostruoController.buscarMonstruo();
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void menuDragon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones del dragon");
        System.out.println("1.-Crear dragon \n2-Editar dragon \n3-Borrar dragon \n4-Mostrar dragon");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1:
                dragonController.crearDragon();
                break;
            case 2:
                dragonController.actualizarDragon();
                break;
            case 3:
                dragonController.eliminarDragon();
                break;
            case 4:
                dragonController.buscarDragon();
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void menuBosque() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones del bosque");
        System.out.println("1.-Crear bosque \n2-Editar bosque \n3-Borrar bosque \n4-Mostrar bosque");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1:
                bosqueController.crearBosque();
                break;
            case 2:
                bosqueController.actualizarBosque();
                break;
            case 3:
                bosqueController.eliminarBosque();
                break;
            case 4:
                bosqueController.buscarBosque();
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void menuHechizos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opciones del hechizo");
        System.out.println("1.-Crear hechizo \n2-Editar hechizo \n3-Borrar hechizo \n4-Mostrar hechizo");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1:
                hechizoController.crearHechizo();
                break;
            case 2:
                hechizoController.actualizarHechizo();
                break;
            case 3:
                hechizoController.eliminarHechizo();
                break;
            case 4:
                hechizoController.buscarHechizo();
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean it = true;
        while (it) {
            System.out.println("1-Funciones Mago \n2-Funciones Monstruo \n3-Funciones Dragon" +
                    "\n4-Funciones Bosque \n5-Funciones Hechizos" +
                    "\n6-Hacer batalla");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc) {
                case 1:
                    menuMago();
                    break;
                case 2:
                    menuMonstruo();
                    break;
                case 3:
                    menuDragon();
                    break;

                case 4:
                    menuBosque();
                    break;
                case 5:
                    menuHechizos();
                    break;
                case 6:
                    hacerBatalla();
                    break;
                case 7:
                    it = false;
                default:
                    break;
            }

        }
    }

}
