package com.sapGarden.global.jqgrid.model;

import java.util.List;

public class Jqgrid_searchModel {

	public static final String OPTION_AND = "AND";
	public static final String OPTION_OR = "OR";
	private String groupOp;
	private List<Jqgrid_searchRulesModel> rules;
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<Jqgrid_searchRulesModel> getRules() {
		return rules;
	}
	public void setRules(List<Jqgrid_searchRulesModel> rules) {
		this.rules = rules;
	}
}
