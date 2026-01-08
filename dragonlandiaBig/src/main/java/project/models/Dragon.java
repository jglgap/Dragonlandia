package project.models;
/**
 * Representa un dragón dentro del sistema.
 * <p>
 * Esta entidad está mapeada a la tabla <b>dragones</b> de la base de datos
 * mediante JPA. Un dragón posee un nombre, una intensidad de fuego y un nivel
 * de resistencia.
 * </p>
 */

import jakarta.persistence.*;

@Entity
@Table(name = "dragones")
public class Dragon {

    /**
     * Identificador único del dragón.
     * <p>
     * Se genera automáticamente utilizando la estrategia IDENTITY.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del dragón.
     */
    private String nombre;

    /**
     * Intensidad del fuego que el dragón puede exhalar.
     */
    private int intesidadFuego;

    /**
     * Nivel de resistencia del dragón.
     */
    private int resistencia;


    /**
     * Constructor vacío requerido por JPA.
     */
    public Dragon() {
    }

    

    /**
     * Constructor que permite crear un dragón con sus atributos principales.
     *
     * @param nombre nombre del dragón
     * @param intesidadFuego nivel de intensidad del fuego
     * @param resistencia nivel de resistencia del dragón
     */
    public Dragon(String nombre, int intesidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intesidadFuego = intesidadFuego;
        this.resistencia = resistencia;
    }



    /**
     * Permite al dragón exhalar fuego contra un monstruo.
     * <p>
     * El daño causado reduce la vida del monstruo según la intensidad
     * de fuego del dragón.
     * </p>
     *
     * @param monstruo el monstruo que recibe el ataque
     */
    public void exhalar(Monstruo monstruo){
        monstruo.setVida(monstruo.getVida() - this.intesidadFuego);
    }


    /**
     * Obtiene el identificador del dragón.
     *
     * @return id del dragón
     */
    public int getId() {
        return id;
    }


    /**
     * Establece el identificador del dragón.
     *
     * @param id identificador a asignar
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Obtiene el nombre del dragón.
     *
     * @return nombre del dragón
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Establece el nombre del dragón.
     *
     * @param nombre nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Obtiene la intensidad del fuego del dragón.
     *
     * @return intensidad de fuego
     */
    public int getIntesidadFuego() {
        return intesidadFuego;
    }


    /**
     * Establece la intensidad del fuego del dragón.
     *
     * @param intesidadFuego intensidad de fuego a asignar
     * @throws IllegalArgumentException si la intensidad es menor que 0
     */
    public void setIntesidadFuego(int intesidadFuego) {
        if (intesidadFuego < 0) {
            throw new IllegalArgumentException("El nivel de intensidad de fuego no puede ser menor que 0");
        }
        this.intesidadFuego = intesidadFuego;
    }


    /**
     * Obtiene el nivel de resistencia del dragón.
     *
     * @return resistencia del dragón
     */
    public int getResistencia() {
        return resistencia;
    }


    /**
     * Establece el nivel de resistencia del dragón.
     * <p>
     * Si el valor recibido es menor que 0, la resistencia se ajusta a 0.
     * </p>
     *
     * @param resistencia nivel de resistencia a asignar
     */
    public void setResistencia(int resistencia) {
        if(resistencia < 0){
            resistencia = 0;
        }

        this.resistencia = resistencia;
    }

    

} 