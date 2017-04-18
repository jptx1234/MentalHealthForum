package net.nyist.WangJW.MentalHealthForum.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(serialize=false)
	public static final User AnonymousUser = new User("匿名用户","",false);

	private Long id;
	private String username;
	private String password;
	private String gender = "未填写";
	private Short age;
	private String avatar = "example.jpg";
	private Short status = 1;
	private Boolean isAdmin = false;
	private Set topics = new TreeSet<>();
	private Set replies = new TreeSet<>();

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, Boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	/** full constructor */
	public User(String username, String password, String gender, Short age, String avatar,
			Short status, Boolean isAdmin, Set topics, Set replies) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.avatar = avatar;
		this.status = status;
		this.isAdmin = isAdmin;
		this.topics = topics;
		this.replies = replies;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSONField(serialize=false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
//		this.avatar = avatar;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set getTopics() {
		return this.topics;
	}

	public void setTopics(Set topics) {
		this.topics = topics;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender + ", age="
				+ age + ", avatar=" + avatar + ", status=" + status + ", isAdmin=" + isAdmin + ", topics=" + topics.size()
				+ ", replies=" + replies.size() + "]";
	}
	
}