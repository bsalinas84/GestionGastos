package gestiongastos.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestiongastos.dao.ClienteDAO;
import gestiongastos.dao.ConceptoDAO;
import gestiongastos.dao.EmpleadoDAO;
import gestiongastos.model.Cliente;
import gestiongastos.model.Concepto;
import gestiongastos.model.Empleado;
import gestiongastos.view.EmpleadoView;

/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa de servicio que implementa los métodos necesarios para
 * acceder a la capa de modelo de la entidad Emleado
 *
 */
@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

	private final int EXITO = 0;
	private final int ERROR = -1;
	private final int DUPLICADO = -2;
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
	
	@Autowired 
	ClienteDAO clienteDAO;
	
	@Autowired
	ConceptoDAO conceptoDAO;
	
	/**
	 * @see gestiongastos.service.EmpleadoService#addEmpleado(gestiongastos.view.EmpleadoView)
	 **/
	@Override
	@Transactional
	public int addEmpleado(EmpleadoView vEmpleado) {
		int resultado = 0;
		Empleado empleado = setBDEmpleado(vEmpleado);
		
		try{
			Empleado busqueda = this.empleadoDAO.getEmpleadoByDni(empleado.getDni());
			if(busqueda != null) {
				return this.DUPLICADO;
			} else {
				this.empleadoDAO.addEmpleado(empleado);
				resultado = this.EXITO;
			}
		} catch(Exception ex) {
			resultado = this.ERROR;
		}
		
		return resultado;
	}
	
	/**
	 * @see gestiongastos.service.EmpleadoService#getEmpleadosCount()
	 **/
	@Override
	@Transactional
	public int getEmpleadosCount() {
		return this.empleadoDAO.getEmpleadosCount();
	}

	/**
	 * @see gestiongastos.service.EmpleadoService#getAllEmpleados(int, int, java.lang.String, java.lang.String)
	 **/
	@Override
	@Transactional
	public List<EmpleadoView> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna) {
		 List<EmpleadoView> allEmpleados = new ArrayList<EmpleadoView>();
		 List<Empleado> bdEmpleados = new ArrayList<Empleado>();
		 
		 if(columna.compareTo("cliente") != 0) {
			 bdEmpleados = this.empleadoDAO.getAllEmpleados(pageSize, pageNumber, orden, columna);
			 
			
		 } else {
			 List<Cliente> clientes = this.clienteDAO.getAllClientes(orden);
			 
			 for(Cliente cli : clientes) {
				bdEmpleados = this.empleadoDAO.getEmpleadosByCliente(cli.getId());
			 }
		 }
		 
		 for(Empleado bdEmpleado : bdEmpleados) {
			 EmpleadoView vEmpleado = setVEmpleado(bdEmpleado);
			
			allEmpleados.add(vEmpleado);
		 }
		 
		 return allEmpleados;
	}

	/**
	 * @see gestiongastos.service.EmpleadoService#deleteEmpleado(java.lang.String)
	 **/
	@Override
	@Transactional
	public int deleteEmpleado(String dni) {
		try{
			this.empleadoDAO.deleteEmpleado(dni);
			return this.EXITO;
		} catch (Exception ex) {
			return this.ERROR;
		}
	}

	/**
	 * @see gestiongastos.service.EmpleadoService#getEmpleado(java.lang.String)
	 **/
	@Override
	public Empleado getEmpleado(String dni) {
		return this.empleadoDAO.getEmpleadoByDni(dni);
	}

	/**
	 * @see gestiongastos.service.EmpleadoService#updateEmpleado(gestiongastos.view.EmpleadoView, java.lang.String)
	 **/
	@Override
	public int updateEmpleado(EmpleadoView vEmpleado, String dniAntiguo) {
		Empleado empleado = setBDEmpleado(vEmpleado);
		int resultado = 0;
		try {
			if (vEmpleado.getDni().equals(dniAntiguo)) {
				this.empleadoDAO.updateEmpleado(empleado);
			} else {
				Empleado busqueda = this.empleadoDAO.getEmpleadoByDni(empleado.getDni());
				if (busqueda != null) {
					resultado = this.DUPLICADO;
				} else {
					List<Concepto> conceptos = this.conceptoDAO.getConceptosByDni(dniAntiguo);

					for (Concepto concepto : conceptos) {
						concepto.setDni(vEmpleado.getDni());
						this.conceptoDAO.updateConcepto(concepto);
					}

					this.empleadoDAO.deleteEmpleado(dniAntiguo);
					this.empleadoDAO.addEmpleado(empleado);
				}
				resultado = this.EXITO;
			}

		} catch (Exception ex) {
			resultado = this.ERROR;
		}

		return resultado;
	}
	
	private Empleado setBDEmpleado (EmpleadoView vEmpleado) {
		Empleado empleado = new Empleado();
		DecimalFormat formateador = new DecimalFormat("####.##");
		Number precioguardia = Integer.valueOf(0);
		Number preciohoraextra = Integer.valueOf(0);
		    
			try {
				precioguardia = formateador.parse(vEmpleado.getPrecioguardia());
				preciohoraextra = formateador.parse(vEmpleado.getPreciohoraextra());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    
		empleado.setDni(vEmpleado.getDni());
		empleado.setNombre(vEmpleado.getNombre());
		empleado.setPrecioguardia(precioguardia.floatValue());
		empleado.setPreciohoraextra(preciohoraextra.floatValue());
		empleado.setCliente(vEmpleado.getCliente());
		empleado.setFreelance(vEmpleado.isFreelance());
		
		return empleado;
	}
	
	private EmpleadoView setVEmpleado(Empleado bdEmpleado) {
		EmpleadoView vEmpleado = new EmpleadoView();
		 
			vEmpleado.setDni(bdEmpleado.getDni());
			vEmpleado.setNombre(bdEmpleado.getNombre().replaceAll(",", ""));
			vEmpleado.setPrecioguardia(String.valueOf(bdEmpleado.getPrecioguardia()));
			vEmpleado.setPreciohoraextra(String.valueOf(bdEmpleado.getPreciohoraextra()));
			vEmpleado.setCliente(bdEmpleado.getCliente());
			vEmpleado.setFreelance(bdEmpleado.isFreelance());
		
			Cliente cliente = this.clienteDAO.getCliente(vEmpleado.getCliente());
			vEmpleado.setnCliente(cliente.getNombre());
			
			return vEmpleado;
	}

	/**
	 * @see gestiongastos.service.EmpleadoService#getEmpleadoByNombre(java.lang.String)
	 **/
	@Override
	public List<Empleado> getEmpleadosByNombre(String nombre) {
		return this.empleadoDAO.getEmpleadosByNombre(nombre);
	}
}
