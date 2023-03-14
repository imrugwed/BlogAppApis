package com.rugwed.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rugwed.blog.entity.Comment;
import com.rugwed.blog.entity.Post;
import com.rugwed.blog.exceptions.ResourceNotFoundException;
import com.rugwed.blog.payloads.CommentDTO;
import com.rugwed.blog.repository.CommentRepo;
import com.rugwed.blog.repository.PostRepo;
import com.rugwed.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		Comment comment = this.modelMapper.map(commentDTO, Comment.class);

		comment.setPost(post);

		Comment saveComment = this.commentRepo.save(comment);
		return this.modelMapper.map(saveComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
			this.commentRepo.delete(com);
	}

}
