package com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Category;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

	List<ProductOrder> findByUserId(Integer userId);
	
	ProductOrder findByOrderId(String orderId);
	
}
