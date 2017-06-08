package com.scutnews.service;
import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.Result;
import com.scutnews.bean.News;
import com.scutnews.dao.NewsDAO;
import com.scutnews.util.JsonUtil;

public class ActivityService {
	private NewsDAO newsDao;
	
	 public NewsDAO getNewsDao() {
			return newsDao;
	 }

	 public void setNewsDao(NewsDAO newsDao) {
		    this.newsDao = newsDao;
	
	 }
	 //查找所有活动信息
	 public String GetAllActivity(){
		 List ActivityList=newsDao.findAll();
		 if(ActivityList.isEmpty()){
	    		String message = "没有任何活动信息";
	    		return JsonUtil.toJson("error", message);
	    	}
		 else{
			    return JsonUtil.toJson(ActivityList);	 
		 }
			 
	 }
	 //查找一个活动信息
	 public String GetOneActivity(News act){
		 News act1=newsDao.findById(act.getId());
		 if(act1==null||act1.getType()!=1||act1.getState()==0)
		 {String message = "此活动不存在";
 		return JsonUtil.toJson("error", message);
		 }
		 else
		 {return JsonUtil.toJson(act1);}
	 }
	 //增加一个活动
	 public String AddActivity(News act){
		 act.setType((short)1);
         act.setState((short)1);
         act.setCreateTime(new Timestamp(System.currentTimeMillis()));
         //不知道如何设定优先级
         newsDao.save(act);
         String message = "活动发布成功";
 		 return JsonUtil.toJson("success", message);
      }
	 //更新一个活动
	 public String UpdateActivity(News act){
		 if (act != null&&act.getType()==1&&act.getState()!=0) {
	    		newsDao.attachDirty(act);
	    		String message = "更新成功";
	        	return JsonUtil.toJson("success", message);
			}
	    	else {
	    		String message = "更新失败";
	        	return JsonUtil.toJson("error", message);
			}
		 
	 }
	 //删除一个活动
	 public String DeleteActivity(News act){
		 	News act1 = newsDao.findById(act.getId());
		 	if(act1.getType()==1)
		 	{
	    	act1.setState((short) 0);
	    	newsDao.attachDirty(act1);
	    	String message = "删除成功";
			return JsonUtil.toJson("success", message);
		 	}
		 	else
		 	{String message = "删除失败";
        	return JsonUtil.toJson("error", message);
        	}
	 }
	 
}