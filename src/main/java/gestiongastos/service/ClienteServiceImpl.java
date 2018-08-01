package gestiongastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestiongastos.dao.ClienteDAO;
import gestiongastos.dao.EmpleadoDAO;
import gestiongastos.model.Cliente;
import gestiongastos.model.Empleado;

/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa de servicio que implementa los métodos necesarios para
 * acceder a la capa de modelo de la entidad Cliente
 *
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	private final int EXITO = 0;
	private final int ERROR = -1;
	private final int EMP = -2;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private EmpleadoDAO empleadoDAO;

	/**
	 * @see gestiongastos.service.ClienteService#addCliente(gestiongastos.model.Cliente)
	 **/
	@Override
	@Transactional
	public int addCliente(Cliente cliente) {
		cliente.setId(Long.parseLong("0"));
		int resultado = 0;
		Cliente busqueda = this.clienteDAO.getClienteByNombre(cliente.getNombre());
			
			if(busqueda == null) {
				try{
					this.clienteDAO.addCliente(cliente);
					resultado = this.EXITO;
				} catch (Exception ex) {
					resultado = this.ERROR;
				}
							
			} else {
				resultado = this.ERROR;
			}
		
		return resultado;
	}

	/**
	 * @see gestiongastos.service.ClienteService#getAllClientes(java.lang.String)
	 **/
	@Override
	@Transactional
	public List<Cliente> getAllClientes(String orden) {
		return this.clienteDAO.getAllClientes(orden);
	}

	/**
	 * @see gestiongastos.service.ClienteService#deleteCliente(long)
	 **/
	@Override
	@Transactional
	public int deleteCliente(long clienteId) {
		try {
			List<Empleado> empleados = this.empleadoDAO.getEmpleadosByCliente(clienteId);
			
			if(empleados.size() > 0) {
				return this.EMP;
			} else {
				this.clienteDAO.deleteCliente(clienteId);
				return this.EXITO;
			}
		} catch (Exception ex) {
			return this.ERROR;
		}
		
		
	}

	/**
	 * @see gestiongastos.service.ClienteService#getCliente(long)
	 **/
	@Override
	public Cliente getCliente(long empid) {
		return this.clienteDAO.getCliente(empid);
	}

	/**
	 * @see gestiongastos.service.ClienteService#updateCliente(gestiongastos.model.Cliente)
	 **/
	@Override
	public int updateCliente(Cliente cliente) {
		Cliente busqueda = this.clienteDAO.getClienteByNombre(cliente.getNombre());
		int resultado = 0;
		
		if(busqueda == null) {
			try{
				this.clienteDAO.updateCliente(cliente);
				resultado = this.EXITO;
			} catch (Exception ex) {
				resultado = this.ERROR;
			}			
		} else {
			resultado = this.ERROR;
		}
	
		return resultado;
	}
	
	/**
	 * @see gestiongastos.service.ClienteService#getClienteCount()
	 **/
	@Override
	public int getClienteCount() {
		return this.clienteDAO.getClienteCount();
	}
	
	/**
	 * @see gestiongastos.service.ClienteService#getClienteByNombre(java.lang.String)
	 **/
	@Override
	public Cliente getClienteByNombre(String nombre) {
		return this.clienteDAO.getClienteByNombre(nombre);
	}
}
