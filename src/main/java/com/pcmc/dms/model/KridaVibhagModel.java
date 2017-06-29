package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity(name = "dms_krida")
@Component
@Scope("prototype")
public class KridaVibhagModel {
	
	@Id
	@GeneratedValue
	@Column(name="row_id")
	private Integer rowId;
	
	@Column(name="nasti_number")
	private String nastiNumber;
	
	@Column(name="image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
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
