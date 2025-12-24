package az.edu.itbrains.ecommerce.dtos.user;


import az.edu.itbrains.ecommerce.dtos.basket.BasketCheckoutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCheckoutDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;

    private List<BasketCheckoutDto> baskets = new ArrayList<>();

}
