package com.sapGarden.global.paramQueryGrid;

import java.util.List;

public class ParamQueryGrid_DataJson {

	private long totalRecords;
	private int curPage;
	private List data;
	public ParamQueryGrid_DataJson(){
		
	}
	public ParamQueryGrid_DataJson(long totalRecords,int curPage,List data){
		this.totalRecords = totalRecords;
		this.curPage = curPage;
		this.data = data;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
}
