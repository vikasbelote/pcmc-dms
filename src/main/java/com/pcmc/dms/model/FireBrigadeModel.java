package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_fire_brigade")
@Scope("prototype")
public class FireBrigadeModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "working_person_name")
	private String workingPersonName;
	
	@Column(name = "computer_number")
	private String computerNumber;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getWorkingPersonName() {
		return workingPersonName;
	}

	public void setWorkingPersonName(String workingPersonName) {
		this.workingPersonName = workingPersonName;
	}

	public String getComputerNumber() {
		return computerNumber;
	}

	public void setComputerNumber(String computerNumber) {
		this.computerNumber = computerNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
