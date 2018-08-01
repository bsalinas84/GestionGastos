package gestiongastos.view;

/**
 * @author Bárbara Salinas
 * 
 * Objeto para la capa de vista relacionado con la entidad Empleado
 *
 */
public class EmpleadoView  {

	private String dni;
	private String nombre;
	private String precioguardia;
	private String preciohoraextra;
	private long cliente;
	private String nCliente;
	private boolean freelance;
	
	
	
	/**
	 * Contructor
	 */
	public EmpleadoView() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param dni dni del empleado
	 * @param nombre nombre del empleado
	 * @param precioguardia precio de guardia para el empleado
	 * @param preciohoraextra precio de la hora extra para el empleado
	 * @param cliente cliente al que está asignado el empleado
	 * @param nCliente nombre del cliente al que está asignado el empleado
	 * @param freelance si es freelance o no el empleado
	 */
	public EmpleadoView(String dni, String nombre, String precioguardia, String preciohoraextra, long cliente,
			String nCliente, boolean freelance) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.precioguardia = precioguardia;
		this.preciohoraextra = preciohoraextra;
		this.cliente = cliente;
		this.nCliente = nCliente;
		this.freelance = freelance;
	}

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
	public String getPrecioguardia() {
		return this.precioguardia;
	}

	/**
	 * Asigna valor al campo precioguardia
	 * 
	 * @param precioguardia
	 */
	public void setPrecioguardia(String precioguardia) {
		this.precioguardia = precioguardia;
	}

	/**
	 * Obtiene el valor del campo preciohoraextra
	 * 
	 * @return preciohoraextra
	 */
	public String getPreciohoraextra() {
		return this.preciohoraextra;
	}

	/**
	 * Asigna valor al campo preciohoraextra
	 * 
	 * @param preciohoraextra
	 */
	public void setPreciohoraextra(String preciohoraextra) {
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

	/**
	 * Obtiene el valor del campo nCliente
	 * 
	 * @return nCliente nombre del cliente
	 */
	public String getnCliente() {
		return this.nCliente;
	}
	
	/**
	 * Asigna el valor del campo nCliente
	 *
	 * @param nCliente nombre del cliente
	 */
	public void setnCliente(String nCliente) {
		this.nCliente = nCliente;
	}
}