package com.scutnews.action.user;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.User;
import com.scutnews.service.UserService;
import com.scutnews.util.BaseAction;

public class RegisterAction extends BaseAction implements ModelDriven<User>{
	private User user = new User();
    private UserService userService;
    private String json;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String setJson(String json) {
		return this.json = json;
	}
	public String execute(){
		setJson(userService.register(user));
		return "success";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
