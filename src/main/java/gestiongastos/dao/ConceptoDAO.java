package gestiongastos.dao;

import java.util.Date;
import java.util.List;

import gestiongastos.model.Concepto;

/**
 * @author B�rbara Salinas
 * 
 * Interfaz de la capa del modelo que define las acciones que se 
 * realizan sobre la entidad Concepto
 *
 */
public interface ConceptoDAO {
	/**
	 * Definici�n para la operaci�n A�adir Concepto
	 * 
	 * @param concepto
	 */
	public void addConcepto(Concepto concepto);

	/**
	 * Definici�n para la operaci�n Listado de Conceptos
	 * @param pageSize 
	 * @param pageNumber 
	 * @param orden 
	 * @param columna 
	 * @return Lista de Conceptos
	 */
	public List<Concepto> getAllConceptos(String orden, String columna);

	/**
	 * Definici�n para la operaci�n Borrar un Concepto
	 * 
	 * @param id Identificador del concepto
	 */
	public void deleteConcepto(int id);

	/**
	 * Definici�n para la operaci�n Modificar un Concepto
	 * 
	 * @param concepto a modificar
	 * @return Concepto modificado
	 */
	public Concepto updateConcepto(Concepto concepto);

	/**
	 * Definici�n para la operaci�n B�squeda de Concepto por identificador
	 * 
	 * @param id Identificador del concepto
	 * @return concepto
	 */
	public Concepto getConcepto(int id);
	
	/**
	 * Definici�n para la operaci�n Listado de Conceptos entre fechas
	 * 
	 * @param fechainicio fecha de inicio del listado
	 * @param fechafin fecha de fin del listado
	 * @param pageSize n�mero de registros por p�gina (necesario para paginar)
	 * @param pageNumber n�mero de la p�gina que se muestra (necesario para paginar)
	 * @param orden asc / desc
	 * @param columna columna por la que se ordena
	 * @return Lista de Conceptos
	 */
	public List<Concepto> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber, String orden, String columna);
	
	/**
	 * Definici�n para la operaci�n conteo de registros
	 * 
	 * @return n�mero de registros existentes
	 */
	public int getConceptoCount();
	
	/**
	 * Definici�n para la operaci�n Lista de conceptos filtrados por dni de empleado
	 * 
	 * @param dni DNI del empleado por el que se filtra el listado
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosByDni(String dni);
	
	/**
	 * Definici�n para la operaci�n Listado de conceptos filtrado por tipo de concepto
	 * 
	 * @param tipo Tipo de gasto por el que se filtra el listado
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosByTipo(String tipo);
	
	/**
	 * Definici�n para la operaci�n Listado de conceptos filtrados por dni entre dos fechas
	 * 
	 * @param fechainicio Fecha en la que se inicia el listado
	 * @param fechafin Fecha en la que se finaliza el listado
	 * @param dni DNI del empleado por el que se filtra
	 * @param pageSize N�mero de registros que se devuelven por p�gina (necesario para la paginaci�n)
	 * @param pageNumber P�gina de la paginaci�n que se solicita (necesario para la paginaci�n)
	 * @return Lista de conceptos
	 */
	public List<Concepto> getConceptosBetweenDateByDni(Date fechainicio, Date fechafin, String dni, int pageSize, int pageNumber);
	
	/**
	 * Definici�n para la operaci�n de conteo de registros entre dos fechas
	 * 
	 * @param fechainicio
	 * @param fechafin
	 * @return n�mero de registros
	 */
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin);
}
