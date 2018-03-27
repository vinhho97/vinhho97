package com.hoangvinh.springboot.client.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hoangvinh.springboot.admin.model.PostDTO;
import com.hoangvinh.springboot.admin.service.PostService;

@RestController
public class TestRestController {

	@Autowired
	private PostService postService;

	private String UPLOAD_DIR = "C:\\Users\\VietAnh\\workspace\\agriculture-web\\src\\main\\resources\\templates\\admin\\web\\post\\uploads\\";

	@RequestMapping(value = "/upload-file", method = RequestMethod.POST)
	public ResponseEntity<PostDTO> upload(@RequestParam("file") MultipartFile inputFile) throws IOException {
		PostDTO postDTO = new PostDTO();
		HttpHeaders headers = new HttpHeaders();
		String originalFilename = inputFile.getOriginalFilename();
		File destinationFile = new File(UPLOAD_DIR + File.separator + originalFilename);
		inputFile.transferTo(destinationFile);
		postDTO.setDescription("isjdg");
		postDTO.setCategoryID(1L);
		postDTO.setImageURL(originalFilename);
		postService.addPost(postDTO);
		headers.add("File Uploaded Successfully - ", inputFile.getName());
		return new ResponseEntity<PostDTO>(postDTO, headers, HttpStatus.OK);
	}
	
	@GetMapping("/post-by-category/{id}")
	public List<PostDTO> showPostCategoryID(@PathVariable(name = "id") Long id) {
		return postService.getAllPostByCategoryID(id);
	}
}
