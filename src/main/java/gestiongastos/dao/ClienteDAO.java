package gestiongastos.dao;

import java.util.List;

import gestiongastos.model.Cliente;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Cliente
 *
 */
public interface ClienteDAO {

	/**
	 * Definici�n para la operaci�n A�adir Cliente
	 * 
	 * @param cliente
	 */
	public void addCliente(Cliente cliente);

	/**
	 * Definici�n para la operaci�n Obtener Listado de Clientes
	 * 
	 * @param orden (asc / desc)
	 * @return Lista de clientes
	 */
	public List<Cliente> getAllClientes(String orden);

	/**
	 * Definici�n para la operaci�n Borrar Cliente
	 * 
	 * @param clienteId
	 */
	public void deleteCliente(long clienteId);

	/**
	 * Definici�n para la operaci�n Actualizar Cliente
	 *
	 * 
	 * @param cliente
	 * @return Cliente modificado
	 */
	public Cliente updateCliente(Cliente cliente);

	/**
	 * Definici�n para la operaci�n B�squeda de cleinte por id
	 * 
	 * @param clienteId Identificador del cliente
	 * @return Cliente
	 */
	public Cliente getCliente(long clienteId);
	
	/**
	 * Definici�n para la operaci�n B�squeda de cliente por nombre
	 * 
	 * @param nombre
	 * @return Cliente
	 */
	public Cliente getClienteByNombre(String nombre);
	
	/**
	 * Definici�n para la operaci�n que contea los clientes en BBDD
	 * 
	 * @return n�mero de clientes
	 */
	public int getClienteCount();
}
