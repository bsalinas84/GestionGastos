package gestiongastos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bárbara Salinas
 * 
 * Clase del modelo que representa la entidad Conceptos de BBDD
 *
 */
@Entity
@Table(name = "conceptos")
public class Concepto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String dni;	
	
	@Column
	private String tipo;	
	
	@Column
	private float cantidad;	
	
	@Column
	private Date fecharegistro;
	
	@Column
	private float importe;
	
	@Column
	private String observaciones;
	
	@Column
	private String anotaciones;
	
	

	/**
	 * Obtiene el valor del campo anotaciones
	 * 
	 * @return anotaciones
	 */
	public String getAnotaciones() {
		return this.anotaciones;
	}

	/**
	 * Asigna el valor del campo anotaciones
	 * 
	 * @param anotaciones
	 */
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	/**
	 * Obtiene el valor del campo observaciones
	 * 
	 * @return observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Asigna valor al campo observaciones
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Obtiene el valor del campo inporte
	 * 
	 * @return importe
	 */
	public float getImporte() {
		return this.importe;
	}

	/**
	 * Asigna valor al campo importe
	 * 
	 * @param importe
	 */
	public void setImporte(float importe) {
		this.importe = importe;
	}

	/**
	 * Obtiene el valor del campo id
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Asigna valor al campo id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Obtiene el valor del campo tipo
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return this.tipo;
	}
	
	/**
	 * Asigna valor al campo tipo
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el valor del campo cantidad
	 * 
	 * @return cantidad
	 */
	public float getCantidad() {
		return this.cantidad;
	}
	
	/**
	 * Asigna valor al campo cantidad
	 * 
	 * @param cantidad
	 */
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Obtiene el valor del campo fecharegistro
	 * 
	 * @return fecharegistro
	 */
	public Date getFecharegistro() {
		return this.fecharegistro;
	}
	
	/**
	 * Asigna valor al campo fecharegistro
	 * 
	 * @param fecharegistro
	 */
	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
}
