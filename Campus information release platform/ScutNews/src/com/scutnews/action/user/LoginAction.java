package com.scutnews.action.user;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.User;
import com.scutnews.service.UserService;
import com.scutnews.util.BaseAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class LoginAction extends BaseAction implements ModelDriven<User>{
	private User user = new User();
    private UserService userService;
    private String json;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getJson() {
		return json;
	}
	public String setJson(String json) {
		return this.json = json;
	}
    
	public String execute(){
		setJson(userService.login(user));
		return "success";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
