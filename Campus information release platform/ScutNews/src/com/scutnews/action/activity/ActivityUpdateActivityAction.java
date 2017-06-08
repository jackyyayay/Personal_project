package com.scutnews.action.activity;

import java.sql.Timestamp;
import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.dao.NewsDAO;
import com.scutnews.dao.UserDAO;
import com.scutnews.service.ActivityService;
import com.scutnews.util.BaseAction;

public class ActivityUpdateActivityAction extends BaseAction{
	private NewsDAO newsDao;
	private ActivityService activityService;
	private String json;
	private String id;
	private String title;
	private String content;
	private String picture;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
		News activity = newsDao.findById(Integer.valueOf(this.getId()));
		activity.setTitle(this.getTitle());
		activity.setContent(this.getContent());
		activity.setPicture(this.getPicture());
		setJson(activityService.UpdateActivity(activity));
		return "success";
	}
	
	public NewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDAO newsDao) {
		this.newsDao = newsDao;
	}
}