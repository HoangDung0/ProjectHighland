package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Coupon;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseEntity implements Transformable {

    private String couponID;

    private String couponName;

    private String expirationDate;

    private String couponCode;

    private String description;

    private String thumbnailUrl;

    private float discountRate;

    private float discountAmount;

    private float discountRateCapAmount;

    private float minOrderAmount;


    public static CouponResponseEntity fromCoupon(Coupon dao) {
        return CouponResponseEntity.builder()
                .couponID(dao.getCouponID())
                .couponName(dao.getCouponName())
                .expirationDate(dao.getExpirationDate())
                .couponCode(dao.getCouponCode())
                .description(dao.getDescription())
                .thumbnailUrl(dao.getThumbnailUrl())
                .discountRate(dao.getDiscountRate())
                .discountAmount(dao.getDiscountAmount())
                .discountRateCapAmount(dao.getDiscountRateCapAmount())
                .minOrderAmount(dao.getMinOrderAmount())
                .build();
    }

}
