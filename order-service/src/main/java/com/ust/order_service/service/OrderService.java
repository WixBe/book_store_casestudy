package com.ust.order_service.service;

import com.ust.order_service.dto.Book;
import com.ust.order_service.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(long id);

    Order createOrder(Order order);

    Order updateOrder(long id, Order order);

    void deleteOrder(long id);

    int getStockByBookId(long bookId);
}
