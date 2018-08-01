package gestiongastos.service;

import java.util.List;

import gestiongastos.model.Empleado;
import gestiongastos.view.EmpleadoView;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizarán 
 * sobre la entidad Empleado
 *
 */
public interface EmpleadoService {
	
	/**
	 * Definición para la operación Añadir Empleado
	 * 
	 * @param vEmpleado objeto de la vista para empleados
	 * @return código de éxito o código de error
	 */
	public int addEmpleado(EmpleadoView vEmpleado);

	/**
	 * Definición para la operación Listado de Empleados
	 * 
	 * @param pageSize Número de registros a mostrar por página (necesario para la paginación)
	 * @param pageNumber Número de página que se va a mostrar (necesario para la paginación)
	 * @param orden asc / desc
	 * @param columna columna de la tabla Empleados por la que se ordena
	 * @return lista de empleados
	 */
	public List<EmpleadoView> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definición para la operación de conteo de empleados
	 * 
	 * @return Número de empleados en BBDD
	 */
	public int getEmpleadosCount();

	/**
	 * Definición para la operación Borrar Empleado
	 * 
	 * @param dni
	 * @return código de éxito o código de error
	 */
	public int deleteEmpleado(String dni);

	/**
	 * Definición para la operación búsqueda de empleado por dni
	 * 
	 * @param dni
	 * @return empleado
	 */
	public Empleado getEmpleado(String dni);

	/**
	 * Definición para la operación Modificar Empleado
	 * 
	 * @param empleado empleado a modificar
	 * @param dniAntiguo por si el D.N.I. se modifica para poder buscar los demás datos
	 * @return código de éxito o código de error
	 */
	public int updateEmpleado(EmpleadoView empleado, String dniAntiguo);
	
	/**
	 * Definición para la operación Búsqueda de empleados por nombre
	 * 
	 * @param nombre
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByNombre(String nombre);
}
