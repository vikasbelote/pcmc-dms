package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.pcmc.dms.model.AuditModel;

@Component
public class AuditRepository extends BaseRepository {

	public List<AuditModel> getAuditList(AuditModel auditModel) {

		List<AuditModel> list = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		StringBuffer sb = new StringBuffer();
		sb.append(
				"select row_id,gut_no,gatta_no,doc_no,vibhag_name ");
		sb.append("from dms_audit ");
		sb.append("where gut_no like CONCAT(TRIM(IFNULL('" + auditModel.getGutNo() + "', '')), '%') ");
		sb.append("and gatta_no like CONCAT(TRIM(IFNULL('" + auditModel.getGattaNo() + "', '')), '%') ");
		sb.append("and doc_no like CONCAT(TRIM(IFNULL('" + auditModel.getDocNo() + "', '')), '%') ");
		sb.append("and vibhag_name like CONCAT(TRIM(IFNULL('" + auditModel.getVibhagName() + "', '')), '%') ");
		sb.append("LIMIT 100");

		SQLQuery hibQuery = session.createSQLQuery(sb.toString());

		Object obj = hibQuery.list();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			List<AuditModel> listDb = (List<AuditModel>) obj;
			list = listDb;
		}

		tx.commit();
		session.close();

		return list;
	}

	public AuditModel getAuditModel(int entryId) {

		AuditModel model = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(AuditModel.class);
		cr.add(Restrictions.eq("rowId", new Integer(entryId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			AuditModel modelDb = (AuditModel) obj;
			model = modelDb;
		}

		tx.commit();
		session.close();

		return model;
	}
}
