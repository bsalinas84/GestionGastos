package gestiongastos.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import gestiongastos.model.Cliente;
import gestiongastos.model.Concepto;
import gestiongastos.service.ClienteService;
import gestiongastos.service.ConceptoService;
import gestiongastos.service.EmpleadoService;
import gestiongastos.view.ConceptoView;
import gestiongastos.view.EmpleadoView;

/**
 * @author Bárbara Salinas
 * 
 *         Clase de la capa de control para el módulo Gastos
 */

@Controller
public class ConceptoController {
	/**
	 * Constructor
	 */
	public ConceptoController() {
		System.out.println("ConceptoController()");
	}

	@Autowired
	private ConceptoService conceptoService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ClienteService clienteService;

	private int defaultPageSize = 10;
	private int defaultPageNumber = 1;
	private String defaultorder = "ASC";
	private String defaultcolumn = "nombre";

	/**
	 * Vista de "gastos"
	 * 
	 * @param model
	 *            modelAndView
	 * @param request
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ModelAndView listConcepto(ModelAndView model, HttpServletRequest request) {
		Calendar fechainicio = Calendar.getInstance();
		Calendar fechafin = Calendar.getInstance();
		List<ConceptoView> listConcepto = new ArrayList<ConceptoView>();
		String mes = getParamValue("mes", "", request);
		String tipo = "";
		int pageSize = this.defaultPageSize;
		int pageNumber = this.defaultPageNumber;
		String orden = this.defaultorder;
		String columna = this.defaultcolumn;		
		
		if(request.getParameterMap().containsKey("pageNumber")){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		if(request.getParameterMap().containsKey("pageSize")){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
		if(request.getParameterMap().containsKey("resultado")){
			model.addObject("resultado", request.getParameter("resultado"));
		}
		
		if(request.getParameterMap().containsKey("orden")){
			orden = request.getParameter("orden");
		}
		
		if(request.getParameterMap().containsKey("columna")){
			columna = request.getParameter("columna");
		}

		if (request.getParameterMap().containsKey("tipo")) {
			tipo = request.getParameter("tipo");
		

			switch (tipo) {
			case "todo":
				columna = "dni";
				listConcepto = this.conceptoService.getAllConceptos(orden, columna);
				break;

			case "mes":
				fechainicio.set(Calendar.DATE, 10);
				fechafin.set(Calendar.DATE, 10);
				fechafin.add(Calendar.MONTH, 1);
				listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
						this.conceptoService.getConceptoCount(), 1, orden, columna);
				break;

			case "trimestre":
				fechainicio.add(Calendar.MONTH, -3);
				listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
						this.conceptoService.getConceptoCount(), 1, orden, columna);
				break;

			case "anual":
				fechainicio.set(Calendar.MONTH, 1);
				fechainicio.set(Calendar.DAY_OF_MONTH, 10);
				listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
						this.conceptoService.getConceptoCount(), 1, orden, columna);
				break;
			}

			model.addObject("mes", "");
		} else {
			if (mes.equals("")) {
				if (Calendar.getInstance().get(Calendar.DATE) <= 10) {
					fechainicio.set(Calendar.DATE, 10);
					fechainicio.add(Calendar.MONTH, -1);
					fechafin.set(Calendar.DATE, 10);
				} else {
					fechainicio.set(Calendar.DATE, 10);
					fechafin.set(Calendar.DATE, 10);
					fechafin.add(Calendar.MONTH, 1);
				}

				model.addObject("mes", Calendar.getInstance().getTime());

			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				try {
					if (request.getParameterMap().containsKey("direccion")) {
						if (request.getParameter("direccion").equals("atras")) {
							fechainicio.setTime(sdf.parse(mes));
							fechainicio.set(Calendar.DATE, 10);
							fechainicio.add(Calendar.MONTH, -1);
							fechafin.setTime(sdf.parse(mes));
							fechafin.set(Calendar.DATE, 10);
						}

						if (request.getParameter("direccion").equals("adelante")) {
							fechainicio.setTime(sdf.parse(mes));
							fechainicio.set(Calendar.DATE, 10);
							fechainicio.add(Calendar.MONTH, 1);
							fechafin.setTime(sdf.parse(mes));
							fechafin.set(Calendar.DATE, 10);
							fechafin.add(Calendar.MONTH, 2);
						}
					} else {
						fechainicio.setTime(sdf.parse(mes));
						fechainicio.set(Calendar.DATE, 10);
						fechafin.setTime(sdf.parse(mes));
						fechafin.set(Calendar.DATE, 10);
						fechafin.add(Calendar.MONTH, 1);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
					pageSize, pageNumber, orden, columna);

			model.addObject("direccion", "");
			model.addObject("mes", fechainicio.getTime());
		}

		Concepto concepto = new Concepto();
		model.addObject("orden", orden);
		model.addObject("columna", columna);
		double pages = (double) this.conceptoService.getConceptosCountBetweenDate(fechainicio.getTime(),
				fechafin.getTime()) / pageSize;
		if (pages - (int) pages > 0) {
			pages++;
		}

		model.addObject("pages", Integer.valueOf((int) pages));
		model.addObject("pageSize", Integer.valueOf(pageSize));
		model.addObject("pageActive", Integer.valueOf(pageNumber));
		model.addObject("counterempleados", Integer.valueOf(this.empleadoService.getEmpleadosCount()));
		model.addObject("counterclientes", Integer.valueOf(this.clienteService.getAllClientes("asc").size()));		
		if(tipo.compareTo("todo") == 0) {
			model.addObject("counterconceptos", this.conceptoService.getConceptoCount());
		} else {
			model.addObject("counterconceptos", Integer
				.valueOf(this.conceptoService.getConceptosCountBetweenDate(fechainicio.getTime(), fechafin.getTime()))); 
		}
		model.addObject("concepto", concepto);
		model.addObject("listConcepto", listConcepto);
		model.addObject("nombrefichero", "");
		model.setViewName("gastos");
		
		return model;
	}

	/**
	 * Vista de "saveconcepto"
	 * 
	 * @param vConcepto
	 *            objeto concepto
	 * @param model
	 *            modelAndView
	 * @param nombreEmpleado
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/saveConcepto", method = RequestMethod.POST)
	public ModelAndView saveConcepto(@ModelAttribute ConceptoView vConcepto, ModelAndView model,
			@RequestParam("nombreEmpleado") String nombreEmpleado) {
		vConcepto.setNombreEmpleado(nombreEmpleado);
		int resultado = this.conceptoService.addConcepto(vConcepto);

		model = new ModelAndView("redirect:/gastos");
		model.addObject("resultado", Integer.valueOf(resultado));

		return model;
	}

	/**
	 * Vista de "deleteConcepto"
	 * 
	 * @param model
	 *            modelAndView
	 * @param request
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/deleteConcepto", method = RequestMethod.POST)
	public ModelAndView deleteConcepto(HttpServletRequest request, ModelAndView model) {
		int id = Integer.parseInt(request.getParameter("id"));
		int resultado = this.conceptoService.deleteConcepto(id);

		model = new ModelAndView("redirect:/gastos");
		model.addObject("resultado", Integer.valueOf(resultado));

		return model;
	}

	/**
	 * Vista de "editConcepto"
	 * 
	 * @param vConcepto
	 *            objeto concepto
	 * @param model
	 *            modelAndView
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/editConcepto", method = RequestMethod.POST)
	public ModelAndView editConcepto(@ModelAttribute ConceptoView vConcepto, ModelAndView model) {
		int resultado = this.conceptoService.updateConcepto(vConcepto);

		model = new ModelAndView("redirect:/gastos");
		model.addObject("resultado", Integer.valueOf(resultado));

		return model;
	}

	/**
	 * Vista de "exportExcel"
	 * 
	 * @param nombrefichero
	 *            nombre del fichero que se va a generar
	 * @param tipo
	 *            tipo de filtrado que se realiza
	 * @param cliente
	 *            clientes por los que se filtra
	 * @param mes 
	 * @param model
	 *            modelAndView
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public ModelAndView createExcel(@RequestParam String nombrefichero, @RequestParam String tipo,
			@RequestParam String cliente, @RequestParam String mes, HttpServletResponse response) {
		String fileName = "C:/tmp/" + nombrefichero + ".xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Gastos");
		List<ConceptoView> listConcepto = new ArrayList<ConceptoView>();
		Calendar fechafin = Calendar.getInstance();
		Calendar fechainicio = Calendar.getInstance();
		String orden = this.defaultorder;
		String columna = this.defaultcolumn;

		if (mes.compareTo("") != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			try {
				fechainicio.setTime(sdf.parse(mes));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Calendar.getInstance().get(Calendar.DATE) <= 10) {
			fechainicio.set(Calendar.DATE, 10);
			fechainicio.add(Calendar.MONTH, -1);
			fechafin.set(Calendar.DATE, 10);
		} else {
			fechainicio.set(Calendar.DATE, 10);
			fechafin.set(Calendar.DATE, 10);
			fechafin.add(Calendar.MONTH, 1);
		}

		if (tipo.compareTo("") == 0) {
			if (cliente.compareTo("") == 0) {
				tipo = "mes";
			} else {
				tipo = "cliente";
			}
		}

		switch (tipo) {
		case "todo":
			listConcepto = this.conceptoService.getAllConceptos(orden, columna);
			break;

		case "mes":
			listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
					this.conceptoService.getConceptoCount(), 1, orden, columna);
			break;

		case "trimestre":
			fechainicio.add(Calendar.MONTH, -3);
			listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
					this.conceptoService.getConceptoCount(), 1, orden, columna);
			break;

		case "anual":
			fechainicio.set(Calendar.MONTH, 1);
			fechainicio.set(Calendar.DAY_OF_MONTH, 1);
			listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
					this.conceptoService.getConceptoCount(), 1, orden, columna);
			break;

		case "guardia":
			listConcepto = this.conceptoService.getConceptosByTipo(tipo);
			break;

		case "hora extra":
			listConcepto = this.conceptoService.getConceptosByTipo(tipo);
			break;

		case "gastos":
			listConcepto = this.conceptoService.getConceptosByTipo(tipo);
			break;

		case "cliente":
			List<String> selectedCliente = new ArrayList<String>();
			List<String> nombreSelectedCliente = Arrays.asList(cliente.split(" - "));
			for (String nombre : nombreSelectedCliente) {
				selectedCliente.add(Long.toString(this.clienteService.getClienteByNombre(nombre).getId()));
			}
			listConcepto = this.conceptoService.getConceptosByCliente(selectedCliente, fechainicio.getTime(),
					fechafin.getTime(), this.defaultPageSize, this.defaultPageNumber);
			break;

		default:
			listConcepto = this.conceptoService.getConceptosBetweenDate(fechainicio.getTime(), fechafin.getTime(),
					this.conceptoService.getConceptoCount(), 1, orden, columna);
			break;

		}

		Font redFont = workbook.createFont();
		redFont.setColor(HSSFColor.RED.index);
		CellStyle redStyle = workbook.createCellStyle();
		redStyle.setFont(redFont);

		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		CellStyle boldStyle = workbook.createCellStyle();
		boldStyle.setFont(boldFont);

		int rowNum = 0;
		System.out.println("Creating excel");

		// Cabecera del Excel
		Row hRow = sheet.createRow(rowNum++);
		int colNum = 0;

		Cell hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Nombre Empleado");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("DNI");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Cliente");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Cantidad");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Concepto");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("F. Pago");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Importe");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Fecha");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Anotaciones");
		hCell.setCellStyle(boldStyle);

		hCell = hRow.createCell(colNum++);
		hCell.setCellValue("Fecha Registro");
		hCell.setCellStyle(boldStyle);

		for (ConceptoView concepto : listConcepto) {
			Row row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getNombreEmpleado().toUpperCase());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getDni().toUpperCase());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getNombreCliente().toUpperCase());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getCantidad());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getTipo().toUpperCase());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0) {
				cell.setCellStyle(redStyle);
				cell.setCellValue("DIETAS");
			} else {
				cell.setCellValue("PLUS PRODUCTIVIDAD");
			}

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0)
				cell.setCellStyle(redStyle);
			cell.setCellValue(concepto.getImporte());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0) {
				cell.setCellStyle(redStyle);
			}
			cell.setCellValue(concepto.getObservaciones());

			cell = row.createCell(colNum++);
			if (concepto.getTipo().compareTo("Gastos") == 0) {
				cell.setCellStyle(redStyle);
			}
			cell.setCellValue(concepto.getAnotaciones());

			cell = row.createCell(colNum++);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if (concepto.getTipo().compareTo("Gastos") != 0) {
				cell.setCellValue(format.format(concepto.getFechaRegistro()));
			} else {
				cell.setCellStyle(redStyle);
				cell.setCellValue(format.format(concepto.getFechaRegistro()));
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/gastos");
	}
	
	/**
	 * Vista de "gastosportipo"
	 * 
	 * @param tipo
	 * @param model
	 *            modelAndView
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/gastosportipo", method = RequestMethod.POST)
	public ModelAndView gastosportipo(@RequestParam String tipo, ModelAndView model) {
		List<ConceptoView> listConcepto = this.conceptoService.getConceptosByTipo(tipo);

		Concepto concepto = new Concepto();
		model.addObject("concepto", concepto);
		model.addObject("listConcepto", listConcepto);
		model.addObject("mes", "");
		model.addObject("direccion", "");
		model.addObject("tipo", tipo);
		model.setViewName("gastos");

		return model;
	}

	/**
	 * Vista para el buscador de empleados
	 * 
	 * @param nombre
	 * @return lista de empleados
	 */
	@RequestMapping(value = "/getEmpleados", method = RequestMethod.GET)
	public @ResponseBody String getTags(@RequestParam("term") String nombre) {
		List<EmpleadoView> empleados = this.empleadoService.getAllEmpleados(this.empleadoService.getEmpleadosCount(), 1,
				this.defaultorder, this.defaultcolumn);
		List<String> result = new ArrayList<String>();

		// iterate a list and filter by tagName
		for (EmpleadoView emp : empleados) {
			if (emp.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
				result.add(emp.getNombre());
			}
		}

		Gson gson = new Gson();
		return gson.toJson(result);

	}

