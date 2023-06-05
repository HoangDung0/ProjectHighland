package hoangdung.springboot.projecthighlands.model.request;

import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoRequestEntity {

    private int point;

    private int rank;

    private List<CouponResponseEntity> listUsedCoupons;

    private String cardInfo;

    private String userID;
}
