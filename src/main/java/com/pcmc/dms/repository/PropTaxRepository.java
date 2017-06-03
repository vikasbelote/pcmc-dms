package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.PropTaxModel;

@Repository
public class PropTaxRepository extends BaseRepository {
	
	public List<PropTaxModel> getPropTaxList(PropTaxModel propTaxModel){
		
		List<PropTaxModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,village_name,property_code,property_holder_name,gut_no ");
		sb.append("from dms_property_tax ");
		sb.append("where (village_name like CONCAT(TRIM(IFNULL('" + propTaxModel.getVillageName() + "', '')), '%') or village_name is null) ");
		sb.append("and (property_code like CONCAT(TRIM(IFNULL('" + propTaxModel.getPropertyCode() + "', '')), '%')  or property_code is null) ");
		sb.append("and (gut_no like CONCAT(TRIM(IFNULL('" + propTaxModel.getGutNo() + "', '')), '%') or gut_no is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),PropTaxModel.class);

		return list;
	}
	
	public void savePropTaxList(List<PropTaxModel> listPropTaxModel) {
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		for ( int i = 0; i < listPropTaxModel.size(); i++) {
		    
			PropTaxModel propTaxModel = listPropTaxModel.get(i);
		    session.save(propTaxModel);
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
