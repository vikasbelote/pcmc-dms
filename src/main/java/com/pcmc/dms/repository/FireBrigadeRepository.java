package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.FireBrigadeModel;

@Repository
public class FireBrigadeRepository extends BaseRepository {
	
	public List<FireBrigadeModel> getModelList(FireBrigadeModel model){
		
		List<FireBrigadeModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,working_person_name,computer_number ");
		sb.append("from dms_fire_brigade ");
		sb.append("where (working_person_name like CONCAT(TRIM(IFNULL('" + model.getWorkingPersonName() + "', '')), '%') or working_person_name is null) ");
		sb.append("and (computer_number like CONCAT(TRIM(IFNULL('" + model.getComputerNumber() + "', '')), '%')  or computer_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),FireBrigadeModel.class);

		return list;
	}
}
