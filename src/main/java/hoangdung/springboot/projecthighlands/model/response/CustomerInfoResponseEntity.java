package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.CustomerInfoDto;
import hoangdung.springboot.projecthighlands.service.CustomerInfoService;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponseEntity implements Tranformable {

    private String customerID;

    private int point;

    private int rank;

    private List<CouponResponseEntity> listUsedCoupons;

    private String cardInfo;

    private String userID;

    public static CustomerInfoResponseEntity fromCustomerInfoDto(CustomerInfoDto dto)  {
        List<CouponResponseEntity> listUsedCoupons =
                CustomerInfoService.convertListUsedCouponIDToListUsedCoupons(dto.getUsedCouponJsonString());
        return CustomerInfoResponseEntity.builder()
                .customerID(dto.getCustomerID())
                .point(dto.getPoint())
                .rank(dto.getRank())
                .cardInfo(dto.getCardInfo())
                .listUsedCoupons(listUsedCoupons)
                .userID(dto.getUserDto().getUserID())
                .build();
    }
}
