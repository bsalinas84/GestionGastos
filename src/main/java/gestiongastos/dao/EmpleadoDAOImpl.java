package gestiongastos.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gestiongastos.model.Empleado;
import gestiongastos.util.ListUtil;

/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa del modelo que implementa las acciones que se 
 * realizan sobre la entidad Empleado
 *
 */
@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @see gestiongastos.dao.EmpleadoDAO#addEmpleado(gestiongastos.model.Empleado)
	 **/
	@Override
	public void addEmpleado(Empleado empleado) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(empleado);
	}

	/**
	 * @see gestiongastos.dao.EmpleadoDAO#getAllEmpleados(int, int, java.lang.String, java.lang.String)
	 **/
	@Override
	public List<Empleado> getAllEmpleados(int pageSize, int pageNumber, String orden, String columna) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Empleado.class);
		criteria.setFirstResult((pageNumber-1)*pageSize);
		criteria.setMaxResults(pageSize);
		if(orden.equals("ASC")) {
			criteria.addOrder(Order.asc(columna));
		} else {
			criteria.addOrder(Order.desc(columna));
		}
		
		return ListUtil.castList(Empleado.class, criteria.list());
	}
	
	/**
	 * @see gestiongastos.dao.EmpleadoDAO#getEmpleadosCount()
	 **/
	@Override
	public int getEmpleadosCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Empleado.class);
		criteriaCount.setProjection(Projections.rowCount());
		
		return Integer.parseInt(criteriaCount.uniqueResult().toString());
	}

	/**
	 * @see gestiongastos.dao.EmpleadoDAO#deleteEmpleado(java.lang.String)
	 **/
	@Override
	public void deleteEmpleado(String dni) {
		Empleado empleado = (Empleado) this.sessionFactory.getCurrentSession().load(
				Empleado.class, dni);
		if (null != empleado) {
			this.sessionFactory.getCurrentSession().delete(empleado);
		}
		
	}

	/**
	 * @see gestiongastos.dao.EmpleadoDAO#updateEmpleado(gestiongastos.model.Empleado)
	 **/
	@Override
	public Empleado updateEmpleado(Empleado empleado) {
		this.sessionFactory.getCurrentSession().update(empleado);
		return empleado;
	}

	/**
	 * @see gestiongastos.dao.EmpleadoDAO#getEmpleadoByDni(java.lang.String)
	 **/
	@Override
	public Empleado getEmpleadoByDni(String dni) {
		return (Empleado) this.sessionFactory.getCurrentSession().get(
				Empleado.class, dni);
	}
	
	/**
	 * @see gestiongastos.dao.EmpleadoDAO#getEmpleadoByNombre(java.lang.String)
	 **/
	@Override
	public List<Empleado> getEmpleadosByNombre(String nombre) {
		Query query= this.sessionFactory.getCurrentSession().createQuery("from Empleado where replace(nombre, ',', '')=:nombre");
		query.setParameter("nombre", nombre);

		return ListUtil.castList(Empleado.class, query.list());
	}
	
	/**
	 * @see gestiongastos.dao.EmpleadoDAO#getEmpleadosByCliente(long)
	 **/
	@Override
	public List<Empleado> getEmpleadosByCliente(long clienteId){
		Query query= this.sessionFactory.getCurrentSession().createQuery("from Empleado where cliente=:cliente");
		query.setParameter("cliente", Long.valueOf(clienteId));
		
		return ListUtil.castList(Empleado.class, query.list());
	}
}
