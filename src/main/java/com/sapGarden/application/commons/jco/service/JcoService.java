package com.sapGarden.application.commons.jco.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.JcoReturnModel;

public interface JcoService {
	public JcoReturnModel callFunction(JcoModel jcoModel) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
