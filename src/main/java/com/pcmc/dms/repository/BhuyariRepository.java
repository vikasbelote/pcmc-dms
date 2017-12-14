package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.BhuyariModel;

@Repository
public class BhuyariRepository extends BaseRepository {
	
	public List<BhuyariModel> getModelList(BhuyariModel model){
		
		List<BhuyariModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,nivada_number,work_name,tendor_name,budget_tartude_number ");
		sb.append("from dms_bhuyari ");
		sb.append("where (nivada_number like CONCAT(TRIM(IFNULL('" + model.getNivadaNumber() + "', '')), '%') or nivada_number is null) ");
		sb.append("and (work_name like CONCAT(TRIM(IFNULL('" + model.getWorkName() + "', '')), '%')  or work_name is null) ");
		sb.append("and (budget_tartude_number like CONCAT(TRIM(IFNULL('" + model.getBudgetTartudeNumber() + "', '')), '%')  or budget_tartude_number is null) ");
		sb.append("and (tendor_name like CONCAT(TRIM(IFNULL('" + model.getTendorName() + "', '')), '%')  or tendor_name is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),BhuyariModel.class);

		return list;
	}
}
