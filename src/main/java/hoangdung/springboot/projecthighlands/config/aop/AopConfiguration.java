package hoangdung.springboot.projecthighlands.config.aop;

import hoangdung.springboot.projecthighlands.model.dao.*;
import hoangdung.springboot.projecthighlands.model.response.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class AopConfiguration {


    @Around("@annotation(hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity)")
    public Tranformable transformFromDtoToResponseEntity(ProceedingJoinPoint joinPoint) throws Throwable {
        var result = joinPoint.proceed();

        if (result instanceof User user) {
            return UserResponseEntity.fromUserDto(user);
        } else if (result instanceof Topping topping) {
            return ToppingResponseEntity.fromToppingDto(topping);
        } else if (result instanceof Tag tag) {
            return TagResponseEntity.fromTagDto(tag);
        } else if (result instanceof Product product) {
            return ProductResponseEntity.fromProductDto(product);
        } else if (result instanceof ProductCatalog productCatalog) {
            return ProductCatalogResponseEntity.fromProductCatalogDto(productCatalog);
        } else if (result instanceof Order order) {
            return OrderResponseEntity.fromOrderDto(order);
        } else if (result instanceof OrderItem orderItem) {
            return OrderItemResponseEntity.fromOrderItemDto(orderItem);
        } else if (result instanceof CustomerInfo customerInfo) {
            return CustomerInfoResponseEntity.fromCustomerInfoDto(customerInfo);
        } else if (result instanceof Addresses addresses) {
            return AddressesResponseEntity.fromAddressesDto(addresses);
        } else if (result instanceof Coupon coupon) {
            return CouponResponseEntity.fromCouponDto(coupon);
        } else {
            throw new RuntimeException("Unknown type");
        }

    }

    @Around("@annotation(hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity)")
    public List<Tranformable> multipleTransformFromDtoToResponseEntity(ProceedingJoinPoint joinPoint) throws Throwable {
        var result = (List<Tranformable>) joinPoint.proceed();

        return result.stream()
                .map(e -> {
                    if (e instanceof Addresses address) {
                        return (Tranformable) AddressesResponseEntity.fromAddressesDto(address);
                    }
                    return null;
                })
                .toList();

    }


}
