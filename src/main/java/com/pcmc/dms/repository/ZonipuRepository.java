package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.ZonipuModel;

@Repository
public class ZonipuRepository extends BaseRepository {
	
	public List<ZonipuModel> getModelList(ZonipuModel model){
		
		List<ZonipuModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,nagar_name,person_name,room_no,file_no ");
		sb.append("from dms_zonipu ");
		sb.append("where (nagar_name like CONCAT(TRIM(IFNULL('" + model.getNagarName() + "', '')), '%') or nagar_name is null) ");
		sb.append("and (person_name like CONCAT(TRIM(IFNULL('" + model.getPersonName() + "', '')), '%')  or person_name is null) ");
		sb.append("and (room_no like CONCAT(TRIM(IFNULL('" + model.getRoomNumber() + "', '')), '%')  or room_no is null) ");
		sb.append("and (file_no like CONCAT(TRIM(IFNULL('" + model.getFileNumber() + "', '')), '%')  or file_no is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),ZonipuModel.class);

		return list;
	}
}
