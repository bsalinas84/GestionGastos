package gestiongastos.service;

import java.util.List;

import gestiongastos.model.Empleado;
import gestiongastos.view.EmpleadoView;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizar�n 
 * sobre la entidad Empleado
 *
 */
public interface EmpleadoService {
	
	/**
	 * Definici�n para la operaci�n A�adir Empleado
	 * 
	 * @param vEmpleado objeto de la vista para empleados
	 * @return c�digo de �xito o c�digo de error
	 */
	public int addEmpleado(EmpleadoView vEmpleado);

	/**
	 * Definici�n para la operaci�n Listado de Empleados
	 * 
	 * @param pageSize N�mero de registros a mostrar por p�gina (necesario para la paginaci�n)
	 * @param pageNumber N�mero de p�gina que se va a mostrar (necesario para la paginaci�n)
	 * @param orden asc / desc
	 * @param columna columna de la tabla Empleados por la que se ordena
	 * @return lista de empleados
	 */
	public List<EmpleadoView> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definici�n para la operaci�n de conteo de empleados
	 * 
	 * @return N�mero de empleados en BBDD
	 */
	public int getEmpleadosCount();

	/**
	 * Definici�n para la operaci�n Borrar Empleado
	 * 
	 * @param dni
	 * @return c�digo de �xito o c�digo de error
	 */
	public int deleteEmpleado(String dni);

	/**
	 * Definici�n para la operaci�n b�squeda de empleado por dni
	 * 
	 * @param dni
	 * @return empleado
	 */
	public Empleado getEmpleado(String dni);

	/**
	 * Definici�n para la operaci�n Modificar Empleado
	 * 
	 * @param empleado empleado a modificar
	 * @param dniAntiguo por si el D.N.I. se modifica para poder buscar los dem�s datos
	 * @return c�digo de �xito o c�digo de error
	 */
	public int updateEmpleado(EmpleadoView empleado, String dniAntiguo);
	
	/**
	 * Definici�n para la operaci�n B�squeda de empleados por nombre
	 * 
	 * @param nombre
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByNombre(String nombre);
}
