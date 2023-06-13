package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.OrderItemDto;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseEntity implements Tranformable {

    private String orderItemID;

    private int quantity;

    private Map<String, Integer> listTopping;

    private Map<String, Integer> size;

    private float price;

    private String orderID;

    private String productID;


    public static OrderItemResponseEntity fromOrderItemDto(OrderItemDto dto){
        return OrderItemResponseEntity.builder()
                .orderItemID(dto.getOderItemID())
                .quantity(dto.getQuantity())
                //.listTopping(dto.getListTopping())
                //.size(dto.getSizeJsonString())
                .price(dto.getPrice())
                .orderID(dto.getOrderDto().getOrderID())
                .productID(dto.getProductDto().getProductID())
                .build();
    }
}
