package com.scutnews.service;
import com.scutnews.bean.News;
import com.scutnews.dao.NewsDAO;

import java.sql.Timestamp;
import java.util.List;
import com.scutnews.util.JsonUtil;
import com.scutnews.util.Page;  //get a page news
import com.opensymphony.xwork2.Result;

public class LectureService {
	private NewsDAO newsDAO;
	
	public void setNewsDAO(NewsDAO newsDAO){
		this.newsDAO=newsDAO;
	}
	
	public NewsDAO getNewsDAO(){
		return newsDAO;
	}
	
	public String add(News news){  //发布演讲资讯（将资讯存入数据库）
		List newsList=newsDAO.findByTitle(news.getTitle());
		if(!newsList.isEmpty()){
			String message = "此新闻已发布";
    		return JsonUtil.toJson("error", message);
		}else{
			news.setState((short)1);
			news.setCreateTime(new Timestamp(System.currentTimeMillis()));
			news.setType((short)1);
			news.setWeight((short)1);
			newsDAO.save(news);
			String message="发布成功";
			return JsonUtil.toJson("success", message);
		}
	}
	
	public String delete( News news ){   //删除资讯(就是把该新闻的state设为0)
		News dNews=newsDAO.findById(news.getId());
		dNews.setState((short)0);
		newsDAO.attachDirty(dNews);
		String message="成功删除";
		return  JsonUtil.toJson("success",message);
	}
	
	public String update(News news){  //修改资讯(将新新闻重新写入数据库)
		if(news != null){
			newsDAO.attachDirty(news);
			String message="修改成功";
			return JsonUtil.toJson("success",message);
		}else{
			String message="修改失败";
			return JsonUtil.toJson("error",message);
		}
	}
	
	public String list(){  //列表显示
		List newsList=newsDAO.findByState((short)1);
		return JsonUtil.toJson(newsList);
	}
	
	public String info(News news){   //获取某次演讲详细信息
		News dNews=newsDAO.findById(news.getId());
		if( dNews==null ){
			String message="资讯已被删除";
			return JsonUtil.toJson("",message);
		}else{
			return JsonUtil.toJson(dNews);
		}
	}

}
