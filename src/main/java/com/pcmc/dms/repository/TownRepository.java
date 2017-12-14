package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.TownModel;

@Repository
public class TownRepository extends BaseRepository {
	
public List<TownModel> getModelList(TownModel model){
		
		List<TownModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,village_name,sheet_number ");
		sb.append("from dms_town ");
		sb.append("where (village_name like CONCAT(TRIM(IFNULL('" + model.getVillageName() + "', '')), '%') or village_name is null) ");
		sb.append("and (sheet_number like CONCAT(TRIM(IFNULL('" + model.getSheetNumber() + "', '')), '%')  or sheet_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),TownModel.class);

		return list;
	}
}
