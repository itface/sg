package com.sapGarden.global.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SimplePageableImpl implements Pageable{

	private int DEFAULT_PAGESIZE;
	private int pageNumber;
	private Sort sort;
	private int offset;
	public SimplePageableImpl(int pageNumber,int rowsPerPage,Sort sort){
		this.pageNumber = pageNumber;
		this.DEFAULT_PAGESIZE = rowsPerPage;
		this.sort = sort;
		this.offset=(pageNumber-1)*rowsPerPage;
	}
	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return offset;
	}

	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return this.pageNumber;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return DEFAULT_PAGESIZE;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return sort;
	}

}
