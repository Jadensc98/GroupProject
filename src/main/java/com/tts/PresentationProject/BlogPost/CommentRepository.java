package com.tts.PresentationProject.BlogPost;

import javax.xml.stream.events.Comment;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comments,Long>{
	
}