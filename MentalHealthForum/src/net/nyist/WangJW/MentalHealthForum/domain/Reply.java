package net.nyist.WangJW.MentalHealthForum.domain;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Long id;
	private Topic topic;
	private User user;
	private String content;
	private Timestamp time;
	private Boolean anonymous;
	private Boolean status;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Topic topic, User user, Timestamp time) {
		this.topic = topic;
		this.user = user;
		this.time = time;
	}

	/** full constructor */
	public Reply(Topic topic, User user, String content, Timestamp time, Boolean anonymous, Boolean status) {
		this.topic = topic;
		this.user = user;
		this.content = content;
		this.time = time;
		this.anonymous = anonymous;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getAnonymous() {
		return this.anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}