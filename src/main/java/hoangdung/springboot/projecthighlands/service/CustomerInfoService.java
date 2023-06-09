package hoangdung.springboot.projecthighlands.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.model.dto.CustomerInfoDto;
import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import hoangdung.springboot.projecthighlands.model.response.CustomerInfoResponseEntity;
import hoangdung.springboot.projecthighlands.repository.CouponRepository;
import hoangdung.springboot.projecthighlands.repository.CustomerInfoRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerInfoService {

    public static CouponRepository couponRepository;

    public static CustomerInfoRepository customerInfoRepository;

    public static UserRepository userRepository;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static List<CouponResponseEntity> convertListUsedCouponIDToListUsedCoupons(String listUsedCoupon) {
        return (List<CouponResponseEntity>) objectMapper.readValue(listUsedCoupon, List.class)
                .stream()
                .map(s -> couponRepository.findById(s.toString()).orElseThrow())
                .toList();
    }

    @SneakyThrows
    public String convertListUsedCouponsToListUsedCouponID(List<CouponResponseEntity> listUsedCoupons) {
        List<String> listUsedCouponID = listUsedCoupons
                .stream()
                .map(CouponResponseEntity::getCouponID)
                .toList();
        return objectMapper.writeValueAsString(listUsedCouponID);
    }


    // RequestEntity -> Dto == save ==>> DB -> Dto -> ResponseEntity
    public CustomerInfoResponseEntity createNewCustomerInfo(CustomerInfoRequestEntity entity){
        CustomerInfoDto preparedCustomerInfo = CustomerInfoDto.builder()
                .point(entity.getPoint())
                .rank(entity.getRank())
                .cardInfo(entity.getCardInfo())
                .userDto(userRepository.findById(entity.getUserID()).orElseThrow())
                .usedCouponJsonString(convertListUsedCouponsToListUsedCouponID(entity.getListUsedCoupons()))
                .build();

        return CustomerInfoResponseEntity.fromCustomerInfoDto(customerInfoRepository.save(preparedCustomerInfo));

    }

    public CustomerInfoResponseEntity updateExistingCustomerInfo(String id, CustomerInfoRequestEntity entity) {
        CustomerInfoDto loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();

        loadedCustomerInfo.setPoint(entity.getPoint());
        loadedCustomerInfo.setRank(entity.getRank());
        loadedCustomerInfo.setCardInfo(entity.getCardInfo());
        loadedCustomerInfo.setUsedCouponJsonString(convertListUsedCouponsToListUsedCouponID(entity.getListUsedCoupons()));

        return CustomerInfoResponseEntity.fromCustomerInfoDto(customerInfoRepository.save(loadedCustomerInfo));
    }


    public CustomerInfoResponseEntity deleteCustomerByID(String id) {
        CustomerInfoDto loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();
        customerInfoRepository.deleteById(id);
        return CustomerInfoResponseEntity.fromCustomerInfoDto(loadedCustomerInfo);
    }

    public CustomerInfoResponseEntity getCustomerByUserID(String userID) {
        return CustomerInfoResponseEntity.fromCustomerInfoDto(customerInfoRepository.getCustomerInfoByUserID(userID));
    }

    public CustomerInfoResponseEntity addUsedCoupon(String usedCouponID, String customerID) {
        CouponResponseEntity entityUsedCoupon = CouponResponseEntity.fromCouponDto(couponRepository.findById(usedCouponID).orElseThrow());
        CustomerInfoDto dtoCustomerInfo = customerInfoRepository.findById(customerID).orElseThrow();

        List<CouponResponseEntity> listUsedCoupon = convertListUsedCouponIDToListUsedCoupons(dtoCustomerInfo.getUsedCouponJsonString());
        listUsedCoupon.add(entityUsedCoupon);
        dtoCustomerInfo.setUsedCouponJsonString(convertListUsedCouponsToListUsedCouponID(listUsedCoupon));

        return CustomerInfoResponseEntity.fromCustomerInfoDto(customerInfoRepository.save(dtoCustomerInfo));
    }

}