	/**
	 * Vista para el filtro por cliente
	 * 
	 * @param model
	 *            ModelAndView
	 * @return model ModelAndView
	 */
	@RequestMapping(value = "/selectcliente", method = RequestMethod.GET)
	public ModelAndView selectCliente(ModelAndView model) {
		List<Cliente> listCliente = this.clienteService.getAllClientes(this.defaultorder);

		model.addObject("counter", Integer.valueOf(this.clienteService.getClienteCount()));
		model.addObject("listCliente", listCliente);
		model.addObject("counter", Integer.valueOf(listCliente.size()));
		model.setViewName("select_cliente");

		return model;
	}

	/**
	 * Vista para los informes filtrados por cliente
	 * 
	 * @param model
	 *            ModelAndView
	 * @param request
	 *            HttpServeltRequest
	 * @return model ModelAndView
	 */
	@RequestMapping(value = "/gastosporcliente", method = RequestMethod.POST)
	public ModelAndView gastosPorCliente(ModelAndView model, HttpServletRequest request) {
		List<String> selectedCliente = new ArrayList<String>();
		List<String> list = Collections.list(request.getParameterNames());
		Calendar fechainicio = Calendar.getInstance();
		Calendar fechafin = Calendar.getInstance();
		String cliente = "";

		for (int i = 0; i < list.size(); i++) {
			selectedCliente.add(list.get(i));
		}

		if (Calendar.getInstance().get(Calendar.DATE) <= 10) {
			fechainicio.set(Calendar.DATE, 10);
			fechainicio.add(Calendar.MONTH, -1);
			fechafin.set(Calendar.DATE, 10);
		} else {
			fechainicio.set(Calendar.DATE, 10);
			fechafin.set(Calendar.DATE, 10);
			fechafin.add(Calendar.MONTH, 1);
		}

		List<ConceptoView> listConcepto = this.conceptoService.getConceptosByCliente(selectedCliente,
				fechainicio.getTime(), fechafin.getTime(), this.defaultPageSize, this.defaultPageNumber);

		for (int i = 0; i < selectedCliente.size(); i++) {
			if (cliente.compareTo("") == 0) {
				cliente += this.clienteService.getCliente(Long.parseLong(selectedCliente.get(i))).getNombre();
			} else {
				cliente += " - " + this.clienteService.getCliente(Long.parseLong(selectedCliente.get(i))).getNombre();
			}
		}

		model.addObject("cliente", cliente);
		model.addObject("mes", "");
		model.addObject("counterconceptos", Integer.valueOf(listConcepto.size()));
		model.addObject("pages", Integer.valueOf(listConcepto.size() / this.defaultPageSize));
		model.addObject("listConcepto", listConcepto);
		model.addObject("concepto", new Concepto());
		model.setViewName("gastos");

		return model;
	}

