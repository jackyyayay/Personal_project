package com.scutnews.service;

import java.sql.Timestamp;
import java.util.List;

import com.scutnews.bean.Comment;
import com.scutnews.bean.News;
import com.scutnews.bean.User;
import com.scutnews.dao.CommentDAO;
import com.scutnews.dao.NewsDAO;
import com.scutnews.dao.UserDAO;
import com.scutnews.util.JsonUtil;

public class CommentService {
	private CommentDAO commentDao;
	private NewsDAO newsDao;
	private UserDAO userDao;

	public CommentDAO getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public NewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDAO newsDao) {
		this.newsDao = newsDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	// 发表评论
	public String add(Comment comment) {
		//
		// 用户登陆确定
		//
		// 评论文字检验是否符合规范
		//
		// 临时用户名和资讯ID
		String username = "4233";
		int newsID = 1;
		//
		int flag = 1;// 规范标志位
		// 若符合规范写入数据库
		if (flag == 1) {
			User user = (User) userDao.findByUsername(username).get(0);
			comment.setUser(user);
			News news = (News) newsDao.findById(newsID);
			comment.setNews(news);
			comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
			comment.setState((short) 1);
			commentDao.save(comment);
			String message = "发表评论成功";
			return JsonUtil.toJson("success", message);
		}
		// 若不符合规范则提示用户 评论中包含敏感信息，请修改
		else {
			String message = "发表评论失败";
			return JsonUtil.toJson("error", message);
		}
	}

	// 根据评论的ID搜索评论
	public String info(Comment comment) {
		Comment jcomment = commentDao.findById(comment.getId());
		if (jcomment == null) {
			String message = "无此评论";
			return JsonUtil.toJson("error", message);
		} else {
			return JsonUtil.toJson(jcomment);
		}
	}

	// 删除评论
	public String delete(Comment comment) {
		Comment jcomment = commentDao.findById(comment.getId());
		jcomment.setState((short) 0);
		commentDao.attachDirty(jcomment);
		String message = "删除成功";
		return JsonUtil.toJson("success", message);

	}

	// list
	public String list(int curpage) {
		List total = commentDao.findByState((short) 1);
		int totalSize = total.size();
		int totalPage = 0;
		if (totalSize % 10 != 0) {
			totalPage = totalSize / 10 + 1;
		} else {
			totalPage = totalSize / 10;
		}
		List commentList = commentDao.findByPage(curpage, 10);
		return JsonUtil.toJson(commentList, totalPage);
	}


	//update
	public String update(Comment comment) {

		if (comment != null) {
			commentDao.attachDirty(comment);
			String message = "更新成功";
			return JsonUtil.toJson("success", message);
		} else {
			String message = "更新失败";
			return JsonUtil.toJson("error", message);
		}
	}
}
