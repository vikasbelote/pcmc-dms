package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.VivahNodhaniModel;

@Repository
public class VivahNodhniRepository extends BaseRepository {
	
	public List<VivahNodhaniModel> getVivahNodhaniModelList(VivahNodhaniModel vivahNodhaniModel) {
		
		List<VivahNodhaniModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, prabhag_name, entity_name, entity_registration_no, entity_registration_date, gatta_no ");
		sb.append("from dms_vivah_nodhani ");
		sb.append("where (prabhag_name like CONCAT(TRIM(IFNULL('" + vivahNodhaniModel.getPrabhagName() + "', '')), '%') or prabhag_name is null) ");
		sb.append("and (entity_name like CONCAT(TRIM(IFNULL('" + vivahNodhaniModel.getEntityName() + "', '')), '%')  or entity_name is null) ");
		sb.append("and (entity_registration_no like CONCAT(TRIM(IFNULL('" + vivahNodhaniModel.getEntityRegistrationNo() + "', '')), '%') or entity_registration_no is null) ");
		sb.append("and (entity_registration_date like CONCAT(TRIM(IFNULL('" + vivahNodhaniModel.getEntityRegistrationDate() + "', '')), '%') or entity_registration_date is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), VivahNodhaniModel.class);
		
		return list;
	}
}
