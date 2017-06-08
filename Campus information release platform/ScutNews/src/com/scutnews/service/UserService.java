package com.scutnews.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.opensymphony.xwork2.Result;
import com.scutnews.bean.Role;
import com.scutnews.bean.User;
import com.scutnews.dao.RoleDAO;
import com.scutnews.dao.UserDAO;
import com.scutnews.util.JsonUtil;
import com.scutnews.util.Page;

public class UserService {
	private UserDAO userDao;
	private RoleDAO roleDao;
	
    public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public String login(User luser){
    	List userList =  userDao.findByUsername(luser.getUsername());
    	if(userList.isEmpty()){
    		String message = "没有这个用户";
    		return JsonUtil.toJson("error", message);
    	}
    	else{
    		User user = (User)userList.get(0);
    		if (!luser.getPassword().equals(user.getPassword())) {
    			String message = "用户名与密码不一致";
        		return JsonUtil.toJson("error", message);
			}
    		else if (user.getState()==0) {
    			String message = "用户名已被注销";
        		return JsonUtil.toJson("error", message);
			}
   		    else {
   			    String message = "登陆成功";
        		return JsonUtil.toJson("success", message);
		    }
    	}
    }
    
    public String register(User user){
    	List userList = userDao.findByUsername(user.getUsername());
    	if(!userList.isEmpty()){
    		String message = "此用户名已被注册";
    		return JsonUtil.toJson("error", message);
    	}
    	else {
    		Role role = roleDao.findById(3);
    		user.setRole(role);
    		user.setState((short) 1);
    		user.setCreatetime(new Timestamp(System.currentTimeMillis()));
			userDao.save(user);
			String message = "注册成功";
    		return JsonUtil.toJson("success", message);
		}
    }
    
	public String list(int curpage){
		List total = userDao.findByState((short)1);
		int totalSize = total.size();
		int totalPage=0;
		 if(totalSize%10!=0){  
             totalPage = totalSize/10 + 1;  
         }else{  
             totalPage = totalSize/10;  
         }
    	List userList = userDao.findByPage(curpage, 10);
    	return JsonUtil.toJson(userList, totalPage);
    }
    
	public String info(User user){
    	User juser = userDao.findById(user.getId());
    	if(juser==null){
    		String message = "此用户不存在";
    		return JsonUtil.toJson("error", message);
    	}
    	else {
			return JsonUtil.toJson(juser);
		}
    }
    
    public String delete(User user){
    	User juser = userDao.findById(user.getId());
    	juser.setState((short) 0);
    	userDao.attachDirty(juser);
    	String message = "删除成功";
		return JsonUtil.toJson("success", message);
		
    }
    
    public String update(User user){
        
    	if (user != null) {
    		userDao.attachDirty(user);
    		String message = "更新成功";
        	return JsonUtil.toJson("success", message);
		}
    	else {
    		String message = "更新失败";
        	return JsonUtil.toJson("error", message);
		}
    }
    
}
