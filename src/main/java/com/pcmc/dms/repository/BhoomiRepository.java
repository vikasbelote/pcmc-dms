package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.BhoomiModel;

@Repository
public class BhoomiRepository extends BaseRepository {
	
	public List<BhoomiModel> getModelList(BhoomiModel model){
		
		List<BhoomiModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,village_name,subject_name,serve_no,gat_no,hissa_no,file_no ");
		sb.append("from dms_bhoomi ");
		sb.append("where (village_name like CONCAT(TRIM(IFNULL('" + model.getVillageName() + "', '')), '%') or village_name is null) ");
		sb.append("and (subject_name like CONCAT(TRIM(IFNULL('" + model.getSubjectName() + "', '')), '%')  or subject_name is null) ");
		sb.append("and (serve_no like CONCAT(TRIM(IFNULL('" + model.getServeNumber() + "', '')), '%')  or serve_no is null) ");
		sb.append("and (gat_no like CONCAT(TRIM(IFNULL('" + model.getGatNumber() + "', '')), '%')  or gat_no is null) ");
		sb.append("and (hissa_no like CONCAT(TRIM(IFNULL('" + model.getHissaNumber() + "', '')), '%')  or hissa_no is null) ");
		sb.append("and (file_no like CONCAT(TRIM(IFNULL('" + model.getFileNumber() + "', '')), '%')  or file_no is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),BhoomiModel.class);

		return list;
	}
}
