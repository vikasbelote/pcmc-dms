package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.NagarSachivModel;

@Repository
public class NagarSachivRepository extends BaseRepository {
	
	public List<NagarSachivModel> getModelList(NagarSachivModel model){
		
		List<NagarSachivModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,body_name,karypatrika_no,karypatrika_date ");
		sb.append("from dms_nagar_sachiv ");
		sb.append("where (body_name like CONCAT(TRIM(IFNULL('" + model.getBodyName() + "', '')), '%') or body_name is null) ");
		sb.append("and (karypatrika_no like CONCAT(TRIM(IFNULL('" + model.getKarypatrikaNumber() + "', '')), '%')  or karypatrika_no is null) ");
		sb.append("and (karypatrika_date like CONCAT(TRIM(IFNULL('" + model.getKarypatrikaDate() + "', '')), '%')  or karypatrika_date is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),NagarSachivModel.class);

		return list;
	}
}
