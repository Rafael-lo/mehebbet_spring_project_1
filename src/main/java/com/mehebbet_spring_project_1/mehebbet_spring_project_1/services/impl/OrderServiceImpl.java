package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.OrderRequest;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.ProductOrder;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception {
		// TODO Auto-generated method stub
		
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
