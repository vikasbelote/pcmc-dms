package com.pcmc.dms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.BuildingPermissionModel;

@Repository
public class BuildingPermissionRepository extends BaseRepository {
	
	public List<BuildingPermissionModel> getBuildingPermissionList(BuildingPermissionModel model){
		
		List<BuildingPermissionModel> list = null;
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,village_name,serve_no,architect_name,owner_name,building_permission_no,gatta_no,file_name,year ");
		sb.append("from dms_building_permission ");
		sb.append("where village_name like CONCAT(TRIM(IFNULL('" + model.getVillageName() + "', '')), '%') ");
		sb.append("and serve_no like CONCAT(TRIM(IFNULL('" + model.getServeNo()  + "', '')), '%') ");
		sb.append("and architect_name like CONCAT(TRIM(IFNULL('" + model.getArchitectName()  + "', '')), '%') ");
		sb.append("and owner_name like CONCAT(TRIM(IFNULL('" + model.getOwnerName()  + "', '')), '%') ");
		sb.append("and building_permission_no like CONCAT(TRIM(IFNULL('" + model.getBuildingPermissionNo()  + "', '')), '%') ");
		sb.append("and gatta_no like CONCAT(TRIM(IFNULL('" + model.getGattaNo()  + "', '')), '%') ");
		sb.append("and file_name like CONCAT(TRIM(IFNULL('" + model.getFileName()  + "', '')), '%') ");
		sb.append("and year like CONCAT(TRIM(IFNULL('" + model.getYear()  + "', '')), '%') ");
		
		SQLQuery hibQuery = session.createSQLQuery(sb.toString());
		
		Object obj = hibQuery.list();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			List<BuildingPermissionModel> listDb = (List<BuildingPermissionModel>) obj;
			list = listDb;
		}
		
		tx.commit();
		session.close();
		return list;
	}
	
	public BuildingPermissionModel getBuildingPermissionModel(int rowId){
		
		BuildingPermissionModel model = null;

		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(BuildingPermissionModel.class);
		cr.add(Restrictions.eq("rowId", new Integer(rowId)));

		Object obj = cr.uniqueResult();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			BuildingPermissionModel bupModel = (BuildingPermissionModel) obj;
			model = bupModel;
		}

		tx.commit();
		session.close();

		return model;
		
	}
	
}
