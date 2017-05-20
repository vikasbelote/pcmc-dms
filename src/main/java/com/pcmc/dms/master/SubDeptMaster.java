package com.pcmc.dms.master;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pcmc.dms.model.DepartmentModel;

@Component
@Scope("prototype")
@Entity(name = "dms_sub_dept")
public class SubDeptMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "sub_dept_id")
	private Integer subDeptId;
	
	@Column(name = "sub_dept_value")
	private String subDeptValue;
	
	@Column(name = "sub_dept_name")
	private String subDeptName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dept_id")
	private DepartmentModel department;

	public Integer getSubDeptId() {
		return subDeptId;
	}

	public void setSubDeptId(Integer subDeptId) {
		this.subDeptId = subDeptId;
	}

	public String getSubDeptName() {
		return subDeptName;
	}

	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}

	public String getSubDeptValue() {
		return subDeptValue;
	}

	public void setSubDeptValue(String subDeptValue) {
		this.subDeptValue = subDeptValue;
	}

	
}
