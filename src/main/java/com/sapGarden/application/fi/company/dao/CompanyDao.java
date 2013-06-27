package com.sapGarden.application.fi.company.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sapGarden.application.fi.company.model.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company,Long>{

	@Modifying
	@Query("delete from Company t where t.comp_code=?1 and t.sapclient=?2")
	public void deleteByComp_codeAndSapclient(String comp_code,long sapclient);
	@Modifying
	@Query("delete from Company t where  t.sapclient=?1")
	public void deleteBySapclient(long sapclient);
}
