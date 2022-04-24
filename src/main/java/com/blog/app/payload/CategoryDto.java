package com.blog.app.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
	
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="Minimum size of category should be of 4 character")
	private String categoryTitle;
	@NotBlank
	@Size(min=4,message="Minimum size of category should be of 4 character")
	private String categoryDescription;

}
