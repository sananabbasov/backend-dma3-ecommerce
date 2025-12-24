package az.edu.itbrains.ecommerce.models;


import az.edu.itbrains.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus orderStatus;
    private String address;
    private String phone;
    private String fullAddress;
    private String note;

    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
