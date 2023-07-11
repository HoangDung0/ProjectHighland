package hoangdung.springboot.projecthighlands.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.CustomerInfo;
import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.CouponResponseEntity;
import hoangdung.springboot.projecthighlands.model.response.CustomerInfoResponseEntity;
import hoangdung.springboot.projecthighlands.repository.CouponRepository;
import hoangdung.springboot.projecthighlands.repository.CustomerInfoRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CustomerInfoService {

    public final CouponRepository couponRepository;

    public final CustomerInfoRepository customerInfoRepository;

    public final UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

//    @SneakyThrows
//    public static List<CouponResponseEntity> convertListUsedCouponIDToListUsedCoupons(String listUsedCoupon) {
//        return (List<CouponResponseEntity>) objectMapper.readValue(listUsedCoupon, List.class)
//                .stream()
//                .map(s -> couponRepository.findById(s.toString()).orElseThrow())
//                .toList();
//    }
//
//    @SneakyThrows
//    public String convertListUsedCouponsToListUsedCouponID(List<CouponResponseEntity> listUsedCoupons) {
//        List<String> listUsedCouponID = listUsedCoupons
//                .stream()
//                .map(CouponResponseEntity::getId)
//                .toList();
//        return objectMapper.writeValueAsString(listUsedCouponID);
//    }

    @SneakyThrows
    private Transformable mapFromCustomerInfoToResponseEntity(CustomerInfo persistedCustomerInfo) {
        var mappedResponse = CustomerInfoResponseEntity.fromCustomerInfo(persistedCustomerInfo);

        var usedCouponJson = objectMapper.readTree(persistedCustomerInfo.getUsedCouponJsonString());

        var responseUsedCoupons = StreamSupport.stream(usedCouponJson.spliterator(), false)
                .map(s -> couponRepository.findById(s.asText("")).orElse(null))
                .map(CouponResponseEntity::fromCoupon)
                .toList();

        mappedResponse.setListUsedCoupons(responseUsedCoupons);

        return mappedResponse;
    }


    public Transformable createNewCustomerInfo(CustomerInfoRequestEntity entity){
        CustomerInfo customerInfo =  CustomerInfo.builder()
                .point(entity.getPoint())
                .rank(entity.getRank())
                .cardInfo(entity.getCardInfo())
                .user(userRepository.findById(entity.getUserID()).orElseThrow())
                .build();

        return mapFromCustomerInfoToResponseEntity(customerInfoRepository.save(customerInfo));
    }


    public Transformable updateExistingCustomerInfo(String id, CustomerInfoRequestEntity entity) {
        CustomerInfo loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();

        loadedCustomerInfo.setPoint(entity.getPoint());
        loadedCustomerInfo.setRank(entity.getRank());
        loadedCustomerInfo.setCardInfo(entity.getCardInfo());

        return mapFromCustomerInfoToResponseEntity(customerInfoRepository.save(loadedCustomerInfo));
    }

    public Transformable deleteCustomerByID(String id) {
        CustomerInfo loadedCustomerInfo = customerInfoRepository.findById(id).orElseThrow();
        customerInfoRepository.deleteById(id);
        return mapFromCustomerInfoToResponseEntity(loadedCustomerInfo);
    }

    public Transformable getCustomerByUserID(String userID) {
        return mapFromCustomerInfoToResponseEntity(customerInfoRepository.getCustomerInfoByUserID(userID));
    }

    public Transformable getCustomerByID(String id){
        return mapFromCustomerInfoToResponseEntity(customerInfoRepository.findById(id).orElseThrow());
    }


    @SneakyThrows
    public Transformable addUsedCoupon(String usedCouponID, String customerID) {
        CustomerInfo dtoCustomerInfo = customerInfoRepository.findById(customerID).orElseThrow();

        couponRepository.findById(usedCouponID).orElseThrow();

        var usedCouponJson = ofNullable(dtoCustomerInfo.getUsedCouponJsonString()).orElse("[]");

        var usedCoupons = (ArrayNode) objectMapper.readTree(usedCouponJson);
        usedCoupons.add(usedCoupons);

        dtoCustomerInfo.setUsedCouponJsonString(usedCoupons.toString());

        return mapFromCustomerInfoToResponseEntity(customerInfoRepository.save(dtoCustomerInfo));
    }

}
