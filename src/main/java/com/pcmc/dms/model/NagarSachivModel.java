package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_nagar_sachiv")
@Scope("prototype")
public class NagarSachivModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "body_name")
	private String bodyName;
	
	@Column(name = "karypatrika_no")
	private String karypatrikaNumber;
	
	@Column(name = "karypatrika_date")
	private String karypatrikaDate;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getBodyName() {
		return bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}

	public String getKarypatrikaNumber() {
		return karypatrikaNumber;
	}

	public void setKarypatrikaNumber(String karypatrikaNumber) {
		this.karypatrikaNumber = karypatrikaNumber;
	}

	public String getKarypatrikaDate() {
		return karypatrikaDate;
	}

	public void setKarypatrikaDate(String karypatrikaDate) {
		this.karypatrikaDate = karypatrikaDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
