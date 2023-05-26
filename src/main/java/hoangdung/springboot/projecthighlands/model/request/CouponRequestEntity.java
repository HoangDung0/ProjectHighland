package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequestEntity {

    private String couponName;

    private String expirationDate;

    private String couponCode;

    private String description;

    private String thumbnailUrl;

    private float discountRate;

    private float discountAmount;

    private float discountRateCapAmount;

    private float minOrderAmount;
}
