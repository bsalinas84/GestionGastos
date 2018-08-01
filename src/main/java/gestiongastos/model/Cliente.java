package gestiongastos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bárbara Salinas
 * 
 * Clase del modelo que representa la entidad Clientes de BBDD
 *
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String nombre;

	/**
	 * Constructor
	 */
	public Cliente() {
		super();
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param id Identificador de cliente
	 * @param nombre Nombre de cliente
	 */
	public Cliente(int id, String nombre) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNombre(nombre);
	}

	

	/**
	 * Obtiene el valor del campo id
	 * 
	 * @return id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Asigna valor al campo id
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtiene el valor del campo nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Asigna valor al campo nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}