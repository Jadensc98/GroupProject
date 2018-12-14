package com.tts.PresentationProject.BlogPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Comments {
	private Long idBlogPost;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String commentPost;
	


public Comments() {
	
}

public Comments(String commentPost) {
	this.commentPost = commentPost;
}

public String getCommentPost() {
	return commentPost;
}

public void setCommentPost(String commentPost) {
	this.commentPost = commentPost;
}

@Override
public String toString() {
	return "Comments [id=" + id + ", commentPost=" + commentPost + "]";
}
}
