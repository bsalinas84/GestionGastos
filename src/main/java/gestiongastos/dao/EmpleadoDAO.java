package gestiongastos.dao;

import java.util.List;

import gestiongastos.model.Empleado;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Empleado
 *
 */
public interface EmpleadoDAO {
	/**
	 * Definici�n para la operaci�n A�adir Empleado
	 * 
	 * @param empleado
	 */
	public void addEmpleado(Empleado empleado);

	/**
	 * Definici�n para la operaci�n Listado de Todos los empleados
	 * 
	 * @param pageSize N�mero de registros a mostrar en cada p�gina (necesario para paginaci�n)
	 * @param pageNumber P�gina que se mostrar� (necesario para paginaci�n)
	 * @param orden asc / desc
	 * @param columna Columna por la que se ordenar� el listado
	 * @return Lista de Empleados
	 */
	public List<Empleado> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definici�n para la operaci�n Contar empleados
	 * 
	 * @return N�mero de Empleados
	 */
	public int getEmpleadosCount();

	/**
	 * Definici�n para la operaci�n Borrar Empleado
	 * 
	 * @param dni
	 */
	public void deleteEmpleado(String dni);

	/**
	 * Definici�n para la operaci�n Modificar Empleado
	 * 
	 * @param empleado Objeto empleado para modificar
	 * @return empleado Objejto empleado modificado
	 */
	public Empleado updateEmpleado(Empleado empleado);

	/**
	 * Definici�n para la operaci�n B�squeda Empleado por dni
	 * 
	 * @param dni para la b�squeda
	 * @return Empleado
	 */
	public Empleado getEmpleadoByDni(String dni);
	
	/**
	 * Definici�n para la operaci�n B�squeda de empleado por nombre
	 * 
	 * @param nombre del empelado a buscar
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByNombre(String nombre);
	
	/**
	 * Definici�n para la operaci�n B�squeda de empleados por cliente
	 * @param clienteId Identificador del ciente por el que se filtrar�
	 * @return Lista de empleados
	 */
	public List<Empleado> getEmpleadosByCliente(long clienteId);

}
