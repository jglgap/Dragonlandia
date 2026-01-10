package project.controllers;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.connections.Manager;
import project.models.Hechizo;
import project.models.Mago;
import project.models.Monstruo;
import project.views.BattleView;

public class MostruoController {

    Manager mang = null;
    BattleView vista = null;

    MostruoController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
    }

    public void crearMostruo() {

        Monstruo monstruoCreado = vista.getValoresMonstruo();

        try (EntityManager em = mang.getEMF().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(monstruoCreado);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Problemas creando el monstruo");

        }
    }

    public void actualizarMonstruo() {
        Monstruo monstruo = null;
        EntityTransaction tx = null;
        int idMonstruo = vista.getId();
        int poderMagico = vista.getNuevaFuerza();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            monstruo = em.find(Monstruo.class, idMonstruo);
            monstruo.setFuerza(poderMagico);
            em.merge(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error actualizando monstruo");
            tx.rollback();
        }
    }

    public void eliminarMonstruo() {
        Monstruo monstruo = null;
        EntityTransaction tx = null;
        int idMonstruo = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            monstruo = em.find(Monstruo.class, idMonstruo);
            em.remove(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrando monstruo");
            tx.rollback();
        }
    }

    public String buscarMonstruo() {
        Monstruo monstruo = null;
        EntityTransaction tx = null;
        int idMonstruo = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            monstruo = em.find(Monstruo.class, idMonstruo);
            tx.commit();
            return monstruo.toString();

        } catch (Exception e) {
            tx.rollback();
            return "Error busqueda monstruo";
        }
    }

}
