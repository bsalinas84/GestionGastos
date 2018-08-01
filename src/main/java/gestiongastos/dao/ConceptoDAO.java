package gestiongastos.dao;

import java.util.Date;
import java.util.List;

import gestiongastos.model.Concepto;

/**
 * @author Bárbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Concepto
 *
 */
public interface ConceptoDAO {
	/**
	 * Definición para la operación Añadir Concepto
	 * 
	 * @param concepto
	 */
	public void addConcepto(Concepto concepto);

	/**
	 * Definición para la operación Listado de Conceptos
	 * @param pageSize 
	 * @param pageNumber 
	 * @param orden 
	 * @param columna 
	 * @return Lista de Conceptos
	 */
	public List<Concepto> getAllConceptos(String orden, String columna);

	/**
	 * Definición para la operación Borrar un Concepto
	 * 
	 * @param id Identificador del concepto
	 */
	public void deleteConcepto(int id);

	/**
	 * Definición para la operación Modificar un Concepto
	 * 
	 * @param concepto a modificar
	 * @return Concepto modificado
	 */
	public Concepto updateConcepto(Concepto concepto);

	/**
	 * Definición para la operación Búsqueda de Concepto por identificador
	 * 
	 * @param id Identificador del concepto
	 * @return concepto
	 */
	public Concepto getConcepto(int id);
	
	/**
	 * Definición para la operación Listado de Conceptos entre fechas
	 * 
	 * @param fechainicio fecha de inicio del listado
	 * @param fechafin fecha de fin del listado
	 * @param pageSize número de registros por página (necesario para paginar)
	 * @param pageNumber número de la página que se muestra (necesario para paginar)
	 * @param orden asc / desc
	 * @param columna columna por la que se ordena
	 * @return Lista de Conceptos
	 */
	public List<Concepto> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definición para la operación conteo de registros
	 * 
	 * @return número de registros existentes
	 */
	public int getConceptoCount();
	
	/**
	 * Definición para la operación Lista de conceptos filtrados por dni de empleado
	 * 
	 * @param dni DNI del empleado por el que se filtra el listado
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosByDni(String dni);
	
	/**
	 * Definición para la operación Listado de conceptos filtrado por tipo de concepto
	 * 
	 * @param tipo Tipo de gasto por el que se filtra el listado
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosByTipo(String tipo);
	
	/**
	 * Definición para la operación Listado de conceptos filtrados por dni entre dos fechas
	 * 
	 * @param fechainicio Fecha en la que se inicia el listado
	 * @param fechafin Fecha en la que se finaliza el listado
	 * @param dni DNI del empleado por el que se filtra
	 * @param pageSize Número de registros que se devuelven por página (necesario para la paginación)
	 * @param pageNumber Página de la paginación que se solicita (necesario para la paginación)
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosBetweenDateByDni(Date fechainicio, Date fechafin, String dni, int pageSize, int pageNumber);
	
	/**
	 * Definición para la operación de conteo de registros entre dos fechas
	 * 
	 * @param fechainicio
	 * @param fechafin
	 * @return número de registros
	 */
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin);
}
