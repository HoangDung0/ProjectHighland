package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseEntity {

    private String orderID;

    private LocalDate createdDate;

    private LocalDate lastUpdateDate;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String pickUpStore;

    private String couponCode;

    private float totalPrice;

    private String orderStatus;

    private String paymentMethod;

    private String pickUpOption;

    private String userID;


    public static OrderResponseEntity fromOrderDto(OrderDto dto) {
        return OrderResponseEntity.builder()
                .orderID(dto.getOrderID())
                .createdDate(dto.getCreatedDate())
                .lastUpdateDate(dto.getLastUpdateDate())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .address3(dto.getAddress3())
                .address4(dto.getAddress4())
                .pickUpStore(dto.getPickUpStore())
                .couponCode(dto.getCouponCode())
                .totalPrice(dto.getTotalPrice())
                .orderStatus(dto.getOrderStatus().toString())
                .paymentMethod(dto.getPaymentMethod().toString())
                .pickUpOption(dto.getPickUpOption().toString())
                .userID(dto.getUserDto().getUserID())
                .build();
    }
}
