package hoangdung.springboot.projecthighlands.service;

import com.sun.jdi.request.DuplicateRequestException;
import hoangdung.springboot.projecthighlands.model.dto.CouponDto;
import hoangdung.springboot.projecthighlands.model.dto.OrderDto;
import hoangdung.springboot.projecthighlands.model.dto.OrderItemDto;
import hoangdung.springboot.projecthighlands.model.request.OrderRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.OrderResponseEntity;
import hoangdung.springboot.projecthighlands.repository.OrderItemRepository;
import hoangdung.springboot.projecthighlands.repository.OrderRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static hoangdung.springboot.projecthighlands.service.CustomerInfoService.couponRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    public static OrderRepository orderRepository;

    public static OrderItemRepository orderItemRepository;

    public static UserRepository userRepository;

    @SneakyThrows
    public float calculatePrice(String id, OrderRequestEntity entity) {
        float totalPrice = 0;
        List<OrderItemDto> listOrderItem = orderItemRepository.getOrderItemByOrderID(id);
        //tổng giá tiền khi chưa áp dụng Coupon
        for (OrderItemDto dto : listOrderItem) {
            totalPrice = totalPrice + dto.getPrice();
        }
        //Kiểm tra xem coupon đã đc user sử dụng trong Order nào khác ch
        List<OrderDto> listOrderUsedCoupon = orderRepository.getOrderByUserIDAndCouponCode(id, entity.getCouponCode());
        if(!listOrderUsedCoupon.isEmpty()){
            //nếu coupon đã và đang sử dụng trong order khác thì ném lỗi
            throw new DuplicateRequestException();
        }else{
            //tính giảm giá = coupon
            CouponDto couponDto = couponRepository.getCouponByCouponCode(entity.getCouponCode());
            //Kiểm tra tổng hóa đơn có đủ để áp dụng coupon ko
            if(couponDto.getMinOrderAmount() > totalPrice){
                throw new Exception();
            }else {
                //Kiểm tra xem coupon giảm theo % hay giảm thẳng
                if(couponDto.getDiscountAmount() != 0){
                    //giảm thẳng thì trừ thẳng vào total
                    totalPrice = totalPrice - couponDto.getDiscountAmount();
                }else if(couponDto.getDiscountRate() != 0){
                    //giảm theo % thì kiểm tra xem số tiền giảm đó có cao hơn số tiền tối đa được giảm ko
                    if(totalPrice * (1 / couponDto.getDiscountRate()) > couponDto.getDiscountRateCapAmount()){
                        //nếu số tiền giảm cao hơn số tối đa cho phép thì chỉ giảm số tiền tối đa
                        totalPrice = totalPrice - couponDto.getDiscountRateCapAmount();
                    }else{
                        //nếu số tiền giảm thấp hơn số tối đa cho phép thì trừ vào tiền giảm
                        totalPrice = totalPrice - (totalPrice * (1 / couponDto.getDiscountRate()));
                    }
                }
            }
        }
        return totalPrice;
    }



    public OrderResponseEntity createNewOrder(OrderRequestEntity entity) {
        OrderDto preparedOrder = OrderDto.builder()
                .createdDate(LocalDate.now())
                .lastUpdateDate(LocalDate.now())
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .address3(entity.getAddress3())
                .address4(entity.getAddress4())
                .pickUpStore(entity.getPickUpStore())
                .totalPrice(0)
                .orderStatus(OrderDto.OrderStatus.valueOf(entity.getOrderStatus()))
                .paymentMethod(OrderDto.PaymentMethod.valueOf(entity.getPaymentMethod()))
                .pickUpOption(OrderDto.PickUpOption.valueOf(entity.getPickUpOption()))
                .userDto(userRepository.findById(entity.getUserID()).orElseThrow())
                .build();
        return OrderResponseEntity.fromOrderDto(orderRepository.save(preparedOrder));

    }

    public OrderResponseEntity updateExistingOrder(String id, OrderRequestEntity entity) {
        OrderDto loadedOrder = orderRepository.findById(id).orElseThrow();

        loadedOrder.setLastUpdateDate(LocalDate.now());
        loadedOrder.setOrderStatus(OrderDto.OrderStatus.valueOf(entity.getOrderStatus()));
        loadedOrder.setPaymentMethod(OrderDto.PaymentMethod.valueOf(entity.getPaymentMethod()));
        loadedOrder.setPickUpOption(OrderDto.PickUpOption.valueOf(entity.getPickUpOption()));
        loadedOrder.setPickUpStore(entity.getPickUpStore());
        loadedOrder.setAddress1(entity.getAddress1());
        loadedOrder.setAddress2(entity.getAddress2());
        loadedOrder.setAddress3(entity.getAddress3());
        loadedOrder.setAddress3(entity.getAddress4());
        loadedOrder.setTotalPrice(calculatePrice(id, entity));

        return OrderResponseEntity.fromOrderDto(orderRepository.save(loadedOrder));
    }


    public OrderResponseEntity deleteOrderByID(String id) {
        OrderDto loadedOrder = orderRepository.findById(id).orElseThrow();
        orderRepository.deleteById(id);
        return OrderResponseEntity.fromOrderDto(loadedOrder);
    }


//    public List<OrderItemResponseEntity> getOrderItemByOrderID(String id) {
//        return orderItemRepository.getOrderItemByOrderID(id)
//                .stream()
//                .map(OrderItemResponseEntity::fromOrderItemDto)
//                .toList();
//    }

    public OrderResponseEntity getOrderByID(String id) {
        return OrderResponseEntity.fromOrderDto(orderRepository.findById(id).orElseThrow());
    }

}