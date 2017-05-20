package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.LawModel;

@Repository
public class LawRepository extends BaseRepository{

	public List<LawModel> getLawModelList(LawModel lawModel) {

		List<LawModel> list = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,case_no,court_name,gatta_no,file_name,year ");
		sb.append("from dms_law ");
		sb.append("where (case_no like CONCAT(TRIM(IFNULL('" + lawModel.getCaseNo() + "', '')), '%') or case_no is null) ");
		sb.append("and (court_name like CONCAT(TRIM(IFNULL('" + lawModel.getCourtName() + "', '')), '%')  or court_name is null) ");
		sb.append("and (gatta_no like CONCAT(TRIM(IFNULL('" + lawModel.getGattaNo() + "', '')), '%') or gatta_no is null) ");
		sb.append("and (file_name like CONCAT(TRIM(IFNULL('" + lawModel.getFileName() + "', '')), '%') or file_name is null) ");
		sb.append("and (year like CONCAT(TRIM(IFNULL('" + lawModel.getYear() + "', '')), '%') or year is null) ");
		sb.append("LIMIT 100");

		SQLQuery hibQuery = session.createSQLQuery(sb.toString());

		Object obj = hibQuery.list();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			List<LawModel> listDb = (List<LawModel>) obj;
			list = listDb;
		}
		tx.commit();
		session.close();

		return list;
	}

	public LawModel getLawModel(int entryId) {

		LawModel model = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(LawModel.class);
		cr.add(Restrictions.eq("rowId", new Integer(entryId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			LawModel modelDb = (LawModel) obj;
			model = modelDb;
		}

		tx.commit();
		session.close();

		return model;

	}
	
	public void saveLawList(List<LawModel> lawModelList) {
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		for (int i = 0; i < lawModelList.size(); i++) {
		    
			LawModel lawModel = lawModelList.get(i);
		    session.save(lawModel);
		    if ( i % 40 == 0 ) { //40, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
		    }
		}

		tx.commit();
		session.close();
	}

}
