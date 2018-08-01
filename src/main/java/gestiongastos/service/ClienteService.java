package gestiongastos.service;

import java.util.List;

import gestiongastos.model.Cliente;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizar�n 
 * sobre la entidad Cliente
 *
 */
public interface ClienteService {
	
	/**
	 * Definici�n para la operaci�n A�adir Cliente
	 * 
	 * @param cliente
	 * @return c�digo de �xito o c�digo de error
	 */
	public int addCliente(Cliente cliente);

	/**
	 * Definici�n para la operaci�n Listado de todos los clientes
	 * 
	 * @param orden asc / desc
	 * @return Listado de todos los clientes
	 */
	public List<Cliente> getAllClientes(String orden);

	/**
	 * Definici�n para la operaci�n Borrar Cliente
	 * 
	 * @param clienteId
	 * @return c�digo de �xito o c�digo de error
	 */
	public int deleteCliente(long clienteId);

	/**
	 * Definici�n para la operaci�n Buscar cliente por id
	 * 
	 * @param clienteid identificardor del cliente
	 * @return cliente
	 */
	public Cliente getCliente(long clienteid);

	/**
	 * Definici�n para la operaci�n Modificar cliente
	 * 
	 * @param cliente
	 * @return c�digo de �xito o c�digo de error
	 */
	public int updateCliente(Cliente cliente);
	
	/**
	 * Definici�n de la operaci�n que contea el n�mero de clientes en BBDD
	 * 
	 * @return n�mero de clientes registrados
	 */
	public int getClienteCount();
	
	/**
	 * Definici�n de la operaci�n de B�squeda de clientes por nombre
	 * 
	 * @param nombre
	 * @return cliente 
	 */
	public Cliente getClienteByNombre(String nombre);
}
