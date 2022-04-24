package com.blog.app.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> content;
	private Integer pageSize;
	private Integer pageNumber;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;

}
