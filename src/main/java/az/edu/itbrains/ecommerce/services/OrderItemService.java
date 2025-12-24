package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Order;

import java.util.List;

public interface OrderItemService {
    void createOrderItems(Order order, List<Basket> baskets);
}
