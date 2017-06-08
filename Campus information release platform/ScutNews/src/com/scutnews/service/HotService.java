package com.scutnews.service;
import java.util.List;

import com.scutnews.bean.News;
import com.scutnews.dao.NewsDAO;
import com.scutnews.util.JsonUtil;

public class HotService {
    private NewsDAO newsDao;
	public NewsDAO getNewsDao() {
		return newsDao;
	}
	public void setNewsDao(NewsDAO newsDao) {
		this.newsDao = newsDao;
	}
	public String add(News news){
		News news2 = newsDao.findById(news.getId());
		news2.setWeight((short)10);
		newsDao.attachDirty(news2);
        String message = "添加头条成功";	
		return JsonUtil.toJson("success", message);
	}
	public String delete(News news){
		News news2 = newsDao.findById(news.getId());
		news2.setState((short)0);
		newsDao.attachDirty(news2);
        String message = "删除头条成功";	
		return JsonUtil.toJson("success", message);
	}
	public String info(News news){
		News news2 = newsDao.findById(news.getId());
		return JsonUtil.toJson(news2);
	}
	public String update(News news){
		if (newsDao.findById(news.getId()) != null) {
			newsDao.attachDirty(news);
			String message = "更新成功";
			return JsonUtil.toJson("success", message);
		}
		else {
		    String message = "更新失败";
		    return JsonUtil.toJson("error", message);
		}
	}
	public String list(){
		List list = newsDao.findByWeight((short)10);
		if(list.isEmpty()){
			String message = "查找头条失败";	
			return JsonUtil.toJson("error", message);
		}
		else {
			return JsonUtil.toJson(list);
		}
	}
}
