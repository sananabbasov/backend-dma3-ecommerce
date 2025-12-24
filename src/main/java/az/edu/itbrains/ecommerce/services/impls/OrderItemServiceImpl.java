package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Order;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.repositories.OrderItemRepository;
import az.edu.itbrains.ecommerce.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public void createOrderItems(Order order, List<Basket> baskets) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (Basket basket : baskets) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(basket.getProduct());
            orderItem.setQuantity(basket.getQuantity());
            orderItem.setPrice(basket.getProduct().getPrice());
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderItemRepository.saveAll(orderItems);
    }
}
