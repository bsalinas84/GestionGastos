package gestiongastos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bárbara Salinas
 * 
 * Clase del modelo que representa la entidad Empleados de BBDD.
 *
 */
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

	private static final long serialVersionUID = -5111806001694002954L;
	
	@Id
	private String dni;

	@Column
	private String nombre;
	
	@Column
	private float precioguardia;
	
	@Column
	private float preciohoraextra;
	
	@Column
	private long cliente;
	
	@Column
	private boolean freelance;
	
	/**
	 * Obtiene el valor del campo freelance
	 * 
	 * @return freelance
	 */
	public boolean isFreelance() {
		return this.freelance;
	}

	/**
	 * Asigna valor al campo freelance
	 * 
	 * @param freelance valor a introducir en BBDD
	 */
	public void setFreelance(boolean freelance) {
		this.freelance = freelance;
	}

	/**
	 * Asigna valor al campo cliente para un empleado
	 * 
	 * @param cliente
	 */
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	/**
	 * Obtiene el valor del campo dni
	 * 
	 * @return dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * Asigna valor al campo dni
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Obtiene el valor del campo nombre
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

	/**
	 * Obtiene el valor del campo precioguardia
	 * 
	 * @return precioguardia
	 */
	public float getPrecioguardia() {
		return this.precioguardia;
	}

	/**
	 * Asigna valor al campo precioguardia
	 * 
	 * @param precioguardia
	 */
	public void setPrecioguardia(float precioguardia) {
		this.precioguardia = precioguardia;
	}

	/**
	 * Obtiene el valor del campo preciohoraextra
	 * 
	 * @return preciohoraextra
	 */
	public float getPreciohoraextra() {
		return this.preciohoraextra;
	}

	/**
	 * Asigna valor al campo preciohoraextra
	 * 
	 * @param preciohoraextra
	 */
	public void setPreciohoraextra(float preciohoraextra) {
		this.preciohoraextra = preciohoraextra;
	}

	/**
	 * Obtiene el valor del campo cliente
	 * 
	 * @return cliente
	 */
	public long getCliente() {
		return this.cliente;
	}
}