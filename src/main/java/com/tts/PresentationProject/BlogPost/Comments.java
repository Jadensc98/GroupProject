package com.tts.PresentationProject.BlogPost;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Comments {
	private Long idBlogPost;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String commentPost;
	private Long userId;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDttm;
	


public Comments() {
	
}

public Comments(String commentPost) {
	this.commentPost = commentPost;
}
public Comments(String commentPost, Date updateDttm) {
	this.commentPost = commentPost;
	this.updateDttm = updateDttm;
}

public String getCommentPost() {
	return commentPost;
}

public void setCommentPost(String commentPost) {
	this.commentPost = commentPost;
}

public Date getUpdateDttm() {
	return updateDttm;
}

public void setUpdateDttm(Date updateDttm) {
	this.updateDttm = updateDttm;
}

@Override
public String toString() {
	return "Comments [id=" + id + ", commentPost=" + commentPost + "]";
}
}
