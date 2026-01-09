package project.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.connections.Manager;
import project.models.Dragon;
import project.models.Hechizo;
import project.views.BattleView;

public class HechizoController {

  Manager mang = null;
    BattleView vista = null;

    HechizoController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
    }

    public void crearHechizo() {

        EntityTransaction tx = null;
        try (EntityManager em = mang.getEMF().createEntityManager()) {

            tx = em.getTransaction();
            tx.begin();
            Hechizo hechizo = vista.getValoresHechizo();
            em.persist(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Problemas creando hechizo");
            tx.rollback();
        }

    }

    public void actualizarHechizo() {
        Hechizo hechizo = null;
        EntityTransaction tx = null;
        int idHechizo = vista.getId();
        String descripcion = vista.nuevaDescripcion();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            hechizo = em.find(Hechizo.class, idHechizo);
            hechizo.setDescripcion(descripcion);
            em.merge(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error actualizando hechizo");
            tx.rollback();
        }
    }

    public void eliminarHechizo() {
        Hechizo hechizo = null;
        EntityTransaction tx = null;
        int idHechizo = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            hechizo = em.find(Hechizo.class, idHechizo);
            em.remove(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrando hechizo");
            tx.rollback();
        }
    }

    public String buscarHechizo() {
        Hechizo hechizo = null;
        EntityTransaction tx = null;
        int idHechizo = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            hechizo = em.find(Hechizo.class, idHechizo);
            tx.commit();
            return hechizo.toString();

        } catch (Exception e) {
            tx.rollback();
            return "Error busqueda hechizo";
        }
    }




}
