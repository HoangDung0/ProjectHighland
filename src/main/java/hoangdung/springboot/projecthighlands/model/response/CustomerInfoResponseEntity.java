package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.CustomerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponseEntity implements Transformable {

    private String id;

    private int point;

    private int rank;

    private List<CouponResponseEntity> listUsedCoupons;

    private String cardInfo;

    private String userID;

    public static CustomerInfoResponseEntity fromCustomerInfo(CustomerInfo dao)  {
        return CustomerInfoResponseEntity.builder()
                .id(dao.getId())
                .point(dao.getPoint())
                .rank(dao.getRank())
                .cardInfo(dao.getCardInfo())
                .userID(dao.getUser().getId())
                .build();
    }

}
