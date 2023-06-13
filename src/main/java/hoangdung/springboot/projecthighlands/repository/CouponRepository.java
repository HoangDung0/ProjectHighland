package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.CouponDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<CouponDto, String> {

    CouponDto findCouponDtosByCouponCodeIsIgnoreCase(String code);

    List<CouponDto> findCouponsByCouponNameContainingIgnoreCase(String tagName);
}

