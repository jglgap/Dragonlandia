package project.controllers;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.connections.Manager;
import project.models.Dragon;
import project.models.Hechizo;
import project.models.Mago;
import project.views.BattleView;

public class DragonController {

    Manager mang = null;
    BattleView vista = null;

    DragonController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
    }

    public void crearDragon() {

        EntityTransaction tx = null;
        try (EntityManager em = mang.getEMF().createEntityManager()) {

            tx = em.getTransaction();
            tx.begin();
            Dragon dragon = vista.getValoresDragon();
            em.persist(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Problemas creando dragon");
            tx.rollback();
        }

    }

    public void actualizarDragon() {
        Dragon dragon = null;
        EntityTransaction tx = null;
        int idDragon = vista.getId();
        int intensidadFuego = vista.getNuevaFuerza();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            dragon = em.find(Dragon.class, idDragon);
            dragon.setIntesidadFuego(intensidadFuego);
            em.merge(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error actualizando dragon");
            tx.rollback();
        }
    }

    public void eliminarDragon() {
        Dragon dragon = null;
        EntityTransaction tx = null;
        int idDragon = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            dragon = em.find(Dragon.class, idDragon);
            em.remove(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrando dragon");
            tx.rollback();
        }
    }

    public String buscarDragon() {
        Dragon dragon = null;
        EntityTransaction tx = null;
        int idDragon = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            dragon = em.find(Dragon.class, idDragon);
            tx.commit();
            return dragon.toString();

        } catch (Exception e) {
            tx.rollback();
            return "Error busqueda dragon";
        }
    }

}
