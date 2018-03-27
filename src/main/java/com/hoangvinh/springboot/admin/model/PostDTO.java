package com.hoangvinh.springboot.admin.model;

import java.io.Serializable;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private String imageURL;
	private Long categoryID;

	public PostDTO() {
	}

	public PostDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
