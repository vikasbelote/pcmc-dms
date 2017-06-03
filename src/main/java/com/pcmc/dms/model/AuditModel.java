package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_audit")
@Scope("prototype")
public class AuditModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "record_type")
	private String recordType;
	
	@Column(name = "gut_no")
	private String gutNo;
	
	@Column(name = "rack_no")
	private String rackNo;
	
	@Column(name = "doc_no")
	private String docNo;
	
	@Column(name = "vibhag_sanketik_no")
	private String vibhagSanketikNo;
	
	@Column(name = "up_vibhag_sanketik_no")
	private String upVibhagSanketikNo;
	
	@Column(name = "gatta_no")
	private String gattaNo;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "vibhag_name")
	private String vibhagName;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getGutNo() {
		return gutNo;
	}

	public void setGutNo(String gutNo) {
		this.gutNo = gutNo;
	}

	public String getRackNo() {
		return rackNo;
	}

	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getVibhagSanketikNo() {
		return vibhagSanketikNo;
	}

	public void setVibhagSanketikNo(String vibhagSanketikNo) {
		this.vibhagSanketikNo = vibhagSanketikNo;
	}

	public String getUpVibhagSanketikNo() {
		return upVibhagSanketikNo;
	}

	public void setUpVibhagSanketikNo(String upVibhagSanketikNo) {
		this.upVibhagSanketikNo = upVibhagSanketikNo;
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

	public String getVibhagName() {
		return vibhagName;
	}

	public void setVibhagName(String vibhagName) {
		this.vibhagName = vibhagName;
	}

}
