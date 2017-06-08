package com.scutnews.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String title;
	private String content;
	private Timestamp createTime;
	private Short state;
	private Short type;
	private Short weight;
	private String picture;
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(User user, String title, Timestamp createTime, Short state,
			Short type, Short weight) {
		this.user = user;
		this.title = title;
		this.createTime = createTime;
		this.state = state;
		this.type = type;
		this.weight = weight;
	}

	/** full constructor */
	public News(User user, String title, String content, Timestamp createTime,
			Short state, Short type, Short weight, String picture, Set comments) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
		this.type = type;
		this.weight = weight;
		this.picture = picture;
		this.comments = comments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getWeight() {
		return this.weight;
	}

	public void setWeight(Short weight) {
		this.weight = weight;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}