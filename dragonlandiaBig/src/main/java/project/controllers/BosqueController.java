package project.controllers;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.connections.Manager;
import project.models.Bosque;
import project.models.Dragon;
import project.models.Monstruo;
import project.views.BattleView;

public class BosqueController {

    Manager mang = null;
    BattleView vista = null;

    BosqueController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
    }

    public void crearBosque() {

        EntityTransaction tx = null;
        try (EntityManager em = mang.getEMF().createEntityManager()) {

            tx = em.getTransaction();
            tx.begin();
            Monstruo jefe = em.find(Monstruo.class, 1);
            Monstruo m1 = em.find(Monstruo.class, 2);
            Monstruo m2 = em.find(Monstruo.class, 3);
            Dragon dragon = em.find(Dragon.class, 1);
            List<Monstruo> monstruos = Arrays.asList(jefe, m1, m2);
            Bosque bosque = vista.getValoresBosque(jefe, monstruos, dragon);
            em.persist(bosque);
            tx.commit();

        } catch (Exception e) {
            System.out.println("Problemas crendo el bosque");
            tx.rollback();
        }

    }

    public void actualizarBosque() {
        Bosque bosque = null;
        EntityTransaction tx = null;
        int idBosque = vista.getId();
        int nivelDificultad = vista.getNuevaFuerza();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            bosque = em.find(Bosque.class, idBosque);
            bosque.setNivelPeligro(nivelDificultad);
            em.merge(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error actualizando bosque");
            tx.rollback();
        }
    }

    public void eliminarBosque() {
        Bosque bosque = null;
        EntityTransaction tx = null;
        int idBosque = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            bosque = em.find(Bosque.class, idBosque);
            em.remove(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrando bosque");
            tx.rollback();
        }
    }

    public String buscarBosque() {
        Bosque bosque = null;
        EntityTransaction tx = null;
        int idBosque = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            bosque = em.find(Bosque.class, idBosque);
            tx.commit();
            return bosque.toString();

        } catch (Exception e) {
            tx.rollback();
            return "Error busqueda bosque";
        }
    }

}