	/**
	 * Vista para la búsqueda de conceptos por empleado
	 * 
	 * @param model
	 *            ModelAndView
	 * @param nombre
	 *            nombre del empleado para filtrar
	 * @param request
	 *            HTTPServletRequest
	 * @return model ModelAndView
	 */
	@RequestMapping(value = "/gastosporempleado", method = RequestMethod.POST)
	public ModelAndView gastosporempleados(ModelAndView model, @RequestParam("nombreEmpleado") String nombre,
			HttpServletRequest request) {

		List<ConceptoView> listConcepto = this.conceptoService.getConceptosBetweenDateByNombre(nombre,
				getFecha("inicio"), getFecha("fin"),
				Integer.valueOf(getParamValue("pageSize", String.valueOf(this.defaultPageSize), request)),
				Integer.valueOf(getParamValue("pageNumber", String.valueOf(this.defaultPageNumber), request)));

		model.addObject("cliente", "");
		model.addObject("mes", "");
		model.addObject("counterconceptos", Integer.valueOf(listConcepto.size()));
		model.addObject("pages", Integer.valueOf(listConcepto.size() / this.defaultPageSize));
		model.addObject("listConcepto", listConcepto);
		model.addObject("concepto", new Concepto());
		model.setViewName("gastos");

		return model;
	}

	private String getParamValue(String name, String defaultValue, HttpServletRequest request) {

		if (request.getParameterMap().containsKey(name)) {
			return request.getParameter(name);
		} else {
			return defaultValue;
		}
	}

	private Date getFecha(String tipo) {

		Calendar fechainicio = Calendar.getInstance();
		Calendar fechafin = Calendar.getInstance();

		if (Calendar.getInstance().get(Calendar.DATE) <= 10) {
			fechainicio.set(Calendar.DATE, 10);
			fechainicio.add(Calendar.MONTH, -1);
			fechafin.set(Calendar.DATE, 10);
		} else {
			fechainicio.set(Calendar.DATE, 10);
			fechafin.set(Calendar.DATE, 10);
			fechafin.add(Calendar.MONTH, 1);
		}

		if (tipo.compareTo("inicio") == 0) {
			return fechainicio.getTime();
		} else {
			return fechafin.getTime();
		}

	}
}
