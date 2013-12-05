package com.sapGarden.application.fi.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.fi.customer.model.Customer;
import com.sapGarden.application.fi.customer.service.CustomerService;
import com.sapGarden.global.utils.commonUtils.XmlUtil;
import com.sapGarden.system.db.DbContextHolder;

@Controller
@RequestMapping("/fi/customer/external")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping(value = "/new",method=RequestMethod.POST)
	public @ResponseBody String addCustomer(String customerXml,String user,String sapclientAlias){
		String name = null;
		if(customerXml!=null&&!"".equals(customerXml)){
			Customer customer = XmlUtil.toBean(customerXml, Customer.class);
			name=customerService.addCustomer(customer, user, sapclientAlias);
		}
		return name;
	}
}
