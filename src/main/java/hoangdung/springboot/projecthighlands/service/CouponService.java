package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.CouponDto;
import hoangdung.springboot.projecthighlands.model.request.CouponRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import hoangdung.springboot.projecthighlands.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    public CouponResponseEntity getCouponByCouponCode(String code) {
        return CouponResponseEntity.fromCouponDto(couponRepository.getCouponByCouponCode(code));
    }

    public List<CouponResponseEntity> searchCouponByCouponName(String name) {
        List<CouponDto> listCouponDto = couponRepository.findCouponsByCouponNameContainingIgnoreCase(name);

        // đổi thành stream api và optional pattern
        List<CouponResponseEntity> listCouponsReponse = new ArrayList<>();
        for (CouponDto dto : listCouponDto ) {
            listCouponsReponse.add( CouponResponseEntity.fromCouponDto(dto));
        }

        return listCouponsReponse;

    }


    public CouponResponseEntity createNewCoupon(CouponRequestEntity entity) {
        CouponDto preparedCoupon = CouponDto.builder()
                .couponName(entity.getCouponName())
                .expirationDate(entity.getExpirationDate())
                .couponCode(entity.getCouponCode())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .discountRate(entity.getDiscountRate())
                .discountAmount(entity.getDiscountAmount())
                .discountRateCapAmount(entity.getDiscountRateCapAmount())
                .minOrderAmount(entity.getMinOrderAmount())
                .build();

        return CouponResponseEntity.fromCouponDto(couponRepository.save(preparedCoupon));

    }

    public CouponResponseEntity updateExistingCoupon(String id, CouponRequestEntity entity){
        CouponDto loadedCoupon = couponRepository.findById(id).orElseThrow();

        loadedCoupon.setCouponName(entity.getCouponName());
        loadedCoupon.setExpirationDate(entity.getExpirationDate());
        loadedCoupon.setCouponCode(entity.getCouponCode());
        loadedCoupon.setDescription(entity.getDescription());
        loadedCoupon.setThumbnailUrl(entity.getThumbnailUrl());
        loadedCoupon.setDiscountRate(entity.getDiscountRate());
        loadedCoupon.setDiscountAmount(entity.getDiscountAmount());
        loadedCoupon.setDiscountRateCapAmount(entity.getDiscountAmount());
        loadedCoupon.setMinOrderAmount(entity.getMinOrderAmount());

        return CouponResponseEntity.fromCouponDto(couponRepository.save(loadedCoupon));
    }


    public CouponResponseEntity deleteCouponByID(String id) {
        CouponDto loadedCoupon = couponRepository.findById(id).orElseThrow();
        couponRepository.deleteById(id);
        return CouponResponseEntity.fromCouponDto(loadedCoupon);
    }
}
