package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.OrderRequest;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.ProductOrder;

public interface OrderService {

	public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception;

	public List<ProductOrder> getOrdersByUser(Integer userId);

	public ProductOrder updateOrderStatus(Integer id, String status);

	public List<ProductOrder> getAllOrders();

	public ProductOrder getOrdersByOrderId(String orderId);
	
	public Page<ProductOrder> getAllOrdersPagination(Integer pageNo,Integer pageSize);
}