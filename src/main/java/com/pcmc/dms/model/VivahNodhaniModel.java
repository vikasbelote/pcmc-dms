package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity(name = "dms_vivah_nodhani")
public class VivahNodhaniModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "prabhag_name")
	private String prabhagName;
	
	@Column(name = "entity_name")
	private String entityName;
	
	@Column(name = "entity_registration_no")
	private String entityRegistrationNo;
	
	@Column(name = "entity_registration_date")
	private String entityRegistrationDate;
	
	@Column(name = "gatta_no")
	private String gattaNo;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getPrabhagName() {
		return prabhagName;
	}

	public void setPrabhagName(String prabhagName) {
		this.prabhagName = prabhagName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityRegistrationNo() {
		return entityRegistrationNo;
	}

	public void setEntityRegistrationNo(String entityRegistrationNo) {
		this.entityRegistrationNo = entityRegistrationNo;
	}

	public String getEntityRegistrationDate() {
		return entityRegistrationDate;
	}

	public void setEntityRegistrationDate(String entityRegistrationDate) {
		this.entityRegistrationDate = entityRegistrationDate;
	}

	public String getGattaNo() {
		return gattaNo;
	}

	public void setGattaNo(String gattaNo) {
		this.gattaNo = gattaNo;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
