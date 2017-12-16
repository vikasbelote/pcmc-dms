package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_town")
@Scope("prototype")
public class TownModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "sheet_number")
	private String sheetNumber;
	
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

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
