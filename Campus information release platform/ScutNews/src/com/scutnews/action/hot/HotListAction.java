package com.scutnews.action.hot;

import com.scutnews.service.HotService;
import com.scutnews.util.BaseAction;

public class HotListAction extends BaseAction {
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
		setJson(hotService.list());
		return "success";
	}
}
