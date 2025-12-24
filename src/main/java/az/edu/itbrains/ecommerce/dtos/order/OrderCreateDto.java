package az.edu.itbrains.ecommerce.dtos.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    private String address;
    private String phone;
    private String fullAddress;
    private String note;
}
