package com.scutnews.action.comment;

/**
 * @author LIU
 *
 */

import com.opensymphony.xwork2.ModelDriven;

import com.scutnews.bean.Comment;
import com.scutnews.service.CommentService;
import com.scutnews.util.BaseAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CommentDeleteAction extends BaseAction implements ModelDriven<Comment> {
	private Comment comment = new Comment();
	private CommentService commentService;
    private String json;
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

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

	public String execute(){
		setJson(commentService.delete(comment));
		return "success";
	}
	
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
	
}
