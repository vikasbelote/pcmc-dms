package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.pcmc.dms.model.AuditModel;
import com.pcmc.dms.model.PropTaxModel;

@Component
public class AuditRepository extends BaseRepository {

	public List<AuditModel> getAuditList(AuditModel auditModel) {

		List<AuditModel> list = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,gut_no,gatta_no,doc_no,vibhag_name ");
		sb.append("from dms_audit ");
		sb.append("where (gut_no like CONCAT(TRIM(IFNULL('" + auditModel.getGutNo() + "', '')), '%') or gut_no is null) ");
		sb.append("and (gatta_no like CONCAT(TRIM(IFNULL('" + auditModel.getGattaNo() + "', '')), '%')  or gatta_no is null) ");
		sb.append("and (doc_no like CONCAT(TRIM(IFNULL('" + auditModel.getDocNo() + "', '')), '%')  or doc_no is null) ");
		sb.append("and (vibhag_name like CONCAT(TRIM(IFNULL('" + auditModel.getVibhagName() + "', '')), '%')  or vibhag_name is null) ");
		sb.append("LIMIT 100");

		list = super.getList(sb.toString(),AuditModel.class);

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
