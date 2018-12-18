package com.tts.PresentationProject.BlogPost;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String place;
	private String story;
	private Long userId;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDttm;
	


public BlogPost() {
	
}

public BlogPost(String name, String place, String story,Date updateDttm) {
	this.name = name;
	this.place = place;
	this.story = story;
	this.updateDttm = updateDttm;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getStory() {
	return story;
}


public void setStory(String story) {
	this.story = story;
}
public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
}


public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}



public Date getUpdateDttm() {
	return updateDttm;
}

public void setUpdateDttm(Date updateDttm) {
	this.updateDttm = updateDttm;
}

@Override
public String toString() {
	return "BlogPost [id=" + id + ", name=" + name + ", place=" + place + ", story=" + story + ", userId=" + userId
			+ ", updateDttm=" + updateDttm + "]";
}


}
