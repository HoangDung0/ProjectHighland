package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dao.Coupon;
import hoangdung.springboot.projecthighlands.model.request.CouponRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import hoangdung.springboot.projecthighlands.repository.CouponRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    @TranferToResponseEntity
    public Transformable getCouponByCouponCode(String code) {
        return couponRepository.findCouponDtosByCouponCodeIsIgnoreCase(code);
    }

    public List<CouponResponseEntity> searchCouponByCouponName(String name) {
        return couponRepository.findCouponsByCouponNameContainingIgnoreCase(name).stream()
                .map(CouponResponseEntity::fromCoupon)
                .toList();
    }


    // RequestEntity -> Dto == save ==>> DB -> Dto -> ResponseEntity
    @TranferToResponseEntity
    public Transformable createNewCoupon(CouponRequestEntity entity) {
        return couponRepository.save(Coupon.builder()
                .couponName(entity.getCouponName())
                .expirationDate(entity.getExpirationDate())
                .couponCode(entity.getCouponCode())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .discountRate(entity.getDiscountRate())
                .discountAmount(entity.getDiscountAmount())
                .discountRateCapAmount(entity.getDiscountRateCapAmount())
                .minOrderAmount(entity.getMinOrderAmount())
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingCoupon(String id, CouponRequestEntity entity) {
        Coupon loadedCoupon = couponRepository.findById(id).orElseThrow();

        loadedCoupon.setCouponName(entity.getCouponName());
        loadedCoupon.setExpirationDate(entity.getExpirationDate());
        loadedCoupon.setCouponCode(entity.getCouponCode());
        loadedCoupon.setDescription(entity.getDescription());
        loadedCoupon.setThumbnailUrl(entity.getThumbnailUrl());
        loadedCoupon.setDiscountRate(entity.getDiscountRate());
        loadedCoupon.setDiscountAmount(entity.getDiscountAmount());
        loadedCoupon.setDiscountRateCapAmount(entity.getDiscountAmount());
        loadedCoupon.setMinOrderAmount(entity.getMinOrderAmount());

        return couponRepository.save(loadedCoupon);
    }

    @TranferToResponseEntity
    public Transformable deleteCouponByID(String id) {
        Coupon loadedCoupon = couponRepository.findById(id).orElseThrow();
        couponRepository.deleteById(id);
        return loadedCoupon;
    }
}
