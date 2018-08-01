package gestiongastos.dao;

import java.util.List;

import gestiongastos.model.Empleado;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Empleado
 *
 */
public interface EmpleadoDAO {
	/**
	 * Definición para la operación Añadir Empleado
	 * 
	 * @param empleado
	 */
	public void addEmpleado(Empleado empleado);

	/**
	 * Definición para la operación Listado de Todos los empleados
	 * 
	 * @param pageSize Número de registros a mostrar en cada página (necesario para paginación)
	 * @param pageNumber Página que se mostrará (necesario para paginación)
	 * @param orden asc / desc
	 * @param columna Columna por la que se ordenará el listado
	 * @return Lista de Empleados
	 */
	public List<Empleado> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definición para la operación Contar empleados
	 * 
	 * @return Número de Empleados
	 */
	public int getEmpleadosCount();

	/**
	 * Definición para la operación Borrar Empleado
	 * 
	 * @param dni
	 */
	public void deleteEmpleado(String dni);

	/**
	 * Definición para la operación Modificar Empleado
	 * 
	 * @param empleado Objeto empleado para modificar
	 * @return empleado Objejto empleado modificado
	 */
	public Empleado updateEmpleado(Empleado empleado);

	/**
	 * Definición para la operación Búsqueda Empleado por dni
	 * 
	 * @param dni para la búsqueda
	 * @return Empleado
	 */
	public Empleado getEmpleadoByDni(String dni);
	
	/**
	 * Definición para la operación Búsqueda de empleado por nombre
	 * 
	 * @param nombre del empelado a buscar
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByNombre(String nombre);
	
	/**
	 * Definición para la operación Búsqueda de empleados por cliente
	 * @param clienteId Identificador del ciente por el que se filtrará
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByCliente(long clienteId);

}
