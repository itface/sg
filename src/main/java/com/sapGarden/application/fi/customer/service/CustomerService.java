package com.sapGarden.application.fi.customer.service;

import com.sapGarden.application.fi.customer.model.Customer;

public interface CustomerService {

	public String addCustomer(Customer customer,String user,String sapclientAlias);
	public String updateCustomer(Customer customer,String user,String sapclientAlias);
	public String delCustomer(Customer customer,String user,String sapclientAlias);
}
