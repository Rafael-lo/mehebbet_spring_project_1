package com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public Cart findByProductIdAndUserId(Integer productId, Integer userId);
	
	public Integer countByUserId(Integer userId);
	
	public List<Cart> findByUserId(Integer userId);
}
