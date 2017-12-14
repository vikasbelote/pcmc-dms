package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.KridaVibhagModel;

@Repository
public class KridaVibhagRepository extends BaseRepository {
	
	public List<KridaVibhagModel> getKridaVibhagModelList(KridaVibhagModel kridaVibahgModel) {
		
		List<KridaVibhagModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, nasti_number, nasti_name ");
		sb.append("from dms_krida ");
		sb.append("where (nasti_number like CONCAT(TRIM(IFNULL('" + kridaVibahgModel.getNastiNumber() + "', '')), '%') or nasti_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), KridaVibhagModel.class);	
		return list;
	}
}
