package gestiongastos.view;

import java.util.Date;

/**
 * @author Bárbara Salinas
 * 
 * Objeto para la capa de vista relacionado con la entidad Concepto
 *
 */
public class ConceptoView {
	
	private int id;
	
	private String dni;	
	
	private String tipo;	
	
	private float cantidad;	
	
	private String nombreEmpleado;
	
	private String nombreCliente;
	
	private float importe;
	
	private String observaciones;
	
	private boolean isFreelance;
	
	private String anotaciones;
	
	private Date fechaRegistro;
	
	/**
	 * Obtiene las anotaciones del concepto
	 * 
	 * @return the anotaciones
	 */
	public String getAnotaciones() {
		return this.anotaciones;
	}

	/**
	 * Asigna el valor a las anotaciones
	 * 
	 * @param anotaciones
	 */
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	/**
	 * Obtiene si el empleado es o no freelance
	 * 
	 * @return isFreelance
	 */
	public boolean isFreelance() {
		return this.isFreelance;
	}

	/**
	 * Asigna si el empleado es o no freelance
	 * 
	 * @param isFreelance
	 */
	public void setFreelance(boolean isFreelance) {
		this.isFreelance = isFreelance;
	}

	/**
	 * Obtiene el valor del campo nombreEmpleado
	 * 
	 * @return nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return this.nombreEmpleado;
	}

	/**
	 * Asigna valor al campo nombreEmpleado
	 * 
	 * @param nombreEmpleado
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	/**
	 * Obtiene el valor del campo nombreCliente
	 * 
	 * @return nombreCliente
	 **/
	public String getNombreCliente() {
		return this.nombreCliente;
	}

	/**
	 * Asigna valor al campo nombreCliente
	 * 
	 * @param nombreCliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * Obtiene el valor fechaResgistro
	 * 
	 * @return fechaRegistro
	 */
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	/**
	 * Asigna el valor fechaRegistro
	 * 
	 * @param fechaRegistro 
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
