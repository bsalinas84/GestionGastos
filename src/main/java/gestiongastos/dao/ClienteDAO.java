package gestiongastos.dao;

import java.util.List;

import gestiongastos.model.Cliente;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Cliente
 *
 */
public interface ClienteDAO {

	/**
	 * Definición para la operación Añadir Cliente
	 * 
	 * @param cliente
	 */
	public void addCliente(Cliente cliente);

	/**
	 * Definición para la operación Obtener Listado de Clientes
	 * 
	 * @param orden (asc / desc)
	 * @return Lista de clientes
	 */
	public List<Cliente> getAllClientes(String orden);

	/**
	 * Definición para la operación Borrar Cliente
	 * 
	 * @param clienteId
	 */
	public void deleteCliente(long clienteId);

	/**
	 * Definición para la operación Actualizar Cliente
	 *
	 * 
	 * @param cliente
	 * @return Cliente modificado
	 */
	public Cliente updateCliente(Cliente cliente);

	/**
	 * Definición para la operación Búsqueda de cleinte por id
	 * 
	 * @param clienteId Identificador del cliente
	 * @return Cliente
	 */
	public Cliente getCliente(long clienteId);
	
	/**
	 * Definición para la operación Búsqueda de cliente por nombre
	 * 
	 * @param nombre
	 * @return Cliente
	 */
	public Cliente getClienteByNombre(String nombre);
	
	/**
	 * Definición para la operación que contea los clientes en BBDD
	 * 
	 * @return número de clientes
	 */
	public int getClienteCount();
}
