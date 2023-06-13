package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.CouponDto;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponseEntity implements Tranformable {

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


    public static CouponResponseEntity fromCouponDto(CouponDto dto) {
        return CouponResponseEntity.builder()
                .couponID(dto.getCouponID())
                .couponName(dto.getCouponName())
                .expirationDate(dto.getExpirationDate())
                .couponCode(dto.getCouponCode())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .discountRate(dto.getDiscountRate())
                .discountAmount(dto.getDiscountAmount())
                .discountRateCapAmount(dto.getDiscountRateCapAmount())
                .minOrderAmount(dto.getMinOrderAmount())
                .build();
    }

}
