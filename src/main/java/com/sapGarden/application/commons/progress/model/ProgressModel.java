package com.sapGarden.application.commons.progress.model;


public class ProgressModel {

	private static ProgressModel progressModel;
	private Progress progress;
	private ProgressModel(){
		
	}
	public synchronized static ProgressModel getInstance(){
		if(progressModel==null){
			progressModel = new ProgressModel();
		}
		return progressModel;
	}
	public synchronized Progress getProgress(){
		if(progress==null){
			progress = new Progress();
		}
		return progress;
	}
}
