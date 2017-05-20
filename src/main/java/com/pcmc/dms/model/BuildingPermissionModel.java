package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_building_permission")
@Scope("prototype")
public class BuildingPermissionModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "serve_no")
	private String serveNo;
	
	@Column(name = "architect_name")
	private String architectName;
	
	@Column(name = "owner_name")
	private String ownerName;
	
	@Column(name = "building_permission_no")
	private String buildingPermissionNo;
	
	@Column(name = "gatta_no")
	private String gattaNo;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getServeNo() {
		return serveNo;
	}

	public void setServeNo(String serveNo) {
		this.serveNo = serveNo;
	}

	public String getArchitectName() {
		return architectName;
	}

	public void setArchitectName(String architectName) {
		this.architectName = architectName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getBuildingPermissionNo() {
		return buildingPermissionNo;
	}

	public void setBuildingPermissionNo(String buildingPermissionNo) {
		this.buildingPermissionNo = buildingPermissionNo;
	}

	public String getGattaNo() {
		return gattaNo;
	}

	public void setGattaNo(String gattaNo) {
		this.gattaNo = gattaNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
