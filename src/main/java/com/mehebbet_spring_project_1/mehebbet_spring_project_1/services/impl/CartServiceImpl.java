package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Cart;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Product;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories.CartRepository;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories.ProductRepository;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories.UserRepository;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.CartService;

public class CartServiceImpl implements CartService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart saveCart(Integer productId, Integer userId) {
		UserDtls userDtls = userRepository.findById(userId).get();
		Product product = productRepository.findById(productId).get();
		
		Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);
		
		Cart cart = null;
		
		if (ObjectUtils.isEmpty(cartStatus)) {
			cart = new Cart();
			cart.setProduct(product);
			cart.setUser(userDtls);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		} else {
			cart = cartStatus;
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		
		Cart saveCart = cartRepository.save(cart);

		return saveCart;
	}

	@Override
	public List<Cart> getCartsByUser(Integer userId) {
		List<Cart> carts = cartRepository.findByUserId(userId);
		
		Double totalOrderPrice = 0.0;
		
		List<Cart> updateCarts = new ArrayList<>();
		
		for (Cart c : carts) {
			Double totalPrice = (c.getProduct().getDiscountPrice() * c.getQuantity());
			c.setTotalPrice(totalPrice);
			totalOrderPrice = totalOrderPrice + totalPrice;
			c.setTotalOrderPrice(totalOrderPrice);
			updateCarts.add(c);
		}
		
		return updateCarts;
	}

	@Override
	public Integer getCountCart(Integer userId) {
		return cartRepository.countByUserId(userId);
	}

	@Override
	public void updateQuantity(String sy, Integer cid) {
	
		Cart cart = cartRepository.findById(cid).get();
		int updateQuantity;
		
		if (sy.equalsIgnoreCase("de")) {
			updateQuantity = cart.getQuantity() - 1; // 5 - 1 = 4
			
			if (updateQuantity <= 0) {
				cartRepository.delete(cart);
			} else {
				cart.setQuantity(updateQuantity);
				cartRepository.save(cart);
			}
		} else {
			updateQuantity = cart.getQuantity() + 1;
			cart.setQuantity(updateQuantity);
			cartRepository.save(cart);
		}
		
	}

}
