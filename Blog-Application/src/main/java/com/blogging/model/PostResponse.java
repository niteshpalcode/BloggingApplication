package com.blogging.model;

import java.util.List;

import com.blogging.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElement;
	private Integer totalpages;
	private boolean lastPage;
	
	
}
