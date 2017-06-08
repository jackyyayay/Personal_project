package com.scutnews.action.comment;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.Comment;
import com.scutnews.bean.User;
import com.scutnews.service.CommentService;
import com.scutnews.util.BaseAction;

/**
 * @author LIU
 * 
 */
public class CommentInfoAction extends BaseAction implements
		ModelDriven<Comment> {
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
		setJson(commentService.info(comment));
		return "success";
	}

	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}

}
