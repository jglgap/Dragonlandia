package project.models;

import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Representa un monstruo en el sistema.
 * Cada monstruo tiene un nombre, vida, tipo y fuerza, y puede atacar magos.
 */
@Entity
@Table(name = "monstruos")
public class Monstruo {

    /**
     * Identificador único del monstruo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del monstruo.
     */
    private String nombre;

    /**
     * Vida actual del monstruo. No puede ser negativa.
     */
    @PositiveOrZero
    private int vida;

    /**
     * Tipo del monstruo.
     */
    private TipoMonstruo tipo;

    /**
     * Fuerza del monstruo, utilizada para calcular daño en ataques.
     */
    private int fuerza;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Monstruo() {}

    /**
     * Constructor que inicializa un monstruo con nombre, vida, tipo y fuerza.
     * 
     * @param nombre Nombre del monstruo.
     * @param vida Vida inicial del monstruo (>=0).
     * @param tipo Tipo del monstruo.
     * @param fuerza Fuerza del monstruo.
     */
    public Monstruo(String nombre, @PositiveOrZero int vida, TipoMonstruo tipo, int fuerza) {
        this.nombre = nombre;
        this.vida = vida;
        this.tipo = tipo;
        this.fuerza = fuerza;
    }

    /**
     * Obtiene el identificador del monstruo.
     * 
     * @return id del monstruo.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del monstruo.
     * 
     * @param id Identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del monstruo.
     * 
     * @return nombre del monstruo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del monstruo.
     * 
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la vida del monstruo.
     * 
     * @return vida del monstruo.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Establece la vida del monstruo.
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
     * Obtiene el tipo del monstruo.
     * 
     * @return tipo del monstruo.
     */
    public TipoMonstruo getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del monstruo.
     * 
     * @param tipo Tipo a asignar.
     */
    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fuerza del monstruo.
     * 
     * @return fuerza del monstruo.
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Establece la fuerza del monstruo.
     * 
     * @param fuerza Fuerza a asignar.
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * Realiza un ataque contra un mago reduciendo su vida según la fuerza del monstruo.
     * La actualización se realiza en la base de datos usando Hibernate.
     * 
     * @param mago Mago objetivo del ataque.
     */
    public void atacar(Mago mago){
            mago.setVida(mago.getVida() - this.fuerza);
    }

}
