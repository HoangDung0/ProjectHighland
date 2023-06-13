package hoangdung.springboot.projecthighlands.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order")
public class OrderDto implements Tranformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PickUpOption pickUpOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private UserDto userDto;

    public enum OrderStatus{
        PLACED, PENDING, COMPLETED, CANCELLED
    }
    public enum PaymentMethod{
        CASH, CARD
    }
    public enum PickUpOption{
        AT_STORE, DELIVERY
    }

}
