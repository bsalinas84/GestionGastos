package gestiongastos.service;

import java.util.List;

import gestiongastos.model.Cliente;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizarán 
 * sobre la entidad Cliente
 *
 */
public interface ClienteService {
	
	/**
	 * Definición para la operación Añadir Cliente
	 * 
	 * @param cliente
	 * @return código de éxito o código de error
	 */
	public int addCliente(Cliente cliente);

	/**
	 * Definición para la operación Listado de todos los clientes
	 * 
	 * @param orden asc / desc
	 * @return Listado de todos los clientes
	 */
	public List<Cliente> getAllClientes(String orden);

	/**
	 * Definición para la operación Borrar Cliente
	 * 
	 * @param clienteId
	 * @return código de éxito o código de error
	 */
	public int deleteCliente(long clienteId);

	/**
	 * Definición para la operación Buscar cliente por id
	 * 
	 * @param clienteid identificardor del cliente
	 * @return cliente
	 */
	public Cliente getCliente(long clienteid);

	/**
	 * Definición para la operación Modificar cliente
	 * 
	 * @param cliente
	 * @return código de éxito o código de error
	 */
	public int updateCliente(Cliente cliente);
	
	/**
	 * Definición de la operación que contea el número de clientes en BBDD
	 * 
	 * @return número de clientes registrados
	 */
	public int getClienteCount();
	
	/**
	 * Definición de la operación de Búsqueda de clientes por nombre
	 * 
	 * @param nombre
	 * @return cliente 
	 */
	public Cliente getClienteByNombre(String nombre);
}
