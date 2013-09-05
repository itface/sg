package com.sapGarden.application.commons.runtime.columninfo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;


@Entity
@Table(name="BO_SYNCONFIG_RUNTIME_COLINFO")
public class RuntimeColumnInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720613L;
	
	@Id
	@TableGenerator(name = "bo_synconfig_runtime_colinfo_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "bo_synconfig_runtime_colinfo_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 10000,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "bo_synconfig_runtime_colinfo_gen")
	private long id;
	private int ifkey;
	private String sourceColumn;
	private String sourceColumnName;
	private String targetColumn;
	private String targetColumnName;
	private String status;
	private String businesstype;
	private long sapclient;
	private int searchable;
	private String searchtype;
	public RuntimeColumnInfo(){
		super();
	}
	public RuntimeColumnInfo(JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn){
		if(jqgirdNewDbReflectOfColumn!=null){			
			String newId = jqgirdNewDbReflectOfColumn.getId();
			//如果newId不为空并且不为_empty，则说明是更新，要设置id，如果不设置id，则为更新
			if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
				this.id=Long.parseLong(newId);
			}
			this.sourceColumn=jqgirdNewDbReflectOfColumn.getSourceColumn();
			this.targetColumn=jqgirdNewDbReflectOfColumn.getTargetColumn();
			this.status=jqgirdNewDbReflectOfColumn.getStatus();
			this.businesstype=jqgirdNewDbReflectOfColumn.getBusinesstype();
			this.sourceColumnName=jqgirdNewDbReflectOfColumn.getSourceColumnName();
			this.targetColumnName=jqgirdNewDbReflectOfColumn.getTargetColumnName();
			this.ifkey=jqgirdNewDbReflectOfColumn.getIfkey();
			this.sapclient=jqgirdNewDbReflectOfColumn.getSapclient();
			this.searchable=jqgirdNewDbReflectOfColumn.getSearchable();
			this.searchtype=jqgirdNewDbReflectOfColumn.getSearchtype();
		}
	}
	public RuntimeColumnInfo(BaseTableModel baseTableModel,long sapclient){
		if(baseTableModel!=null){
			this.sourceColumn = baseTableModel.getColumnname();
			this.sourceColumnName = baseTableModel.getColumnmemo();
			this.targetColumn = baseTableModel.getColumnname();
			this.targetColumnName = baseTableModel.getColumnmemo();
			this.businesstype = baseTableModel.getTablename();
			this.ifkey=0;
			this.sapclient=sapclient;
			this.searchable=0;
			this.status="ACTIVATE";
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSourceColumn() {
		return sourceColumn;
	}
	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}
	public String getTargetColumn() {
		return targetColumn;
	}
	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getSourceColumnName() {
		return sourceColumnName;
	}
	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}
	public String getTargetColumnName() {
		return targetColumnName;
	}
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	public int getIfkey() {
		return ifkey;
	}
	public void setIfkey(int ifkey) {
		this.ifkey = ifkey;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	public int getSearchable() {
		return searchable;
	}
	public void setSearchable(int searchable) {
		this.searchable = searchable;
	}
	public String getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	
}
