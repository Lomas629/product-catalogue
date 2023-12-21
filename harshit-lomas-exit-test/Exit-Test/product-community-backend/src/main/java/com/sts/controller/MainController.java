package com.sts.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sts.dto.CommunityStatsDto;
import com.sts.dto.LoginRequestDto;
import com.sts.dto.ProductStatsDto;
import com.sts.dto.SearchRequestDto;
import com.sts.entity.*;
import com.sts.exceptions.DuplicateProductIDException;
import com.sts.exceptions.EmailAlreadyExist;
import com.sts.exceptions.InvalidCredentialsException;
import com.sts.service.ProductService;
import com.sts.service.ReviewSerivce;
import com.sts.service.UserService;
import com.sts.service.UtilService;

@RestController
@CrossOrigin("*")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewSerivce reviewService;
	
	@Autowired
	private UtilService utilService;
	
	@PostMapping("/api/signup")
	public User signUp(@RequestBody @Valid User user) throws EmailAlreadyExist {
		return this.userService.addUser(user);
	}
	
	@PostMapping("/api/login")
	@ResponseBody
	public User login(@RequestBody LoginRequestDto loginRequest) throws InvalidCredentialsException {
		String email=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		return this.userService.findUser(email, password);
	}
	
	@CrossOrigin("*")
	@PostMapping(value= {"/api/addProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public Product addProduct(@RequestPart("product") Product product,
							  @RequestPart("imageFile") MultipartFile[] file ) throws DuplicateProductIDException, IOException {
		Set<ImageModel> images = uploadImage(file);
		product.setProductImages(images);
		return this.productService.addProduct(product);
	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multiPartFiles) throws IOException {
		Set<ImageModel> imageModels = new HashSet<>();
		
		for(MultipartFile file:multiPartFiles) {
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes()
			);
			imageModels.add(imageModel);
		}
		
		return imageModels;
	}
	
	@GetMapping("/api/products")
	@ResponseBody
	public List<Product> getProducts(){
		return this.productService.getAllProducts();
	}
	
	@GetMapping("/api/product/{id}")
	@ResponseBody
	public Product getProductById(@PathVariable("id") Integer id) {
		return this.productService.findProductById(id);
	}
	
	@PostMapping("/api/search")
	@ResponseBody
	public List<Product> getProductsBySearch(@RequestBody SearchRequestDto searchRequest) {
		return this.productService.searchResults(searchRequest);
	}
	
	@PostMapping("/api/addReview")
	@ResponseBody
	public Reviews saveReview(@RequestBody Reviews review) {
		return this.reviewService.addReview(review);
	}
	
	@GetMapping("/api/reviews")
	@ResponseBody
	public List<Reviews> getAllReviews(@RequestParam(name="id") Integer id) {
		List<Reviews> reviews=this.reviewService.getReviews(id);
		for(Reviews review:reviews) System.out.println(review);
	    return this.reviewService.getReviews(id);
	}
	
	
	@GetMapping("/api/approvedReview/{id}")
	@ResponseBody
	public Reviews approveThisReview(@PathVariable("id") Integer id) {
		return this.reviewService.approveReviewById(id);
	}
	
	@GetMapping("/api/products/stats")
	@ResponseBody
	public List<ProductStatsDto> getProductStats() {
		Map<Integer,List<Double>> statsData=this.productService.getProductStats();
		return this.utilService.toProductStatsDtoList(statsData);
	}
	
	@GetMapping("/api/stats")
	@ResponseBody
	public CommunityStatsDto getStats() {
		Integer productCount=productService.getCount();
		Integer reviewCount=reviewService.getCount();
		Integer userCount=userService.getCount();
		
		return this.utilService.toCommunityStatsDto(productCount,reviewCount,userCount);
	}
	
	
}
