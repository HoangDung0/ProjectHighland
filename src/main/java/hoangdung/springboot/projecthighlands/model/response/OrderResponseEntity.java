package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Order;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseEntity implements Transformable {

    private String id;

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


    public static OrderResponseEntity fromOrder(Order dao) {
        return OrderResponseEntity.builder()
                .id(dao.getId())
                .createdDate(dao.getCreatedDate())
                .lastUpdateDate(dao.getLastUpdateDate())
                .address1(dao.getAddress1())
                .address2(dao.getAddress2())
                .address3(dao.getAddress3())
                .address4(dao.getAddress4())
                .pickUpStore(dao.getPickUpStore())
                .couponCode(dao.getCouponCode())
                .totalPrice(dao.getTotalPrice())
                .orderStatus(dao.getOrderStatus().toString())
                .paymentMethod(dao.getPaymentMethod().toString())
                .pickUpOption(dao.getPickUpOption().toString())
                .userID(dao.getUser().getId())
                .build();
    }
}
