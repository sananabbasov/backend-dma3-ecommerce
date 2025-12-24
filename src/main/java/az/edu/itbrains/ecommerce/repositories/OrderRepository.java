package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
