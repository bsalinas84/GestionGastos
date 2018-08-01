package gestiongastos.service;

import java.util.Date;
import java.util.List;

import gestiongastos.model.Concepto;
import gestiongastos.view.ConceptoView;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa de servicio que define las acciones que se realizarán 
 * sobre la entidad Concepto
 *
 */
public interface ConceptoService {
	/**
	 * Definición para la operación Añadir Concepto
	 * 
	 * @param vConcepto Objeto de la vista para conceptos
	 * @return código de éxito o código de error
	 */
	public int addConcepto(ConceptoView vConcepto);

	/**
	 * Definición para la operación Listado de Conceptoos
	 * @param pageSize 
	 * @param pageNumber 
	 * @param orden 
	 * @param columna 
	 * 
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getAllConceptos(String orden, String columna);

	/**
	 * Definición para la operación Borrado de concepto
	 * 
	 * @param id Identificador del concepto
	 * @return código de éxito o código de error
	 */
	public int deleteConcepto(int id);

	/**
	 * Definición para la operación Búsqueda de un concepto
	 * 
	 * @param id Identificador del concepto
	 * @return Concepto
	 */
	public Concepto getConcepto(int id);

	/**
	 * Definición para la operación Modificar concepto
	 * 
	 * @param vConcepto Objeto de la capa de vista para concepto
	 * @return código de éxito o código de error
	 */
	public int updateConcepto(ConceptoView vConcepto);
	
	/**
	 * Definición para la operación de Búsqueda de concepto entre dos fechas
	 * 
	 * @param fechainicio fecha de inicio de la búsqueda
	 * @param fechafin fecha de fin de la búsqueda
	 * @param pageSize número de conceptos por página (necesario para la paginación)
	 * @param pageNumber número de la página que se va a mostrar (necesario para la paginación)
	 * @param orden asc / desc
	 * @param columna columna por la que se va a ordenar la lista
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definición para la operación que contea los conceptos almacenados
	 * 
	 * @return número de conceptos
	 */
	public int getConceptoCount();
	
	/**
	 * Definición para la operación de búsqueda de conceptos por tipo
	 * 
	 * @param tipo
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosByTipo(String tipo);
	
	/**
	 * Definición para la operación de búsqueda de conceptos por cliente
	 * 
	 * @param selectedCliente lista de ids de clientes por las que filtrar
	 * @param fechainicio fecha de inicio de la búsqueda
	 * @param fechafin fecha de fin de la búsqueda
	 * @param pageSize número de conceptos por página (necesario para la paginación)
	 * @param pageNumber número de la página que se va a mostrar (necesario para la paginación)
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosByCliente(List<String> selectedCliente, Date fechainicio, Date fechafin, int pageSize, int pageNumber);
	
	/**
	 * Definición para la operación de búsqueda de conceptos por empleado y fecha
	 * 
	 * @param nombre nombre del empleado para filtrar
	 * @param fechainicio fecha de inicio de la búsqueda
	 * @param fechafin fecha de fin de la búsqueda
	 * @param pageSize número de conceptos por página (necesario para la paginación)
	 * @param pageNumber número de la página que se va a mostrar (necesario para la paginación)
	 * @return lista de conceptos
	 */
	public List<ConceptoView> getConceptosBetweenDateByNombre(String nombre, Date fechainicio, Date fechafin, int pageSize, int pageNumber);
	
	/**
	 * Definición para la operación de conteo de registros
	 * 
	 * @param fechainicio
	 * @param fechafin
	 * @return número de registros
	 */
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin);
}
