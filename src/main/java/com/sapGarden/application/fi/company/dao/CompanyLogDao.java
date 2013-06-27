package com.sapGarden.application.fi.company.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.fi.company.model.CompanyLog;


public interface CompanyLogDao extends PagingAndSortingRepository<CompanyLog,Long>,JpaSpecificationExecutor<CompanyLog>{

	@Modifying
	@Transactional
	//@Query("update CompanyLog t set t.processstatus='已处理' where t.id in (:ids)")
	@Query("update CompanyLog t set t.processstatus='已处理' where t.sapclient=:sapclient and (to_char(t.id) = :ids or instr(:ids,','||t.id||',',1,1)>0 or instr(:ids,t.id||',',1,1)=1 or(instr(:ids,','||t.id,1,1)>0 and instr(:ids,','||t.id,1,1)=length(:ids)-length(','||t.id)+1))")
	public void updateProcessstatusByIds(@Param("ids")String ids,@Param("sapclient")long sapclient);
	@Modifying
	@Query("delete from CompanyLog t where t.sapclient=:sapclient")
	public void deleteAllBySapclient(@Param("sapclient")long sapclient);

}
