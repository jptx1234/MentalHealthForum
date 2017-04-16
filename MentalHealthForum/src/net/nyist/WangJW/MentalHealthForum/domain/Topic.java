package net.nyist.WangJW.MentalHealthForum.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Topic entity. @author MyEclipse Persistence Tools
 */

public class Topic implements java.io.Serializable {

	// Fields

	private Long id;
	private User user;
	private String title;
	private String content;
	private Board board;
	private Timestamp time = new Timestamp(System.currentTimeMillis());
	private Boolean anonymous = false;
	private Short status = 0;
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Topic() {
	}

	/** minimal constructor */
	public Topic(User user) {
		this.user = user;
	}

	/** full constructor */
	public Topic(User user, String title, String content, Timestamp time, Boolean anonymous, Short status,
			Set replies) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.time = time;
		this.anonymous = anonymous;
		this.status = status;
		this.replies = replies;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		if (anonymous == null || anonymous) {
			return User.AnonymousUser;
		}
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

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
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

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}
	
	public Integer getRepliesCount(){
		return this.replies.size();
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", board=" + board
				+ ", time=" + time + ", anonymous=" + anonymous + ", status=" + status + ", replies=" + replies.size() + "]";
	}

	
}