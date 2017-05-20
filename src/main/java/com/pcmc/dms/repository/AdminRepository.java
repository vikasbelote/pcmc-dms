package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.model.LoginModel;

@Repository
public class AdminRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public LoginModel createUser(LoginModel loginModel, Integer deptId) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		DepartmentModel department = (DepartmentModel) session.load(DepartmentModel.class, deptId);
		loginModel.setDepartment(department);

		loginModel = (LoginModel) session.merge(loginModel);

		tx.commit();
		session.close();

		return loginModel;
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentModel> getDepartmentList() {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(DepartmentModel.class);
		List<DepartmentModel> list = cr.list();

		tx.commit();
		session.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<LoginModel> getUserList() {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(LoginModel.class);
		cr.addOrder(Order.desc("loginId"));
		cr.setMaxResults(10);

		List<LoginModel> list = cr.list();

		tx.commit();
		session.close();

		return list;
	}

	public LoginModel getLoginModel(int loginId) {

		LoginModel loginModel = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(LoginModel.class);
		cr.add(Restrictions.eq("loginId", new Integer(loginId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			LoginModel modelDb = (LoginModel) obj;
			loginModel = modelDb;
		}

		tx.commit();
		session.close();

		return loginModel;

	}

	public void deleteUser(int loginId) {

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		LoginModel loginModel = new LoginModel();
		loginModel.setLoginId(loginId);

		session.delete(loginModel);

		tx.commit();
		session.close();

	}
}
