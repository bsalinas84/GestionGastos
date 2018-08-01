package gestiongastos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gestiongastos.model.Cliente;
import gestiongastos.service.ClienteService;

/**
* @author Bárbara Salinas
 * 
 * Clase de la capa de control para el módulo Clientes
 */

@Controller
public class ClienteController {
	
	/**
	 * Constructor
	 */
	public ClienteController() {
		System.out.println("ClienteController()");
	}

	@Autowired
	private ClienteService clienteService;
	
	private String defaultOrder = "ASC";
	
	/**
	 * Vista de "clientes"
	 * 
	 * @param model modelAndView
	 * @param request
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ModelAndView listCliente(ModelAndView model, HttpServletRequest request) {
		String orden = this.defaultOrder;
		
		if(request.getParameterMap().containsKey("orden")) {
			orden = request.getParameter("orden");
		} 
		List<Cliente> listCliente = this.clienteService.getAllClientes(orden);
		Cliente cliente = new Cliente();
		
		if(request.getParameterMap().containsKey("resultado")){
			model.addObject("resultado", request.getParameter("resultado"));
		}		
		
		model.addObject("orden", orden);
		model.addObject("cliente", cliente);
		model.addObject("listCliente", listCliente);
		model.addObject("counter",Integer.valueOf( listCliente.size()));
		model.setViewName("clientes");
		
		return model;
	}
	
	/**
	 * Vista de "saveCliente"
	 * 
	 * @param model modelAndView
	 * @param cliente
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/saveCliente", method = RequestMethod.POST)
	public ModelAndView saveCliente(@ModelAttribute Cliente cliente, ModelAndView model) {
		int resultado = 0;
		
		if (cliente.getId() == 0) { 
			resultado = this.clienteService.addCliente(cliente);
		} else {
			resultado = this.clienteService.updateCliente(cliente);
		}
		
		model = new ModelAndView("redirect:/clientes");
		model.addObject("resultado", Integer.valueOf(resultado));
		
		return model;
	}

	/**
	 * Vista de "deleteCliente"
	 * 
	 * @param model modelAndView
	 * @param request
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/deleteCliente", method = RequestMethod.POST)
	public ModelAndView deleteCliente(HttpServletRequest request, ModelAndView model) {
		Long clienteId = Long.valueOf(request.getParameter("id"));
		int resultado = this.clienteService.deleteCliente(Long.parseLong(clienteId.toString()));
		
		model = new ModelAndView("redirect:/clientes");
		model.addObject("resultado", Integer.valueOf(resultado));
		
		return model;
	}
}