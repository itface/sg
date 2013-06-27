package com.sapGarden.application.commons.progress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.progress.model.Progress;
import com.sapGarden.application.commons.progress.model.ProgressModel;

@Controller
@RequestMapping("/application/common/progress")
public class ProgressController {

	/*
	@RequestMapping(value=("/getProgressPercent"))
	public @ResponseBody int getProgressPercent(){
		Progress progress = ProgressModel.getInstance().getProgress();
		return progress.getPercent();
	}
	@RequestMapping(value=("/getTotalNum"))
	public @ResponseBody String getTotalNum(){
		Progress progress = ProgressModel.getInstance().getProgress();
		return ""+progress.getTotalNum()+","+progress.getTotalCustomer()+"";
	}
	@RequestMapping(value=("/getTotalTime"))
	public @ResponseBody long initProgressPercent(){
		Progress progress = ProgressModel.getInstance().getProgress();
		long s=progress.getBeginTime();
		long e=progress.getEndTime();
		long totalTime = (e-s)/1000;
		ProgressModel.getInstance().getProgress().init();
		return totalTime;
	}
	@RequestMapping(value=("/cancelInit"))
	public @ResponseBody void cancelInit(){
		ProgressModel.getInstance().getProgress().setCancel(true);

	}
	*/
}
