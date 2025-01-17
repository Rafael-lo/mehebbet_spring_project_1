package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services;

import java.util.List;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Cart;

public interface CartService {
	public Cart saveCart(Integer productId, Integer userId);

	public List<Cart> getCartsByUser(Integer userId);
	
	public Integer getCountCart(Integer userId);

	public void updateQuantity(String sy, Integer cid);
}
