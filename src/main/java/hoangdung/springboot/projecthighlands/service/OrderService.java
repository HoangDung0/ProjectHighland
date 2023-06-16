package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Coupon;
import hoangdung.springboot.projecthighlands.model.dao.Order;
import hoangdung.springboot.projecthighlands.model.dao.OrderItem;
import hoangdung.springboot.projecthighlands.model.request.OrderRequestEntity;
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
        List<OrderItem> listOrderItem = orderItemRepository.getOrderItemByOrderID(id);
        //tổng giá tiền khi chưa áp dụng Coupon
        for (OrderItem dao : listOrderItem) {
            totalPrice = totalPrice + dao.getPrice();
        }
        //Kiểm tra xem coupon đã đc user sử dụng trong Order nào khác ch
        List<Order> listOrderUsedCoupon = orderRepository.getOrderByUserIDAndCouponCode(id, entity.getCouponCode());
        if (!listOrderUsedCoupon.isEmpty()) {
            //nếu coupon đã và đang sử dụng trong order khác thì ném lỗi
            throw new Exception();
        } else {
            //tính giảm giá = coupon
            Coupon coupon = couponRepository.findCouponDtosByCouponCodeIsIgnoreCase(entity.getCouponCode());
            //Kiểm tra tổng hóa đơn có đủ để áp dụng coupon ko
            if (coupon.getMinOrderAmount() > totalPrice) {
                throw new Exception();
            } else {
                //Kiểm tra xem coupon giảm theo % hay giảm thẳng
                if (coupon.getDiscountAmount() != 0) {
                    //giảm thẳng thì trừ thẳng vào total
                    return totalPrice - coupon.getDiscountAmount();
                }

                //viết theo dạng toán tử ba ngôi
                if (coupon.getDiscountRate() != 0) {
                    //giảm theo % thì kiểm tra xem số tiền giảm đó có cao hơn số tiền tối đa được giảm ko
                    if (totalPrice * (1 / coupon.getDiscountRate()) > coupon.getDiscountRateCapAmount()) {
                        //nếu số tiền giảm cao hơn số tối đa cho phép thì chỉ giảm số tiền tối đa
                        totalPrice = totalPrice - coupon.getDiscountRateCapAmount();
                    } else {
                        //nếu số tiền giảm thấp hơn số tối đa cho phép thì trừ vào tiền giảm
                        totalPrice = totalPrice - (totalPrice * (1 / coupon.getDiscountRate()));
                    }
                }
            }
        }
        return totalPrice;
    }


    @TranferToResponseEntity
    public Transformable createNewOrder(OrderRequestEntity entity) {
        return orderRepository.save(Order.builder()
                .createdDate(LocalDate.now())
                .lastUpdateDate(LocalDate.now())
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .address3(entity.getAddress3())
                .address4(entity.getAddress4())
                .pickUpStore(entity.getPickUpStore())
                .totalPrice(0)
                .orderStatus(Order.OrderStatus.valueOf(entity.getOrderStatus()))
                .paymentMethod(Order.PaymentMethod.valueOf(entity.getPaymentMethod()))
                .pickUpOption(Order.PickUpOption.valueOf(entity.getPickUpOption()))
                .user(userRepository.findById(entity.getUserID()).orElseThrow()).build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingOrder(String id, OrderRequestEntity entity) {
        Order loadedOrder = orderRepository.findById(id).orElseThrow();

        loadedOrder.setLastUpdateDate(LocalDate.now());
        loadedOrder.setOrderStatus(Order.OrderStatus.valueOf(entity.getOrderStatus()));
        loadedOrder.setPaymentMethod(Order.PaymentMethod.valueOf(entity.getPaymentMethod()));
        loadedOrder.setPickUpOption(Order.PickUpOption.valueOf(entity.getPickUpOption()));
        loadedOrder.setPickUpStore(entity.getPickUpStore());
        loadedOrder.setAddress1(entity.getAddress1());
        loadedOrder.setAddress2(entity.getAddress2());
        loadedOrder.setAddress3(entity.getAddress3());
        loadedOrder.setAddress3(entity.getAddress4());
        loadedOrder.setTotalPrice(calculatePrice(id, entity));

        return orderRepository.save(loadedOrder);
    }

    @TranferToResponseEntity
    public Transformable deleteOrderByID(String id) {
        Order loadedOrder = orderRepository.findById(id).orElseThrow();
        orderRepository.deleteById(id);
        return loadedOrder;
    }

    @TranferToResponseEntity
    public Transformable getOrderByID(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllOrder() {
        return orderRepository.findAll();
    }

}