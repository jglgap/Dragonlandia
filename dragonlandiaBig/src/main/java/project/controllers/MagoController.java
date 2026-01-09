package project.controllers;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.connections.Manager;
import project.models.Hechizo;
import project.models.Mago;
import project.views.BattleView;

public class MagoController {

    Manager mang = null;
    BattleView vista = null;

    MagoController(Manager mang, BattleView vista) {
        this.mang = mang;
        this.vista = vista;
    }

    public void crearMago() {

        Mago magoCreado = null;

        try (EntityManager em = mang.getEMF().createEntityManager()) {
            em.getTransaction().begin();
            Hechizo bolaFuego = em.find(Hechizo.class, 1);
            Hechizo rayo = em.find(Hechizo.class, 2);
            Hechizo bolaNieve = em.find(Hechizo.class, 3);
            Hechizo atormentacion = em.find(Hechizo.class, 4);
            List<Hechizo> conjuros = Arrays.asList(bolaFuego, rayo, bolaNieve, atormentacion);
            magoCreado = vista.getValoresmago(conjuros);
            em.persist(magoCreado);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Problemas crendo el mago");

        }
    }


    public void actualizarMago(){
        Mago mago = null;
        EntityTransaction tx = null;
        int idMago = vista.getId();
        int poderMagico = vista.getNuevaFuerza();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            mago = em.find(Mago.class, idMago);
            mago.setNivelMagia(poderMagico);
            em.merge(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error actualizando mago");
            tx.rollback();
        }
    }


    public void eliminarmago(){
        Mago mago = null;
        EntityTransaction tx = null;
        int idMago = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            mago = em.find(Mago.class, idMago);
            em.remove(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrando mago");
            tx.rollback();
        }
    }

    public String buscarMago(){
        Mago mago = null;
        EntityTransaction tx = null;
        int idMago = vista.getId();
        try (EntityManager em = mang.getEMF().createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            mago = em.find(Mago.class, idMago);
            tx.commit();
            return mago.toString();


        } catch (Exception e) {
            tx.rollback();
            return "Error busqueda mago";
        }
    }

}
