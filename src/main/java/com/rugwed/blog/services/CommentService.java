package com.rugwed.blog.services;

import com.rugwed.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO,Integer postId);
	
	void deleteComment(Integer  commentId);
}
