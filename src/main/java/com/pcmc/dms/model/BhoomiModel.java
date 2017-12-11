package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Entity(name = "dms_bhoomi")
@Scope("prototype")
public class BhoomiModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "serve_no")
	private String serveNumber;
	
	@Column(name = "gat_no")
	private String gatNumber;
	
	@Column(name = "hissa_no")
	private String hissaNumber;
	
	@Column(name = "file_no")
	private String fileNumber;
	
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getServeNumber() {
		return serveNumber;
	}

	public void setServeNumber(String serveNumber) {
		this.serveNumber = serveNumber;
	}

	public String getGatNumber() {
		return gatNumber;
	}

	public void setGatNumber(String gatNumber) {
		this.gatNumber = gatNumber;
	}

	public String getHissaNumber() {
		return hissaNumber;
	}

	public void setHissaNumber(String hissaNumber) {
		this.hissaNumber = hissaNumber;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
