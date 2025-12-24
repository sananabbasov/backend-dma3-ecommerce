package az.edu.itbrains.ecommerce.dtos.basket;

import az.edu.itbrains.ecommerce.dtos.product.ProductBasketDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketCheckoutDto {
    private Long id;
    private int quantity;
    private ProductBasketDto product;
}
