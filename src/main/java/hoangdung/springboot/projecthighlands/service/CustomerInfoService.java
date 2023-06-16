package hoangdung.springboot.projecthighlands.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.model.dao.CustomerInfo;
import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import hoangdung.springboot.projecthighlands.repository.CouponRepository;
import hoangdung.springboot.projecthighlands.repository.CustomerInfoRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
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
    @TranferToResponseEntity
    public Transformable createNewCustomerInfo(CustomerInfoRequestEntity entity){
        return customerInfoRepository.save(CustomerInfo.builder()
                .point(entity.getPoint())
                .rank(entity.getRank())
                .cardInfo(entity.getCardInfo())
                .user(userRepository.findById(entity.getUserID()).orElseThrow())
                .usedCouponJsonString(convertListUsedCouponsToListUsedCouponID(entity.getListUsedCoupons()))
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingCustomerInfo(String id, CustomerInfoRequestEntity entity) {
        CustomerInfo loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();

        loadedCustomerInfo.setPoint(entity.getPoint());
        loadedCustomerInfo.setRank(entity.getRank());
        loadedCustomerInfo.setCardInfo(entity.getCardInfo());
        loadedCustomerInfo.setUsedCouponJsonString(convertListUsedCouponsToListUsedCouponID(entity.getListUsedCoupons()));

        return customerInfoRepository.save(loadedCustomerInfo);
    }

    @TranferToResponseEntity
    public Transformable deleteCustomerByID(String id) {
        CustomerInfo loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();
        customerInfoRepository.deleteById(id);
        return loadedCustomerInfo;
    }
    @TranferToResponseEntity
    public Transformable getCustomerByUserID(String userID) {
        return customerInfoRepository.getCustomerInfoByUserID(userID);
    }

    @TranferToResponseEntity
    public Transformable getCustomerByID(String id){
        return customerInfoRepository.findById(id).orElseThrow();
    }

    @TranferToResponseEntity
    public Transformable addUsedCoupon(String usedCouponID, String customerID) {
        CouponResponseEntity entityUsedCoupon = CouponResponseEntity.fromCoupon(couponRepository.findById(usedCouponID).orElseThrow());
        CustomerInfo dtoCustomerInfo = customerInfoRepository.findById(customerID).orElseThrow();

        List<CouponResponseEntity> listUsedCoupon = convertListUsedCouponIDToListUsedCoupons(dtoCustomerInfo.getUsedCouponJsonString());
        listUsedCoupon.add(entityUsedCoupon);
        dtoCustomerInfo.setUsedCouponJsonString(convertListUsedCouponsToListUsedCouponID(listUsedCoupon));

        return customerInfoRepository.save(dtoCustomerInfo);
    }

}
