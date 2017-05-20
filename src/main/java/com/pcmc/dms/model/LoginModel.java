package com.pcmc.dms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Entity(name = "dms_dept_login")
@Scope("prototype")
public class LoginModel {
	
	@Id
	@GeneratedValue
	@Column(name = "login_id")
	private Integer loginId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_pwd")
	private String password;
	
	@Column(name = "print")
	private Boolean isPrint;
	
	@Column(name = "download")
	private Boolean isDownload;
	
	@Column(name = "update_file")
	private Boolean isUpdate;
	
	@Column(name = "mandatory_pwd")
	private Boolean isMandPwd;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dept_id")
	private DepartmentModel department;

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}

	public Boolean getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Boolean isPrint) {
		this.isPrint = isPrint;
	}

	public Boolean getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Boolean isDownload) {
		this.isDownload = isDownload;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Boolean getIsMandPwd() {
		return isMandPwd;
	}

	public void setIsMandPwd(Boolean isMandPwd) {
		this.isMandPwd = isMandPwd;
	}
	
	
	
}
