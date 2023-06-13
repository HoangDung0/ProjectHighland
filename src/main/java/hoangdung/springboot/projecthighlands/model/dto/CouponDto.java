package hoangdung.springboot.projecthighlands.model.dto;

import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_coupon")
public class CouponDto implements Tranformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String couponID;

    private String couponName;

    private String expirationDate;

    @Column(unique = true)
    private String couponCode;

    private String description;

    private String thumbnailUrl;

    private float discountRate;

    private float discountAmount;

    private float discountRateCapAmount;

    private float minOrderAmount;

}
