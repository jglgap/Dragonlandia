package project.models;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Representa un mago en el sistema.
 * Un mago tiene un nombre, vida, nivel de magia y puede atacar monstruos.
 */
@Entity
@Table(name="magos")
public class Mago {

    /**
     * Identificador único del mago.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del mago.
     */
    private String nombre;

    /**
     * Vida actual del mago. No puede ser negativa.
     */
    @PositiveOrZero 
    private int vida;

    /**
     * Nivel de magia del mago. No puede ser negativo.
     */
    @PositiveOrZero
    private int nivelMagia;

    /**
     * Constructor vacío requerido por JPA.
     */


    @OneToMany(targetEntity = Hechizo.class)
    private List<Hechizo> conjuros;


    public Mago() {}

    /**
     * Constructor que inicializa un mago con nombre, vida y nivel de magia.
     * 
     * @param nombre Nombre del mago.
     * @param vida Vida inicial del mago (>=0).
     * @param nivelMagia Nivel de magia inicial del mago (>=0).
     */
    public Mago(String nombre, @PositiveOrZero int vida, @PositiveOrZero int nivelMagia,List<Hechizo> conjuros) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
        this.conjuros = conjuros;
    }

    /**
     * Obtiene el identificador del mago.
     * 
     * @return id del mago.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del mago.
     * 
     * @param id Identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del mago.
     * 
     * @return nombre del mago.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del mago.
     * 
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la vida del mago.
     * 
     * @return vida del mago.
     * @throws IllegalArgumentException si la vida es menor que 0.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Establece la vida del mago.
     * 
     * @param vida Vida a asignar (>=0).
     * @throws IllegalArgumentException si la vida es menor que 0.
     */
    public void setVida(int vida) {
        if (vida < 0) {

            vida = 0;
        }
        this.vida = vida;
    }

    /**
     * Obtiene el nivel de magia del mago.
     * 
     * @return nivel de magia.
     * @throws IllegalArgumentException si el nivel de magia es menor que 0.
     */
    public int getNivelMagia() {
        if (nivelMagia < 0) {
            throw new IllegalArgumentException("El nivel de magia del mago no puede ser menor que 0");
        }
        return nivelMagia;
    }

    /**
     * Establece el nivel de magia del mago.
     * 
     * @param nivelMagia Nivel de magia a asignar (>=0).
     */
    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    /**
     * Realiza un ataque contra un monstruo reduciendo su vida según el nivel de magia del mago.
     * La actualización se realiza en la base de datos usando Hibernate.
     * 
     * @param monstruo Monstruo objetivo del ataque.
     */
    public void ataque(Monstruo monstruo){
    
            monstruo.setVida(monstruo.getVida() - this.nivelMagia);
      
    }

    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo){
        String nombre = hechizo.getNombre();
        String bolaFuego = "Bola de fuego";  
        String rayo = "Rayo";
        String bolaNieve = "Bola de nieve";
        String atormentacion = "Atormentacion";
        for (Hechizo conjuro : conjuros) {
            if (nombre.equals(conjuro.getNombre()) && nombre.equals(bolaFuego.toLowerCase())) {
               monstruo.setVida((int) (monstruo.getVida() - (monstruo.getVida()*0.75)));
               System.out.println("ALLAHUA AKBARRRRRRRRRRRRRRRRRRRRRRRRR");
            }else if (nombre.equals(conjuro.getNombre()) && nombre.equals(rayo.toLowerCase())) {
                monstruo.setVida((int) (monstruo.getVida() - (monstruo.getVida()*0.50)));
                System.out.println("Cuchaooo");
            }else if(nombre.equals(conjuro.getNombre() ) && nombre.equals(bolaNieve.toLowerCase())){
                monstruo.setVida(monstruo.getVida() - monstruo.getVida());
                System.out.println("Congeladooooo, gg ez");
            }else if(nombre.equals(conjuro.getNombre() ) && nombre.equals(atormentacion.toLowerCase())){
                monstruo.setVida((int) (monstruo.getVida() - (monstruo.getVida()*0.10)));
                System.out.println("Buuhhhhhhhhh");
            }
            else{
                this.setVida(this.getVida() - 1);
                throw new NullPointerException();
            }
        
        }

    }

}
