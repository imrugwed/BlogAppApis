package com.rugwed.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

	private Integer postId;
	
	private String title;

	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDTO user;
	
	private CategoryDTO category;
	
	private Set<CommentDTO> comments=new HashSet<>();

}
