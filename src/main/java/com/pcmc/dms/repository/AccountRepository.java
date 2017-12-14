package com.pcmc.dms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pcmc.dms.model.AccountModel;

@Repository
public class AccountRepository extends BaseRepository {
	
	public List<AccountModel> getModelList(AccountModel accountModel){
		
		List<AccountModel> list = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select row_id,gatta_number,financial_year,voucher_number ");
		sb.append("from dms_account ");
		sb.append("where (financial_year like CONCAT(TRIM(IFNULL('" + accountModel.getFinancialYear() + "', '')), '%') or financial_year is null) ");
		sb.append("and (voucher_number like CONCAT(TRIM(IFNULL('" + accountModel.getVoucherNumber() + "', '')), '%')  or voucher_number is null) ");
		sb.append("LIMIT 100");
		
		list = super.getList(sb.toString(),AccountModel.class);

		return list;
	}
}
