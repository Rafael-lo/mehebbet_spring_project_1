package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.impl;

import java.util.List;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.Cart;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.OrderRequest;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.ProductOrder;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CartRepository cartRepository;


	@Override
	public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception {
		List<Cart> carts = cartRepository.findByUserId(userid);


	}

	@Override
	public List<ProductOrder> getOrdersByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrder updateOrderStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrder> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrder getOrdersByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductOrder> getAllOrdersPagination(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
