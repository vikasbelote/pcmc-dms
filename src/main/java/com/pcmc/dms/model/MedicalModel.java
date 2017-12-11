package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_medical")
@Scope("prototype")
public class MedicalModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "head_office")
	private String headOffice;
	
	@Column(name = "period")
	private String period;
	
	@Column(name = "ycmh")
	private String ycmh;
	
	@Column(name = "hospital")
	private String hospital;
	
	@Column(name = "dispensaries")
	private String dispensaries;
	
	@Column(name = "table_no")
	private String tableNumber;
	
	@Column(name = "file_no")
	private String fileNumber;
	
	@Column(name = "keyward")
	private String keyward;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(String headOffice) {
		this.headOffice = headOffice;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getYcmh() {
		return ycmh;
	}

	public void setYcmh(String ycmh) {
		this.ycmh = ycmh;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDispensaries() {
		return dispensaries;
	}

	public void setDispensaries(String dispensaries) {
		this.dispensaries = dispensaries;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getKeyward() {
		return keyward;
	}

	public void setKeyward(String keyward) {
		this.keyward = keyward;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
