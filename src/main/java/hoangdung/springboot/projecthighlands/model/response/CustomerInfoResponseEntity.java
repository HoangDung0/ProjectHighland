package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.CustomerInfo;
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

    public static CustomerInfoResponseEntity fromCustomerInfo(CustomerInfo dao)  {
        List<CouponResponseEntity> listUsedCoupons =
                CustomerInfoService.convertListUsedCouponIDToListUsedCoupons(dao.getUsedCouponJsonString());
        return CustomerInfoResponseEntity.builder()
                .customerID(dao.getCustomerID())
                .point(dao.getPoint())
                .rank(dao.getRank())
                .cardInfo(dao.getCardInfo())
                .listUsedCoupons(listUsedCoupons)
                .userID(dao.getUser().getUserID())
                .build();
    }
}
