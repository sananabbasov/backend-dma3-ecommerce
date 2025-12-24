package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.order.OrderCreateDto;
import az.edu.itbrains.ecommerce.enums.OrderStatus;
import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Order;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.models.User;
import az.edu.itbrains.ecommerce.repositories.OrderRepository;
import az.edu.itbrains.ecommerce.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final ModelMapper modelMapper;

    @Override
    public boolean createNewOrder(String email, OrderCreateDto orderCreateDto) {

        User user = userService.getByEmail(email);

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PENDING);
        order.setUser(user);
        order.setAddress(orderCreateDto.getAddress());
        order.setFullAddress(orderCreateDto.getFullAddress());
        order.setPhone(orderCreateDto.getPhone());
        order.setNote(orderCreateDto.getNote());





        orderRepository.save(order);

        orderItemService.createOrderItems(order, user.getBaskets());

        basketService.removeAllItemsByEmail(user.getEmail());

        return true;
    }
}
