package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.MedicalModel;

@Repository
public class MedicalRepository extends BaseRepository {
	
	public List<MedicalModel> getModelList(MedicalModel model){
		
		List<MedicalModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,head_office,period,ycmh,hospital,dispensaries,table_no,file_no,keyward ");
		sb.append("from dms_medical ");
		sb.append("where (head_office like CONCAT(TRIM(IFNULL('" + model.getHeadOffice() + "', '')), '%') or head_office is null) ");
		sb.append("and (period like CONCAT(TRIM(IFNULL('" + model.getPeriod() + "', '')), '%')  or period is null) ");
		sb.append("and (ycmh like CONCAT(TRIM(IFNULL('" + model.getYcmh() + "', '')), '%') or ycmh is null) ");
		sb.append("and (hospital like CONCAT(TRIM(IFNULL('" + model.getHospital() + "', '')), '%')  or hospital is null) ");
		sb.append("and (dispensaries like CONCAT(TRIM(IFNULL('" + model.getDispensaries() + "', '')), '%') or dispensaries is null) ");
		sb.append("and (table_no like CONCAT(TRIM(IFNULL('" + model.getTableNumber() + "', '')), '%')  or table_no is null) ");
		sb.append("and (file_no like CONCAT(TRIM(IFNULL('" + model.getFileNumber() + "', '')), '%') or file_no is null) ");
		sb.append("and (keyward like CONCAT(TRIM(IFNULL('" + model.getKeyward() + "', '')), '%')  or keyward is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),MedicalModel.class);

		return list;
	}
}
