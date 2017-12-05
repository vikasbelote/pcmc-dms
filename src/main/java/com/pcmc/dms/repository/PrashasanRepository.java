package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.PrashasanModel;

@Repository
public class PrashasanRepository extends BaseRepository {
	
	public List<PrashasanModel> getModelList(PrashasanModel model){
		
		List<PrashasanModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, table_number, nasti_name, nasti_number ");
		sb.append("from dms_prashasan ");
		sb.append("where (table_number like CONCAT(TRIM(IFNULL('" + model.getTableNumber() + "', '')), '%') or table_number is null) ");
		sb.append("and (nasti_name like CONCAT(TRIM(IFNULL('" + model.getNastiName() + "', '')), '%')  or nasti_name is null) ");
		sb.append("and (nasti_number like CONCAT(TRIM(IFNULL('" + model.getNastiNumber() + "', '')), '%') or nasti_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), PrashasanModel.class);
		
		return list;
	}
}
