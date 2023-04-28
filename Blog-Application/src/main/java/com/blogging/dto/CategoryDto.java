package com.blogging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CategoryDto {

	
    private Long categoryId;
	
	private String categoryTitle;
	
	private String categoryDescription;
}
