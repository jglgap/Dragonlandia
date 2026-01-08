package project.models;

import java.util.List;

import jakarta.persistence.*;

/**
 * Representa un bosque en el sistema.
 * Cada bosque tiene un nombre, un nivel de peligro y un monstruo jefe asociado.
 */
@Entity
@Table(name = "bosques")
public class Bosque {

    /**
     * Identificador único del bosque.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del bosque.
     */
    private String nombre;

    /**
     * Nivel de peligro del bosque.
     */
    private int nivelPeligro;

    /**
     * Monstruo jefe que habita en el bosque.
     */
    @OneToOne
    @JoinColumn(name = "monstruo_id", nullable = false)
    private Monstruo monstruoJefe;



    @OneToMany(targetEntity = Monstruo.class )
    private List<Monstruo> listaMonstruos;


    @OneToOne
    @JoinColumn(name = "dragon_id", nullable = false)
    private Dragon dragon;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Bosque() {}

    /**
     * Constructor que inicializa el bosque con nombre, nivel de peligro y monstruo jefe.
     * 
     * @param nombre Nombre del bosque.
     * @param nivelPeligro Nivel de peligro del bosque.
     * @param monstruoJefe Monstruo jefe del bosque.
     */
    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, List<Monstruo> monstruos,Dragon dragon) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
        this.listaMonstruos = monstruos;
        this.dragon = dragon;
    }

    /**
     * Obtiene el identificador del bosque.
     * 
     * @return id del bosque.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del bosque.
     * 
     * @param id Identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del bosque.
     * 
     * @return nombre del bosque.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del bosque.
     * 
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nivel de peligro del bosque.
     * 
     * @return nivel de peligro.
     */
    public int getNivelPeligro() {
        return nivelPeligro;
    }

    /**
     * Establece el nivel de peligro del bosque.
     * 
     * @param nivelPeligro Nivel de peligro a asignar.
     */
    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    /**
     * Obtiene el monstruo jefe del bosque.
     * 
     * @return monstruo jefe.
     */
    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    /**
     * Establece el monstruo jefe del bosque.
     * 
     * @param monstruoJefe Monstruo a asignar como jefe.
     */
    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    
    public List<Monstruo> getListaMonstruos() {
        return listaMonstruos;
    }

    public void setListaMonstruos(List<Monstruo> listaMonstruos) {
        this.listaMonstruos = listaMonstruos;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }


    public void añadirMonstruo(Monstruo monstruo){
        this.listaMonstruos.add(monstruo);
        
    }
    /**
     * Muestra por consola los datos del monstruo jefe del bosque.
     * Incluye nombre, vida, tipo y fuerza.
     */
    public void mostrarDatos(){
        System.out.println("Nombre del monstruo Jefe = " + getMonstruoJefe().getNombre());
        System.out.println("Vida del monstruo jefe = " + getMonstruoJefe().getVida());
        System.out.println("Tipo del monstruo jefe = " + getMonstruoJefe().getTipo());
        System.out.println("Fuerza del monstruo jefe = " + getMonstruoJefe().getFuerza());
    }
}
