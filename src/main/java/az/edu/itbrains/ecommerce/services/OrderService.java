package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.order.OrderCreateDto;

public interface OrderService {
    boolean createNewOrder(String email, OrderCreateDto orderCreateDto);
}
