package com.rugwed.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rugwed.blog.entity.Category;
import com.rugwed.blog.entity.Post;
import com.rugwed.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Category> findByCategory(Category category);

	// searching
	List<Post> findByTitleContaining(String title);
}
