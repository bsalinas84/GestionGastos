package gestiongastos.service;

import java.util.Date;
import java.util.List;

import gestiongastos.model.Concepto;
import gestiongastos.view.ConceptoView;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizar�n 
 * sobre la entidad Concepto
 *
 */
public interface ConceptoService {
	/**
	 * Definici�n para la operaci�n A�adir Concepto
	 * 
	 * @param vConcepto Objeto de la vista para conceptos
	 * @return c�digo de �xito o c�digo de error
	 */
	public int addConcepto(ConceptoView vConcepto);

	/**
	 * Definici�n para la operaci�n Listado de Conceptoos
	 * @param pageSize 
	 * @param pageNumber 
	 * @param orden 
	 * @param columna 
	 * 
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getAllConceptos(String orden, String columna);

	/**
	 * Definici�n para la operaci�n Borrado de concepto
	 * 
	 * @param id Identificador del concepto
	 * @return c�digo de �xito o c�digo de error
	 */
	public int deleteConcepto(int id);

	/**
	 * Definici�n para la operaci�n B�squeda de un concepto
	 * 
	 * @param id Identificador del concepto
	 * @return Concepto
	 */
	public Concepto getConcepto(int id);

	/**
	 * Definici�n para la operaci�n Modificar concepto
	 * 
	 * @param vConcepto Objeto de la capa de vista para concepto
	 * @return c�digo de �xito o c�digo de error
	 */
	public int updateConcepto(ConceptoView vConcepto);
	
	/**
	 * Definici�n para la operaci�n de B�squeda de concepto entre dos fechas
	 * 
	 * @param fechainicio fecha de inicio de la b�squeda
	 * @param fechafin fecha de fin de la b�squeda
	 * @param pageSize n�mero de conceptos por p�gina (necesario para la paginaci�n)
	 * @param pageNumber n�mero de la p�gina que se va a mostrar (necesario para la paginaci�n)
	 * @param orden asc / desc
	 * @param columna columna por la que se va a ordenar la lista
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definici�n para la operaci�n que contea los conceptos almacenados
	 * 
	 * @return n�mero de conceptos
	 */
	public int getConceptoCount();
	
	/**
	 * Definici�n para la operaci�n de b�squeda de conceptos por tipo
	 * 
	 * @param tipo
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosByTipo(String tipo);
	
	/**
	 * Definici�n para la operaci�n de b�squeda de conceptos por cliente
	 * 
	 * @param selectedCliente lista de ids de clientes por las que filtrar
	 * @param fechainicio fecha de inicio de la b�squeda
	 * @param fechafin fecha de fin de la b�squeda
	 * @param pageSize n�mero de conceptos por p�gina (necesario para la paginaci�n)
	 * @param pageNumber n�mero de la p�gina que se va a mostrar (necesario para la paginaci�n)
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosByCliente(List<String> selectedCliente, Date fechainicio, Date fechafin, int pageSize, int pageNumber);
	
	/**
	 * Definici�n para la operaci�n de b�squeda de conceptos por empleado y fecha
	 * 
	 * @param nombre nombre del empleado para filtrar
	 * @param fechainicio fecha de inicio de la b�squeda
	 * @param fechafin fecha de fin de la b�squeda
	 * @param pageSize n�mero de conceptos por p�gina (necesario para la paginaci�n)
	 * @param pageNumber n�mero de la p�gina que se va a mostrar (necesario para la paginaci�n)
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosBetweenDateByNombre(String nombre, Date fechainicio, Date fechafin, int pageSize, int pageNumber);
	
	/**
	 * Definici�n para la operaci�n de conteo de registros
	 * 
	 * @param fechainicio
	 * @param fechafin
	 * @return n�mero de registros
	 */
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin);
}
