package com.scutnews.action.comment;

import java.sql.Timestamp;
import com.scutnews.bean.Comment;
import com.scutnews.bean.User;
import com.scutnews.dao.CommentDAO;
import com.scutnews.service.CommentService;
import com.scutnews.util.BaseAction;

/**
 * @author LIU
 *
 */
public class CommentUpdateAction extends BaseAction {
	private CommentService commentService;
	private CommentDAO commentDao;
	private String json;
	private String id;
	private String content;
	private Timestamp creatTime;
	
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public CommentDAO getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String execute() {
		Comment comment = commentDao.findById(Integer.valueOf(this.getId()));
		
		comment.setContent(this.getContent());
		comment.setCreateTime(this.getCreatTime());
		
		setJson(commentService.update(comment));
		return "success";
	}

	

}
