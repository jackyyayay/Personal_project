package com.scutnews.action.hot;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.News;
import com.scutnews.service.HotService;
import com.scutnews.util.BaseAction;

public class HotAddAction extends BaseAction implements ModelDriven<News>{
	private News news = new News();
    private HotService hotService;
    private String json;
	public HotService getHotService() {
		return hotService;
	}
	public void setHotService(HotService hotService) {
		this.hotService = hotService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public String execute(){
		setJson(hotService.add(news));
		return "success";
	}
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return news;
	}
	
}
