package com.scutnews.action.user;

import java.sql.Timestamp;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.Role;
import com.scutnews.bean.User;
import com.scutnews.dao.UserDAO;
import com.scutnews.service.UserService;
import com.scutnews.util.BaseAction;

public class UserUpdateAction extends BaseAction {
	private UserDAO userDao;
	private UserService userService;
	private String json;
	private String id;
	private String username;
	private String school;
	private String nickname;
	private String sex;
	private String email;
	private String telephone;
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String execute() {
		User user = userDao.findById(Integer.valueOf(this.getId()));
		/*if (this.getUsername() != null || !this.getUsername().isEmpty()
				||!this.getUsername().equals("")) {
			user.setUsername(this.getUsername());
		}
		if (this.getSchool() != null || !this.getSchool().isEmpty()
				||!this.getSchool().equals("")) {
			user.setSchool(this.getSchool());
		}
		if (this.getNickname() != null || !this.getNickname().isEmpty()
				||!this.getNickname().equals("")) {
			user.setNickname(this.getNickname());
		}
		if (this.getSex() != null || !this.getSex().isEmpty()
				||!this.getSex().equals("")) {
			user.setSex(this.getSex());
		}
		if (this.getEmail() != null || !this.getEmail().isEmpty()
				||!this.getEmail().equals("")) {
			user.setEmail(this.getEmail());
		}
		if (this.getTelephone() != null || !this.getTelephone() .isEmpty()
				||!this.getTelephone() .equals("")) {
			user.setTelephone(this.getTelephone());
		}
		if (this.getRoleId() != null || !this.getRoleId().isEmpty()
				||!this.getRoleId().equals("")) {
			user.getRole().setId(Integer.valueOf(this.getRoleId()));
		}*/
		user.setUsername(this.getUsername());
		user.setSchool(this.getSchool());
		user.setNickname(this.getNickname());
		user.setSex(this.getSex());
		user.setEmail(this.getEmail());
		user.setTelephone(this.getTelephone());
		user.getRole().setId(Integer.valueOf(this.getRoleId()));
		setJson(userService.update(user));
		return "success";
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
}
