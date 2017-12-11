package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_iti")
@Scope("prototype")
public class ItiModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "iti_name")
	private String itiName;
	
	@Column(name = "sub_iti_name")
	private String subItiName;
	
	@Column(name = "nasti_name")
	private String nastiName;
	
	@Column(name = "nasti_number")
	private String nastiNumber;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getItiName() {
		return itiName;
	}

	public void setItiName(String itiName) {
		this.itiName = itiName;
	}

	public String getSubItiName() {
		return subItiName;
	}

	public void setSubItiName(String subItiName) {
		this.subItiName = subItiName;
	}

	public String getNastiName() {
		return nastiName;
	}

	public void setNastiName(String nastiName) {
		this.nastiName = nastiName;
	}

	public String getNastiNumber() {
		return nastiNumber;
	}

	public void setNastiNumber(String nastiNumber) {
		this.nastiNumber = nastiNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
