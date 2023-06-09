package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestEntity {

    private int quantity;

    // Topping ID , Quantity
    private Map<String, Integer> listTopping;

    //Size, price
    private Map<String, Integer> size;

    private float price;

    private String orderID;

    private String productID;
}
