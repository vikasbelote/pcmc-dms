package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pcmc.dms.model.AuditModel;
import com.pcmc.dms.model.PropTaxModel;

@Component
public abstract class BaseRepository {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

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
	protected <T> List<T> getList(String sql, Class<T> clazz) {

		List<T> list = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		SQLQuery hibQuery = session.createSQLQuery(sql);
		Object obj = hibQuery.list();

		if (obj != null) {
			list = (List<T>) obj;
		}

		tx.commit();
		session.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> T getModel(int rowId, Class<T> clazz) {

		T model = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(clazz);
		cr.add(Restrictions.eq("rowId", new Integer(rowId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			model = (T) obj;
		}

		tx.commit();
		session.close();

		return model;
	}

	public <T> void saveList(List<T> list) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		for (int i = 0; i < list.size(); i++) {
			T model = list.get(i);
			session.save(model);
			if (i % 40 == 0) { // 40, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		session.close();
	}

}
