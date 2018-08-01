package gestiongastos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gestiongastos.model.Cliente;
import gestiongastos.model.Empleado;
import gestiongastos.service.ClienteService;
import gestiongastos.service.EmpleadoService;
import gestiongastos.view.EmpleadoView;

/**
* @author Bárbara Salinas
 * 
 * Clase de la capa de control para el módulo Empleados
 */

@Controller
public class EmpleadoController {
	/**
	 * Constructor
	 */
	public EmpleadoController() {
		System.out.println("EmpleadoController()");
	}

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ClienteService clienteService;
	
	private final int defaultPageSize = 5;
	private final int defaultPageNumber = 1;
	private final String defaultorder = "ASC";
	private final String defaultcolumn = "nombre";
	
	/**
	 * Vista de "empleados"
	 * 
	 * @param model modelAndView
	 * @param request 
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public ModelAndView listEmpleado(ModelAndView model, HttpServletRequest request) {
		int pageSize = this.defaultPageSize;
		int pageNumber = this.defaultPageNumber;
		String orden = this.defaultorder;
		String column = this.defaultcolumn;		
		
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
			column = request.getParameter("columna");
		}
		
		List<EmpleadoView> listEmpleado = this.empleadoService.getAllEmpleados(pageSize, pageNumber, orden, column);
		List<Cliente> listClientes = this.clienteService.getAllClientes("asc");
		Empleado empleado = new Empleado();
		
		model.addObject("orden", orden);
		model.addObject("columna", column);
		model.addObject("pages", Integer.valueOf(this.empleadoService.getEmpleadosCount()/pageSize));
		model.addObject("pageSize", Integer.valueOf(pageSize));
		model.addObject("pageActive", Integer.valueOf(pageNumber));
		model.addObject("counterempleados", Integer.valueOf(this.empleadoService.getEmpleadosCount()));
		model.addObject("counterclientes", Integer.valueOf(listClientes.size()));
		model.addObject("empleado", empleado);
		model.addObject("listEmpleado", listEmpleado);
		model.addObject("listClientes", listClientes);
		model.setViewName("empleados");
		
		return model;
	}
	
	/**
	 * Vista de "saveEmpleado"
	 *
	 * @param vEmpleado objeto empleado 
	 * @param model modelAndView
	 * @param request 
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/saveEmpleado", method = RequestMethod.POST)
	public ModelAndView saveEmpleado(@ModelAttribute EmpleadoView vEmpleado, ModelAndView model, HttpServletRequest request) {
		vEmpleado.setNombre(request.getParameter("nombre") + ", " + request.getParameter("apellidos"));
		
		int resultado = this.empleadoService.addEmpleado(vEmpleado);
		int pageSize = this.defaultPageSize;
		int pageNumber = this.defaultPageNumber;
		
		if(request.getParameterMap().containsKey("pageNumber")){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		if(request.getParameterMap().containsKey("pageSize")){
			pageNumber = Integer.parseInt(request.getParameter("pageSize"));
		}
	
		
		model = new ModelAndView("redirect:/empleados");
		model.addObject("resultado", Integer.valueOf(resultado));
		model.addObject("pageSize", Integer.valueOf(pageSize));
		model.addObject("pageNumber", Integer.valueOf(pageNumber));
		
		return model;
	}

	/**
	 * Vista de "deleteEmpleado"
	 * 
	 * @param request 
	 * @param model modelAndView
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/deleteEmpleado", method = RequestMethod.POST)
	public ModelAndView deleteEmpleado(HttpServletRequest request, ModelAndView model) {
		String dni = request.getParameter("dni");
		int resultado = this.empleadoService.deleteEmpleado(dni);
		int pageSize = this.defaultPageSize;
		int pageNumber = this.defaultPageNumber;
		
		if(request.getParameterMap().containsKey("pageNumber")){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		if(request.getParameterMap().containsKey("pageSize")){
			pageNumber = Integer.parseInt(request.getParameter("pageSize"));
		}
	
		
		model = new ModelAndView("redirect:/empleados");
		model.addObject("resultado", Integer.valueOf(resultado));
		model.addObject("pageSize", Integer.valueOf(pageSize));
		model.addObject("pageNumber", Integer.valueOf(pageNumber));
		
		return model;
	}

	/**
	 * Vista de "editEmpleado"
	 * 
	 * @param empleado objeto empleado
	 * @param model modelAndView
	 * @param dniAntiguo dni anterior por si se ha modificado
	 * @param request 
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/editEmpleado", method = RequestMethod.POST)
	public ModelAndView editContact(@ModelAttribute EmpleadoView empleado, ModelAndView model, @RequestParam("dniantiguo") String dniAntiguo, HttpServletRequest request) {
		int resultado = this.empleadoService.updateEmpleado(empleado, dniAntiguo);
		int pageSize = this.defaultPageSize;
		int pageNumber = this.defaultPageNumber;
		
		if(request.getParameterMap().containsKey("pageNumber")){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		if(request.getParameterMap().containsKey("pageSize")){
			pageNumber = Integer.parseInt(request.getParameter("pageSize"));
		}
	
		
		model = new ModelAndView("redirect:/empleados");
		model.addObject("resultado", Integer.valueOf(resultado));
		model.addObject("pageSize", Integer.valueOf(pageSize));
		model.addObject("pageNumber", Integer.valueOf(pageNumber));
		
		return model;
	}
}
