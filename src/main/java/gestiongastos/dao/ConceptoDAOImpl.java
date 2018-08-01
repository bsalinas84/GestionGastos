package gestiongastos.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gestiongastos.model.Concepto;
import gestiongastos.util.ListUtil;

/**
 * @author Bárbara Salinas
 * 
 * Clase de la capa del modelo que implementa las acciones que se 
 * realizan sobre la entidad Concepto
 *
 */
@Repository
public class ConceptoDAOImpl implements ConceptoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see gestiongastos.dao.ConceptoDAO#addConcepto(gestiongastos.model.Concepto)
	 **/
	@Override
	public void addConcepto(Concepto concepto) {
		this.sessionFactory.getCurrentSession().save(concepto);
	}

	/**
	 * @see gestiongastos.dao.ConceptoDAO#getAllConceptos()
	 **/
	@Override
	public List<Concepto> getAllConceptos(String orden, String columna) {
		//return ListUtil.castList(Concepto.class, this.sessionFactory.getCurrentSession().createQuery("from Concepto").list());
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Concepto.class);
		if(orden.equals("ASC")) {
			cr.addOrder(Order.asc(columna));
		} else {
			cr.addOrder(Order.desc(columna));
		}
		
		return ListUtil.castList(Concepto.class, cr.list());
	}

	/**
	 * @see gestiongastos.dao.ConceptoDAO#deleteConcepto(int)
	 **/
	@Override
	public void deleteConcepto(int conceptoId) {
		Concepto concepto = (Concepto) this.sessionFactory.getCurrentSession().load(Concepto.class, Integer.valueOf(conceptoId));
		if (null != concepto) {
			this.sessionFactory.getCurrentSession().delete(concepto);
		}
	}

	/**
	 * @see gestiongastos.dao.ConceptoDAO#updateConcepto(gestiongastos.model.Concepto)
	 **/
	@Override
	public Concepto updateConcepto(Concepto concepto) {
		this.sessionFactory.getCurrentSession().merge(concepto);
		return concepto;
	}

	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConcepto(int)
	 **/
	@Override
	public Concepto getConcepto(int conceptoId) {
		return (Concepto) this.sessionFactory.getCurrentSession().get(Concepto.class, Integer.valueOf(conceptoId));
	}
	
	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptosBetweenDate(java.util.Date, java.util.Date, int, int, java.lang.String, java.lang.String)
	 **/
	@Override
	public List<Concepto> getConceptosBetweenDate(Date fechainicio, Date fechafin, int pageSize, int pageNumber, String orden, String columna){
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Concepto.class);
		cr.add(Restrictions.between("fecharegistro", fechainicio, fechafin));
		cr.setFirstResult((pageNumber-1)*pageSize);
		cr.setMaxResults(pageSize);
		if(orden.equals("ASC")) {
			cr.addOrder(Order.asc(columna));
		} else {
			cr.addOrder(Order.desc(columna));
		}
		
		return ListUtil.castList(Concepto.class, cr.list());
	}
	
	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptoCount()
	 **/
	@Override
	public int getConceptoCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Concepto.class);
		criteriaCount.setProjection(Projections.rowCount());

		return Integer.parseInt(criteriaCount.uniqueResult().toString());
	}
	
	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptosByDni(java.lang.String)
	 **/
	@Override
	public List<Concepto> getConceptosByDni(String dni){
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Concepto.class);
		cr.add(Restrictions.eq("dni", dni));
		
		return ListUtil.castList(Concepto.class, cr.list());
	}
	
	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptosByTipo(java.lang.String)
	 **/
	@Override
	public List<Concepto> getConceptosByTipo(String tipo){
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Concepto.class);
		cr.add(Restrictions.eq("tipo", tipo));
		
		return ListUtil.castList(Concepto.class, cr.list());
	}

	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptosBetweenDateByDni(java.util.Date, java.util.Date, java.lang.String, int, int)
	 **/
	@Override
	public List<Concepto> getConceptosBetweenDateByDni(Date fechainicio, Date fechafin, String dni, int pageSize,
			int pageNumber) {
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Concepto.class);
		cr.add(Restrictions.between("fecharegistro", fechainicio, fechafin));
		cr.add(Restrictions.eq("dni", dni));
		cr.addOrder(Order.asc("fecharegistro"));
		cr.setFirstResult((pageNumber-1)*pageSize);
		cr.setMaxResults(pageSize);
		
		return ListUtil.castList(Concepto.class, cr.list());
	}
	
	/**
	 * @see gestiongastos.dao.ConceptoDAO#getConceptosCountBetweenDate(java.util.Date, java.util.Date)
	 **/
	@Override
	public int getConceptosCountBetweenDate(Date fechainicio, Date fechafin) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Concepto.class);
		criteriaCount.add(Restrictions.between("fecharegistro", fechainicio, fechafin));
		criteriaCount.setProjection(Projections.rowCount());

		return Integer.parseInt(criteriaCount.uniqueResult().toString());
	}
}
