package com.sapGarden.application.fi.company.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
//@Component("bo_fin_company_job")
public class CompanyJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//	private final static transient Logger log = LoggerFactory.getLogger("errorLog");
//	private final String type = "bo_fin_company";
//	private BeanUtils beanUtils = null;
//	private CommonSynService commonSynService;
//	private SchedulingService schedulingService;
//	private SapDataCollectionService sapDataCollectionService;
//	public CompanyJob(){
//		getBeanUtils();
//		this.getCommonSynService();
//		this.getSchedulingService();
//		this.getSapClientService();
//	}
//	public void getBeanUtils(){
//		MyApplicationContextUtil myApplicationContextUtil = new MyApplicationContextUtil();
//		this.beanUtils = new BeanUtils();
//		beanUtils.setMyApplicationContextUtil(myApplicationContextUtil);
//	}
//	public void getCommonSynService(){
//		commonSynService = (CommonSynService)beanUtils.getBeanByName("commonSynService");
//	}
//	public void getSchedulingService(){
//		schedulingService = (SchedulingService)beanUtils.getBeanByName("schedulingService");
//	}
//	public void getSapClientService(){
//		sapDataCollectionService = (SapDataCollectionService)beanUtils.getBeanByType(SapDataCollectionService.class);
//	}
//	@Override
//	//在使用Spring代理时，默认只有在public可见度的方法的@Transactional 注解才是有效的，其它可见度（protected、private、包可见）的方法上即使有@Transactional 注解也不会应用这些事务属性的
//	protected void executeInternal(JobExecutionContext context) throws JobExecutionException{
//		// TODO Auto-generated method stub
//		if(commonSynService!=null){
//			try {
//				Object o = context.getJobDetail().getJobDataMap().get("owner");
//				String owner = "";
//				if(o!=null){
//					owner = (String)o;
//				}
//				o = context.getJobDetail().getJobDataMap().get("sapClient");
//				long clientId = 0;
//				if(o!=null){
//					clientId = Long.parseLong((String)o);
//				}
//				//Scheduling scheduling = schedulingService.findOneByJobGroup(type);
//				SapDataCollection sapDataCollection = null;
//				if(clientId>0){
//					sapDataCollection = sapDataCollectionService.findById(clientId);
//				}
//				commonSynService.syn(sapDataCollection,type,LogConstants.OPTTYPE_SCHEDULING,owner,null);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	
}
