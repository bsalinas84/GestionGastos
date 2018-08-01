package gestiongastos.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestiongastos.dao.ClienteDAO;
import gestiongastos.dao.ConceptoDAO;
import gestiongastos.dao.EmpleadoDAO;
import gestiongastos.model.Concepto;
import gestiongastos.model.Empleado;
import gestiongastos.view.ConceptoView;

/**
 * @author Bárbara Salinas
 * 
 *         Clase de la capa de servicio que implementa los métodos necesarios
 *         para acceder a la capa de modelo de la entidad Conceptos
 *
 */
@Service
@Transactional
public class ConceptoServieImpl implements ConceptoService {

	@Autowired
	private ConceptoDAO conceptoDAO;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private ClienteDAO clienteDAO;

	private final int EXITO = 0;
	private final int ERROR = -1;
	private final int NO_EMP = -2;
	private final int DUPLICADO = -3;

	/**
	 * @see gestiongastos.service.ConceptoService#addConcepto(gestiongastos.view.ConceptoView)
	 **/
	@Override
	public int addConcepto(ConceptoView vConcepto) {
		Concepto concepto = new Concepto();
		Date sysdate = Calendar.getInstance().getTime();
		int resultado = 0;

		concepto.setId(vConcepto.getId());
		concepto.setDni(vConcepto.getDni());
		concepto.setTipo(vConcepto.getTipo());
		concepto.setCantidad(vConcepto.getCantidad());
		concepto.setObservaciones(vConcepto.getObservaciones());
		concepto.setFecharegistro(sysdate);
		concepto.setImporte(vConcepto.getImporte());
		concepto.setAnotaciones(vConcepto.getAnotaciones());

		try {
			if (concepto.getDni().compareTo("") == 0) {
				List<Empleado> empleados = this.empleadoDAO.getAllEmpleados(this.empleadoDAO.getEmpleadosCount(), 1,
						"asc", "nombre");
				for (Empleado empleado : empleados) {
					if (vConcepto.getNombreEmpleado().compareTo(empleado.getNombre().replace(",", "")) == 0) {
						concepto.setDni(empleado.getDni());
						break;
					}
				}
			} else {
				Empleado empleado = this.empleadoDAO.getEmpleadoByDni(concepto.getDni());

				if (empleado == null) {
					return this.NO_EMP;
				}
			}

			if (concepto.getTipo().compareTo("standby") != 0) {
				List<Concepto> conceptos = this.conceptoDAO.getAllConceptos("asc", "dni");
				for (Concepto con : conceptos) {
					if (con.getTipo().compareTo("gastos") != 0
							&& con.getObservaciones().contains(concepto.getObservaciones())
							&& con.getTipo().equals(concepto.getTipo()) && con.getDni().equals(concepto.getDni())) {
						resultado = this.DUPLICADO;
					}
				}
			}

			if (concepto.getTipo().compareTo("released") == 0) {
				concepto.setImporte(250);
			}

			if (resultado == 0) {
				this.conceptoDAO.addConcepto(concepto);
				return this.EXITO;
			} else {
				return this.DUPLICADO;
			}
		} catch (Exception ex) {
			return this.ERROR;
		}

	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptoCount()
	 **/
	@Override
	public int getConceptoCount() {
		return this.conceptoDAO.getConceptoCount();
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getAllConceptos()
	 **/
	@Override
	public List<ConceptoView> getAllConceptos(String orden, String columna) {
		List<ConceptoView> allConceptos = new ArrayList<ConceptoView>();
		List<Concepto> conceptos = this.conceptoDAO.getAllConceptos(orden, columna);

		for (Concepto concepto : conceptos) {
			Empleado empleado = this.empleadoDAO.getEmpleadoByDni(concepto.getDni());
			ConceptoView vConcepto = getConceptoView(concepto, empleado);

			allConceptos.add(vConcepto);
		}

		return allConceptos;
	}

	/**
	 * @see gestiongastos.service.ConceptoService#deleteConcepto(int)
	 **/
	@Override
	public int deleteConcepto(int id) {
		try {
			this.conceptoDAO.deleteConcepto(id);
			return this.EXITO;
		} catch (Exception ex) {
			return this.ERROR;
		}
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConcepto(int)
	 **/
	@Override
	public Concepto getConcepto(int id) {
		return this.conceptoDAO.getConcepto(id);
	}

	/**
	 * @see gestiongastos.service.ConceptoService#updateConcepto(gestiongastos.view.ConceptoView)
	 **/
	@Override
	public int updateConcepto(ConceptoView vConcepto) {
		Concepto concepto = new Concepto();
		Date sysdate = Calendar.getInstance().getTime();
		int resultado = 0;

		concepto.setId(vConcepto.getId());
		concepto.setDni(vConcepto.getDni());
		concepto.setTipo(vConcepto.getTipo());
		concepto.setCantidad(vConcepto.getCantidad());
		concepto.setFecharegistro(sysdate);
		concepto.setImporte(vConcepto.getImporte());
		concepto.setObservaciones(vConcepto.getObservaciones());
		concepto.setAnotaciones(vConcepto.getAnotaciones());

		try {
			List<Concepto> conceptos = this.conceptoDAO.getAllConceptos("asc", "dni");
			for (Concepto con : conceptos) {
				if (con.getObservaciones().contains(concepto.getObservaciones())
						&& con.getTipo().equals(concepto.getTipo()) && con.getDni().equals(concepto.getDni())) {
					resultado = this.DUPLICADO;
				}
			}

			if (resultado == 0) {
				this.conceptoDAO.updateConcepto(concepto);
				return this.EXITO;
			} else {
				return this.DUPLICADO;
			}
		} catch (Exception ex) {
			return this.ERROR;
		}
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptosBetweenDate(java.util.Date,
	 *      java.util.Date, int, int, java.lang.String, java.lang.String)
	 **/
	@Override
	public List<ConceptoView> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber,
			String orden, String columna) {
		List<ConceptoView> allConceptos = new ArrayList<ConceptoView>();
		List<Concepto> conceptos = new ArrayList<Concepto>();
		int cont = 0;

		if (columna.compareTo("nombre") != 0 && columna.compareTo("cliente") != 0) {
			conceptos = this.conceptoDAO.getConceptosBetweenDate(fechainicio, fechafin, pageSize, pageNumber, orden,
					columna);
		} else {
			if (columna.compareTo("nombre") == 0) {
				List<Empleado> empleados = this.empleadoDAO.getAllEmpleados(this.empleadoDAO.getEmpleadosCount(), 1,
						orden, columna);
				for (Empleado emp : empleados) {
					List<Concepto> conceptosEmpleado = this.conceptoDAO.getConceptosBetweenDateByDni(fechainicio,
							fechafin, emp.getDni(), pageSize, pageNumber);

					for (Concepto concepto : conceptosEmpleado) {
						if(cont < pageSize) {
							conceptos.add(concepto);
							cont++;
						}
					}
				}
			}
		}

		for (Concepto concepto : conceptos) {
			Empleado empleado = this.empleadoDAO.getEmpleadoByDni(concepto.getDni());
			ConceptoView vConcepto = getConceptoView(concepto, empleado);

			allConceptos.add(vConcepto);
		}

		return allConceptos;
	}

	/**
	 * @return número de registros
	 * @see gestiongastos.service.ConceptoService#getConceptosConceptosCount()
	 **/
	public int getConceptosCount() {
		return this.conceptoDAO.getConceptoCount();
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptosByTipo(java.lang.String)
	 **/
	@Override
	public List<ConceptoView> getConceptosByTipo(String tipo) {
		List<ConceptoView> allConceptos = new ArrayList<ConceptoView>();
		List<Concepto> conceptos = this.conceptoDAO.getConceptosByTipo(tipo);

		for (Concepto concepto : conceptos) {
			Empleado empleado = this.empleadoDAO.getEmpleadoByDni(concepto.getDni());
			ConceptoView vConcepto = getConceptoView(concepto, empleado);

			allConceptos.add(vConcepto);
		}

		return allConceptos;
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptosByCliente(java.util.List,
	 *      java.util.Date, java.util.Date, int, int)
	 **/
	@Override
	public List<ConceptoView> getConceptosByCliente(List<String> selectedCliente, Date fechainicio, Date fechafin,
			int pageSize, int pageNumber) {

		List<ConceptoView> conceptos = new ArrayList<ConceptoView>();
		List<Concepto> aux = new ArrayList<Concepto>();

		for (String clienteId : selectedCliente) {
			// tengo que recuperar los empleados del cliente
			List<Empleado> empleados = this.empleadoDAO.getEmpleadosByCliente(Long.parseLong(clienteId));

			// tengo que recuperar los conceptos de cada empleado
			for (Empleado empleado : empleados) {
				aux = this.conceptoDAO.getConceptosBetweenDateByDni(fechainicio, fechafin, empleado.getDni(), pageSize,
						pageNumber);

				for (Concepto concepto : aux) {
					ConceptoView vConcepto = getConceptoView(concepto, empleado);

					conceptos.add(vConcepto);
				}
			}
		}

		return conceptos;
	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptosBetweenDateByNombre(java.lang.String,
	 *      java.util.Date, java.util.Date, int, int)
	 **/
	@Override
	public List<ConceptoView> getConceptosBetweenDateByNombre(String nombre, Date fechainicio, Date fechafin,
			int pageSize, int pageNumber) {
		List<Empleado> empleado = this.empleadoDAO.getEmpleadosByNombre(nombre);
		List<Concepto> conceptos = this.conceptoDAO.getConceptosBetweenDateByDni(fechainicio, fechafin,
				empleado.get(0).getDni(), pageSize, pageNumber);
		List<ConceptoView> conceptosList = new ArrayList<ConceptoView>();

		for (Concepto concepto : conceptos) {
			ConceptoView vConcepto = getConceptoView(concepto, empleado.get(0));

			conceptosList.add(vConcepto);
		}
		return conceptosList;

	}

	/**
	 * @see gestiongastos.service.ConceptoService#getConceptosCountBetweenDate(java.util.Date,
	 *      java.util.Date)
	 **/
	@Override
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin) {
		return this.conceptoDAO.getConceptosCountBetweenDate(fechainicio, fechafin);
	}

	private ConceptoView getConceptoView(Concepto concepto, Empleado empleado) {
		ConceptoView vConcepto = new ConceptoView();

		vConcepto.setId(concepto.getId());
		vConcepto.setDni(concepto.getDni());
		switch (concepto.getTipo()) {
		case "horasextra":
			vConcepto.setTipo("horas extra");
			break;
		case "festivonacional":
			vConcepto.setTipo("festivo nacional");
			break;
		case "festivononacional":
			vConcepto.setTipo("festivo no nacional");
			break;
		default:
			vConcepto.setTipo(concepto.getTipo());
		}
		vConcepto.setTipo(vConcepto.getTipo().replace(vConcepto.getTipo().substring(0, 1),
				vConcepto.getTipo().substring(0, 1).toUpperCase()));
		vConcepto.setObservaciones(concepto.getObservaciones());
		vConcepto.setNombreEmpleado(empleado.getNombre());
		vConcepto.setNombreCliente(this.clienteDAO.getCliente(empleado.getCliente()).getNombre());
		vConcepto.setFechaRegistro(concepto.getFecharegistro());
		vConcepto.setAnotaciones(concepto.getAnotaciones());

		switch (concepto.getTipo()) {
		case "horasextra":
			vConcepto.setImporte(concepto.getCantidad() * empleado.getPreciohoraextra());
			vConcepto.setCantidad(concepto.getCantidad());
			break;
		case "guardia":
			vConcepto.setImporte(concepto.getCantidad() * empleado.getPrecioguardia());
			vConcepto.setCantidad(concepto.getCantidad());
			break;
		default:
			if (concepto.getCantidad() == 0) {
				concepto.setCantidad(1);
			}
			vConcepto.setImporte(concepto.getImporte() * concepto.getCantidad());
		}

		vConcepto.setCantidad(concepto.getCantidad());

		if (concepto.getTipo().compareTo("guardia") == 0) {
			String[] lFechas = concepto.getObservaciones().split("-");
			String semanas = "";
			SimpleDateFormat formater = new SimpleDateFormat("w");
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			for (String fecha : lFechas) {
				if (fecha.compareTo("") != 0) {
					Date date = null;
					try {
						date = format.parse(fecha);
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (!semanas.contains(formater.format(date))) {
						if (semanas.isEmpty()) {
							semanas = "Semana " + formater.format(date);
						} else {
							semanas += ", " + formater.format(date);
						}
					}
				}
			}
			vConcepto.setObservaciones(semanas);
		} else {
			vConcepto.setObservaciones(concepto.getObservaciones());
		}

		return vConcepto;
	}
}
