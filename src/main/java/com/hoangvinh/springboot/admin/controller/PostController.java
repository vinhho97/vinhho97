package com.hoangvinh.springboot.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoangvinh.springboot.admin.model.PostDTO;
import com.hoangvinh.springboot.admin.service.CategoryService;
import com.hoangvinh.springboot.admin.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;

	private String UPLOAD_DIR = "D:\\project\\src\\main\\resources\\templates\\admin\\web\\post\\uploads";

	@GetMapping("/admin/list-post")
	public String list(Model model) {
		model.addAttribute("listPost", postService.getAllPost());
		return "admin/web/post/listPost";
	}

	@GetMapping("/admin/add-post")
	public String add(Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		model.addAttribute("addPostForm", new PostDTO());
		return "admin/web/post/addPost";
	}

	@PostMapping("/admin/add-post")
	public String add(@ModelAttribute(name = "addPostForm") PostDTO postDTO, @RequestParam("file") MultipartFile inputFile) throws IllegalStateException, IOException {
		HttpHeaders headers = new HttpHeaders();
		String originalFilename = inputFile.getOriginalFilename();
		File destinationFile = new File(UPLOAD_DIR + File.separator + originalFilename);
		if (!destinationFile.exists()) {
			destinationFile.createNewFile();
			inputFile.transferTo(destinationFile);
			postDTO.setImageURL(originalFilename);

			postService.addPost(postDTO);
		}

		headers.add("File Uploaded Successfully - ", inputFile.getName());
		return "redirect:list-post";
	}

	@GetMapping("/post-image/{id}")
	public ResponseEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {
		PostDTO postDTO = postService.getPostByID(id);
		File file2upload = new File(UPLOAD_DIR + "\\" + postDTO.getImageURL());
		InputStream inputImage = new FileInputStream(file2upload);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[512];
		int l = inputImage.read(buffer);
		while (l >= 0) {
			outputStream.write(buffer, 0, l);
			l = inputImage.read(buffer);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "image/jpeg");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		inputImage.close();
		return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);
	}

	@GetMapping("/admin/update-post/{id}")
	public String update(Model model, @PathVariable(name="id") Long id) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		model.addAttribute("updatePostForm", postService.getPostByID(id));
		return "admin/web/post/updatePost";
	}

	@PostMapping("/admin/update-post")
	public void update(@ModelAttribute(name = "updatePostForm") PostDTO postDTO, @RequestParam("file") MultipartFile inputFile, HttpServletResponse response) throws IllegalStateException, IOException {
		HttpHeaders headers = new HttpHeaders();
		String originalFilename = inputFile.getOriginalFilename();
		File destinationFile = new File(UPLOAD_DIR + File.separator + originalFilename);
		if (!destinationFile.exists()) {
			destinationFile.createNewFile();
			inputFile.transferTo(destinationFile);
			postDTO.setImageURL(originalFilename);

			postService.updatePost(postDTO);
		}

		headers.add("File Uploaded Successfully - ", inputFile.getName());
		response.sendRedirect("/admin/list-post");
	}
	
	@GetMapping("/admin/delete-post/{id}")
	private void delete(@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException{
		postService.deletePost(id);
		response.sendRedirect("/admin/list-post");
	}
}
