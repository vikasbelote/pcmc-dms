package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.model.LoginModel;

@Repository
public class HomeRepository {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	public LoginModel getDepartment(LoginModel loginModel){
		
		LoginModel loginModelDb = null;
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		List<Object[]> departmentList = session.createQuery(
				"from dms_dept_login loginModel join loginModel.department dept " +
				"where (loginModel.isMandPwd = true and (loginModel.userName = '"+loginModel.getUserName()+"' and loginModel.password = '"+loginModel.getPassword()+"'))"
					+ "or (loginModel.isMandPwd = false and loginModel.userName = '" + loginModel.getUserName() + "')")
				.list();
	
		for(Object[] obj : departmentList){
			loginModelDb = (LoginModel)obj[0];	
		}
		
	
		
		tx.commit();
		session.close();
		
		return loginModelDb;
	}
}
