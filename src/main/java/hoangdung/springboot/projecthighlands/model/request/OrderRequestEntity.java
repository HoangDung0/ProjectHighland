package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestEntity {

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

}
