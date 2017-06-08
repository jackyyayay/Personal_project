package com.scutnews.bean;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Comment comment;
	private User user;
	private String content;
	private Timestamp createTime;
	private Short state;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Comment comment, User user, Timestamp createTime, Short state) {
		this.comment = comment;
		this.user = user;
		this.createTime = createTime;
		this.state = state;
	}

	/** full constructor */
	public Reply(Comment comment, User user, String content,
			Timestamp createTime, Short state) {
		this.comment = comment;
		this.user = user;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}