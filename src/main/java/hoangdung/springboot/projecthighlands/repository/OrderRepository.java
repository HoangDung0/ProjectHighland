package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDto, String > {
    @Query("select dto from OrderDto dto where dto.userDto.userID = ?1 and dto.couponCode = ?2 " +
            "and (dto.orderStatus = 'PLACED' or dto.orderStatus = 'COMPLETED') ")
    List<OrderDto> getOrderByUserIDAndCouponCode(String id, String couponCode);
}
