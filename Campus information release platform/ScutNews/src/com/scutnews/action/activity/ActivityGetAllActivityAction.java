package com.scutnews.action.activity;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.service.ActivityService;
import com.scutnews.util.BaseAction;

public class ActivityGetAllActivityAction extends BaseAction {
    private ActivityService activityService;
    private String json;
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
		setJson(activityService.GetAllActivity());
		return "success";
	}
}
