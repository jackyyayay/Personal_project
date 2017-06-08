package com.scutnews.action.comment;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.scutnews.bean.Comment;
import com.scutnews.dao.CommentDAO;
import com.scutnews.service.CommentService;
import com.scutnews.util.BaseAction;

/**
 * @author LIU
 *
 */
public class CommentListAction extends BaseAction{
	private CommentService commentService;
    private String json;
    private int curpage;
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
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public String execute(){
		
		setJson(commentService.list(this.getCurpage()));
		
		return "success";
	}
    
}
