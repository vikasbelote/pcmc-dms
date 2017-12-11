package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.ItiModel;

@Repository
public class ItiRepository extends BaseRepository {


	public List<ItiModel> getModelList(ItiModel model){
			
			List<ItiModel> list = null;
			
			StringBuffer sb = new StringBuffer();
			sb.append("select row_id,iti_name,sub_iti_name,nasti_name,nasti_number ");
			sb.append("from dms_iti ");
			sb.append("where (iti_name like CONCAT(TRIM(IFNULL('" + model.getItiName() + "', '')), '%') or iti_name is null) ");
			sb.append("and (sub_iti_name like CONCAT(TRIM(IFNULL('" + model.getSubItiName() + "', '')), '%')  or sub_iti_name is null) ");
			sb.append("and (nasti_name like CONCAT(TRIM(IFNULL('" + model.getNastiName() + "', '')), '%')  or nasti_name is null) ");
			sb.append("and (nasti_number like CONCAT(TRIM(IFNULL('" + model.getNastiNumber() + "', '')), '%')  or nasti_number is null) ");
			sb.append("LIMIT 100");
			
			list = super.getList(sb.toString(),ItiModel.class);
			return list;
	}
}
