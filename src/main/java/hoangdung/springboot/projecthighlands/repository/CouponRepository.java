package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.CouponDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<CouponDto, String> {

    @Query("select dto from CouponDto dto where dto.couponCode = ?1")
    CouponDto getCouponByCouponCode(String code);

    List<CouponDto> findCouponsByCouponNameContainingIgnoreCase(String tagName);
}

