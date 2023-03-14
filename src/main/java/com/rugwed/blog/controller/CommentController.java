package com.rugwed.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rugwed.blog.payloads.ApiResponce;
import com.rugwed.blog.payloads.CommentDTO;
import com.rugwed.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId) {
		CommentDTO createComment = this.commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment deleted successfully !", true), HttpStatus.OK);
	}

//	@DeleteMapping("comments/{commentId}")
//	public ApiResponce deleteComment(@PathVariable Integer commentId) {
//		this.commentService.deleteComment(commentId);
//		return new ApiResponce("Comment is successfully deleted !", true);
//	}
}
