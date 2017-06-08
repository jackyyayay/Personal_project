package com.scutnews.action.user;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.User;
import com.scutnews.dao.UserDAO;
import com.scutnews.service.UserService;
import com.scutnews.util.BaseAction;

public class UserListAction extends BaseAction{
	private UserService userService;
    private String json;
    private int curpage;
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String execute(){
		
		setJson(userService.list(this.getCurpage()));
		
		return "success";
	}
}
