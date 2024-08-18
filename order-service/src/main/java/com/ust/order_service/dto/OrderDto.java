package com.ust.order_service.dto;

import com.ust.order_service.entity.Order;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record OrderDto(

        long id,

        @NotNull(message = "Book id is required")
        long bookId,

        @NotNull(message = "Customer id is required")
        long customerId,

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity should be greater than 0")
        int quantity,

        @NotNull(message = "Status is required")
        @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Status can only be PENDING, CONFIRMED or CANCELLED")
        String status
) {

    public Order toOrder(OrderDto dto) {
        return new Order(
                dto.bookId,
                dto.customerId,
                dto.quantity,
                dto.status
        );
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getBookId(),
                order.getCustomerId(),
                order.getQuantity(),
                order.getStatus()
        );
    }
}
