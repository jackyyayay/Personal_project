package com.scutnews.action.comment;

/**
 * @author LIU
 *
 */

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.Comment;
import com.scutnews.bean.News;
import com.scutnews.bean.User;
import com.scutnews.service.CommentService;
import com.scutnews.util.BaseAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CommentAddAction extends BaseAction implements ModelDriven<Comment> {
	private Comment comment = new Comment();
	private CommentService commentService;
	private String json;

	

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String execute() {
		setJson(commentService.add(comment));
		return "success";
	}

	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
}
