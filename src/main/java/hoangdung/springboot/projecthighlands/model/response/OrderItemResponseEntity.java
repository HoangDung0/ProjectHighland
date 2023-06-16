package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.OrderItem;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseEntity implements Transformable {

    private String orderItemID;

    private int quantity;

    private Map<String, Integer> listTopping;

    private Map<String, Integer> size;

    private float price;

    private String orderID;

    private String productID;


    public static OrderItemResponseEntity fromOrderItem(OrderItem dao){
        return OrderItemResponseEntity.builder()
                .orderItemID(dao.getOderItemID())
                .quantity(dao.getQuantity())
                //.listTopping(dao.getListTopping())
                //.size(dao.getSizeJsonString())
                .price(dao.getPrice())
                .orderID(dao.getOrder().getOrderID())
                .productID(dao.getProduct().getProductID())
                .build();
    }
}
