package com.sapGarden.application.fi.customer.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CustomerJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

	/*
	private final static transient Logger log = LoggerFactory.getLogger("errorLog");
	private BeanUtils beanUtils = null;
	public CustomerJob(){
		getBeanUtils();
	}
	private void getBeanUtils(){
		MyApplicationContextUtil myApplicationContextUtil = new MyApplicationContextUtil();
		this.beanUtils = new BeanUtils();
		beanUtils.setMyApplicationContextUtil(myApplicationContextUtil);
	}
	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		// TODO Auto-generated method stub
		SapClientService sapClientService = (SapClientService)beanUtils.getBeanByName("sapClientService");
		Object o = context.getJobDetail().getJobDataMap().get("owner");
		String owner = "";
		if(o!=null){
			owner = (String)o;
		}
		o = context.getJobDetail().getJobDataMap().get("sapClient");
		long clientId = 0;
		if(o!=null){
			clientId = (Long)o;
		}
		SapClientModel sapClient = null;
		if(clientId>0){
			sapClient = sapClientService.findById(clientId);
		}
		try {
			this.syn(sapClient, owner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String now = sd.format(Calendar.getInstance().getTime());
			log.error(now, e);
			//throw new RuntimeException(e);
		} 
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void syn(SapClientModel sapClient,String user) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException{
		CompareDataService compareDataService = (CompareDataService)beanUtils.getBeanByName("customer_CompareDataService");
		compareDataService.getSapDataAndSave(sapClient, true, LogConstants.OPTTYPE_SCHEDULING, user);
	}
	*/
}
