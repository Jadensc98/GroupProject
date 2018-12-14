package com.tts.PresentationProject.BlogPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String where;
	private String story;
	


public BlogPost() {
	
}

public BlogPost(String name, String where, String story) {
	this.name = name;
	this.where = where;
	this.story = story;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getWhere() {
	return where;
}


public void setWhere(String where) {
	this.where = where;
}


public String getStory() {
	return story;
}


public void setStory(String story) {
	this.story = story;
}


@Override
public String toString() {
	return "BlogPost [id=" + id + ", name=" + name + ", where=" + where + ", story=" + story + "]";
}
}
