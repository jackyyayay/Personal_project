package com.scutnews.action.lecture;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.service.LectureService;
import com.scutnews.util.BaseAction;

public class LectureDeleteAction extends BaseAction implements ModelDriven<News>{

	private News news=new News();
	private LectureService lectureService;
	private String json;

	
	public String getJson(){
		return json;
	}
	public String setJson(String json){
		return this.json=json;
	}
	public String execute(){
		setJson(lectureService.delete(news));
		return "success";
	}
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	public LectureService getLectureService() {
		return lectureService;
	}
	public void setLectureService(LectureService lectureService) {
		this.lectureService = lectureService;
	}
    
}
