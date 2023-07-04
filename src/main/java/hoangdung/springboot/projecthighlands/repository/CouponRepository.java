package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {

    Coupon findCouponByCouponCodeIsIgnoreCase(String code);

    List<Coupon> findCouponsByCouponNameContainingIgnoreCase(String tagName);
}

