package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_bhuyari")
@Scope("prototype")
public class BhuyariModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "nivada_number")
	private String nivadaNumber;
	
	@Column(name = "work_name")
	private String workName;
	
	@Column(name = "tendor_name")
	private String tendorName;
	
	@Column(name = "budget_tartude_number")
	private String budgetTartudeNumber;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getNivadaNumber() {
		return nivadaNumber;
	}

	public void setNivadaNumber(String nivadaNumber) {
		this.nivadaNumber = nivadaNumber;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getBudgetTartudeNumber() {
		return budgetTartudeNumber;
	}

	public void setBudgetTartudeNumber(String budgetTartudeNumber) {
		this.budgetTartudeNumber = budgetTartudeNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTendorName() {
		return tendorName;
	}

	public void setTendorName(String tendorName) {
		this.tendorName = tendorName;
	}
}
