package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.VidhutVibhagModel;

@Repository
public class VidhutVibhagRepository extends BaseRepository {
	
	public List<VidhutVibhagModel> getModelList(VidhutVibhagModel model){
		
		List<VidhutVibhagModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, nivida_number ");
		sb.append("from dms_vidhut_vibhag ");
		sb.append("where (nivida_number like CONCAT(TRIM(IFNULL('" + model.getNividhaNumber() + "', '')), '%') or nivida_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), VidhutVibhagModel.class);
		
		return list;
	}
}
