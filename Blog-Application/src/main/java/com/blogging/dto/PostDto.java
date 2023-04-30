package com.blogging.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blogging.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private List<CommentDto> comments = new ArrayList<>();
	
	
}
