package com.pcmc.dms.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.DepartmentModel;

@Repository
public class MasterRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public <T> List<T> getMasterList(Class<T> clazz) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(clazz);
		List<T> list = cr.list();

		tx.commit();
		session.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getMasterList(Class<T> clazz, String subDeptName) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("subDeptName", subDeptName));
		
		List<T> list = cr.list();

		tx.commit();
		session.close();

		return list;
	}

	public Object saveMasterValue(Object master) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Object obj = session.merge(master);

		tx.commit();
		session.close();

		return obj;
	}

	@SuppressWarnings("unchecked")
	public <T> T getMasterValue(Integer rowId, Class<T> clazz) {

		T master = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("rowId", new Integer(rowId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			master = (T) obj;
		}

		tx.commit();
		session.close();

		return master;
	}
	

	@SuppressWarnings("unchecked")
	public <T> T getSubMasterValue(Integer subDeptId, Class<T> clazz) {

		T master = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("subDeptId", subDeptId));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			master = (T) obj;
		}

		tx.commit();
		session.close();

		return master;
	}

	public <T> void deleteMasterValue(Integer rowId, Class<T> clazz) {
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("rowId", new Integer(rowId)));
		
		Object obj = cr.uniqueResult();
		if (obj != null) {
			session.delete(obj);
		}
		
		tx.commit();
		session.close();
		
	}
	
	public <T> void deleteSubMasterValue(Integer subDeptId, Class<T> clazz) {
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("subDeptId", subDeptId));
		
		Object obj = cr.uniqueResult();
		if (obj != null) {
			session.delete(obj);
		}
		
		tx.commit();
		session.close();
		
	}
	
	public Object saveMasterDeptValue(Object master, Integer deptId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		DepartmentModel department = (DepartmentModel) session.load(DepartmentModel.class, deptId);
		Method setDepartment = master.getClass().getMethod("setDepartment", DepartmentModel.class);
		setDepartment.invoke(master, department);
		
		Object obj = session.merge(master);

		tx.commit();
		session.close();

		return obj;
	}

}
