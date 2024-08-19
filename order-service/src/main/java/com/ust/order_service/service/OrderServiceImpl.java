package com.ust.order_service.service;

import com.ust.order_service.client.BookClient;
import com.ust.order_service.dto.Book;
import com.ust.order_service.entity.Order;
import com.ust.order_service.exception.OrderNotFoundException;
import com.ust.order_service.exception.OutOfStockException;
import com.ust.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookClient bookClient;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id: "+id+" not found"));
    }

    @Override
    public Order createOrder(Order order) {
        Book book = bookClient.getBookById(order.getBookId());
        if (book.stock() > order.getQuantity() && order.getStatus().equals("CONFIRMED")) {
            bookClient.updateBook(book.id(), new Book(
                    book.id(),
                    book.title(),
                    book.author(),
                    book.price(),
                    book.stock() - order.getQuantity()
            ));
            return orderRepository.saveAndFlush(order);
        } else {
            throw new OutOfStockException("Book with id: "+order.getBookId()+" is out of stock");
        }
    }

    @Override
    public Order updateOrder(long id, Order order) {
        if(orderRepository.findById(id).isPresent()) {
            order.setId(id);
            Order existingOrder = orderRepository.findById(id).get();
            if (order.getStatus().equals("CANCELLED") && existingOrder.getStatus().equals("CONFIRMED")) {
                Book book = bookClient.getBookById(id);
                bookClient.updateBook(id,
                         new Book(
                                 id,
                                 book.title(),
                                 book.author(),
                                 book.price(),
                                 book.stock() + order.getQuantity()
                         ));
            }
            return orderRepository.saveAndFlush(order);
        } else {
            throw new OrderNotFoundException("Order with id: "+id+" not found");
        }
    }

    @Override
    public void deleteOrder(long id) {
        if(orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order with id: "+id+" not found");
        }
    }

    @Override
    public int getStockByBookId(long bookId) {
        return bookClient.getStockFromBookId(bookId);
    }
}
