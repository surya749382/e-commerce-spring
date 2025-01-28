package com.jspider.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.e_commerce.exception.ProductException;
import com.jspider.e_commerce.model.Product;
import com.jspider.e_commerce.model.Rating;
import com.jspider.e_commerce.model.User;
import com.jspider.e_commerce.repository.RatingRepository;
import com.jspider.e_commerce.request.RatingRequest;


@Service
public class RatingServiceImplementation implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;
	
	private ProductService productService;

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
	Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepository.getAllProductsRating(productId);
	}

}
