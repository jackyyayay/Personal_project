package com.scutnews.action.activity;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.service.ActivityService;
import com.scutnews.util.BaseAction;

public class ActivityGetOneActivityAction extends BaseAction implements ModelDriven<News>{
	private News activity = new News();
    private ActivityService activityService;
    private String json;
	public News getActivity() {
		return activity;
	}
	public void setActivity(News activity) {
		this.activity = activity;
	}
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String execute(){
		setJson(activityService.GetOneActivity(activity));
		return "success";
	}
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return activity;
	}
}
