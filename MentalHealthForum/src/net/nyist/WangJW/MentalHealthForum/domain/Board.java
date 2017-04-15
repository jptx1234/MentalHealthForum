package net.nyist.WangJW.MentalHealthForum.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Board entity. @author MyEclipse Persistence Tools
 */

public class Board implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set topics = new HashSet(0);

	// Constructors

	/** default constructor */
	public Board() {
	}

	/** minimal constructor */
	public Board(String name) {
		this.name = name;
	}

	/** full constructor */
	public Board(String name, Set topics) {
		this.name = name;
		this.topics = topics;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getTopics() {
		return this.topics;
	}

	public void setTopics(Set topics) {
		this.topics = topics;
	}

}