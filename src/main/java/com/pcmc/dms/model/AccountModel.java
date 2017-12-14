package com.pcmc.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "dms_account")
@Scope("prototype")
public class AccountModel {
	
	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private Integer rowId;
	
	@Column(name = "gatta_number")
	private String gattaNumber;
	
	@Column(name = "financial_year")
	private String financialYear;
	
	@Column(name = "voucher_number")
	private String voucherNumber;
	
	@Column(name = "image_path")
	private String imagePath;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getGattaNumber() {
		return gattaNumber;
	}

	public void setGattaNumber(String gattaNumber) {
		this.gattaNumber = gattaNumber;
	}

	
}
