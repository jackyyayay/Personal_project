package com.scutnews.action.lecture;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.util.BaseAction;
import com.scutnews.service.LectureService;
import com.scutnews.dao.NewsDAO;

public class LectureUpdateAction extends BaseAction{

	private LectureService lectureService;
	private String json;
	private NewsDAO newsDAO;
	private String id;
	private String ownerid;
	private String title;
	private String content;
	private String picture;
	
	public String getJson(){
		return json;
	}
	public String setJson(String json){
		return this.json=json;
	}
	public String execute(){
		News news=newsDAO.findById(Integer.valueOf(this.getId()));
		
		news.setTitle(this.getTitle());
		news.setContent(this.getContent());
		news.setPicture(this.getPicture());
		
		setJson(lectureService.update(news));
		return "success";
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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
	public LectureService getLectureService() {
		return lectureService;
	}
	public void setLectureService(LectureService lectureService) {
		this.lectureService = lectureService;
	}

}
