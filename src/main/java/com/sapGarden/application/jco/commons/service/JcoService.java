package com.sapGarden.application.jco.commons.service;

import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.jco.commons.model.JcoModel;
import com.sapGarden.application.jco.commons.model.JcoReturnModel;

public interface JcoService {
	public JcoReturnModel callFunction(JcoModel jcoModel)throws JcoException;
}
