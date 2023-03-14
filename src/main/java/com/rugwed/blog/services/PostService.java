package com.rugwed.blog.services;

import java.util.List;

import com.rugwed.blog.payloads.PostDTO;
import com.rugwed.blog.payloads.PostResponce;

public interface PostService {

	// c

	PostDTO createPost(PostDTO postDTO, Integer userId, Integer catId);

	// u

	PostDTO updatePost(PostDTO postDTO, Integer postId);

	// d

	void deletePost(Integer postId);

	// get all

	PostResponce getAllPost(Integer PageNumber, Integer pageSize, String sortBy, String sortDir);

	// get

	PostDTO getPostById(Integer postId);

	// get all by cat

	List<PostDTO> getPostByCat(Integer catId);

	// get all by user

	List<PostDTO> getPostByUser(Integer userId);

	// search Posts
	List<PostDTO> searchPosts(String keyword);

}
