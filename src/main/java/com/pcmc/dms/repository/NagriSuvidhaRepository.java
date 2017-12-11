package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.NagriSuvidhaModel;

@Repository
public class NagriSuvidhaRepository extends BaseRepository {
	
	public List<NagriSuvidhaModel> getModelList(NagriSuvidhaModel model) {
		
		List<NagriSuvidhaModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, nasti_number ");
		sb.append("from dms_nagri_suvidha ");
		sb.append("where (nasti_number like CONCAT(TRIM(IFNULL('" + model.getNastiNumber() + "', '')), '%') or nasti_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), NagriSuvidhaModel.class);	
		return list;
	}
}
