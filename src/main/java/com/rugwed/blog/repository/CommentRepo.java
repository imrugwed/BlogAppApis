package com.rugwed.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rugwed.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
}
