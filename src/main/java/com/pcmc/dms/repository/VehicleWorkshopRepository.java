package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.VehicleWorkshopModel;

@Repository
public class VehicleWorkshopRepository extends BaseRepository {
	
	public List<VehicleWorkshopModel> getVehicleWorkshopModelList(VehicleWorkshopModel vehicleWorkshopModel){
		
		List<VehicleWorkshopModel> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("select row_id, table_no, nasti_name, nasti_no ");
		sb.append("from dms_vehicle_workshop ");
		sb.append("where (table_no like CONCAT(TRIM(IFNULL('" + vehicleWorkshopModel.getTableNumber() + "', '')), '%') or table_no is null) ");
		sb.append("and (nasti_name like CONCAT(TRIM(IFNULL('" + vehicleWorkshopModel.getNastiName() + "', '')), '%')  or nasti_name is null) ");
		sb.append("and (nasti_no like CONCAT(TRIM(IFNULL('" + vehicleWorkshopModel.getNastiNumber() + "', '')), '%') or nasti_no is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(), VehicleWorkshopModel.class);
		
		return list;
	}

}
