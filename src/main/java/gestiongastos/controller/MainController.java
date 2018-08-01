package gestiongastos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa de control principal de la aplicación
 *
 */
@Controller
public class MainController {
	/**
	 * Constructor
	 */
	public MainController() {
		System.out.println("MainController()");
	}

	/**
	 * Vista de "main_menu"
	 * 
	 * @param model modelAndView
	 * @return model modelAndView
	 */
	@RequestMapping(value = "/")
	public ModelAndView viewHome(ModelAndView model) {
		model.addObject("error", "");
		model.setViewName("main_menu");
		
		return model;
	}
}
