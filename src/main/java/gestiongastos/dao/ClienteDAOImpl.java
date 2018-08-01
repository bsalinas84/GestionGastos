package gestiongastos.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gestiongastos.model.Cliente;
import gestiongastos.util.ListUtil;


/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa del modelo que implementa las acciones que se 
 * realizan sobre la entidad Cliente
 *
 */
@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	/** 
	 * @see gestiongastos.dao.ClienteDAO#addCliente(gestiongastos.model.Cliente)
	 */
	@Override
	public void addCliente(Cliente cliente) {
		this.sessionFactory.getCurrentSession().save(cliente);

	}

	/**
	 * @see gestiongastos.dao.ClienteDAO#getAllClientes(java.lang.String)
	 **/
	@Override
	public List<Cliente> getAllClientes(String orden) {

		return ListUtil.castList(Cliente.class, this.sessionFactory.getCurrentSession().createQuery("from Cliente order by nombre " + orden)
				.list());
	}

	/**
	 * @see gestiongastos.dao.ClienteDAO#deleteCliente(long)
	 **/
	@Override
	public void deleteCliente(long clienteId) {
		Cliente cliente = (Cliente) this.sessionFactory.getCurrentSession().load(
				Cliente.class, Long.valueOf(clienteId));
		if (null != cliente) {
			this.sessionFactory.getCurrentSession().delete(cliente);
		}

	}

	/**
	 * @see gestiongastos.dao.ClienteDAO#getCliente(long)
	 **/
	@Override
	public Cliente getCliente(long clienteId) {
		return (Cliente) this.sessionFactory.getCurrentSession().get(
				Cliente.class, Long.valueOf(clienteId));
	}

	/**
	 * @see gestiongastos.dao.ClienteDAO#updateCliente(gestiongastos.model.Cliente)
	 **/
	@Override
	public Cliente updateCliente(Cliente cliente) {
		this.sessionFactory.getCurrentSession().update(cliente);
		return cliente;
	}
	
	/**
	 * @see gestiongastos.dao.ClienteDAO#getClienteByNombre(java.lang.String)
	 **/
	@Override
	public Cliente getClienteByNombre(String nombre) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Cliente.class);
		Cliente cliente = (Cliente) criteria.add(Restrictions.eq("nombre", nombre)).uniqueResult();
		
		return cliente;
	}
	
	/**
	 * @see gestiongastos.dao.ClienteDAO#getClienteCount()
	 **/
	@Override
	public int getClienteCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Cliente.class);
		criteriaCount.setProjection(Projections.rowCount());
		
		return Integer.parseInt(criteriaCount.uniqueResult().toString());
	}
}