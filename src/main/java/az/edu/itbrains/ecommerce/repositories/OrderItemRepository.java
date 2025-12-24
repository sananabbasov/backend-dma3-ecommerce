package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
