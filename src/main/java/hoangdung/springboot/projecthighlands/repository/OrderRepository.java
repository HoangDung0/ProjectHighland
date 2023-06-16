package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String > {
    @Query("select order from Order order where order.user.userID = ?1 and order.couponCode = ?2 " +
            "and (order.orderStatus = 'PLACED' or order.orderStatus = 'COMPLETED') ")
    List<Order> getOrderByUserIDAndCouponCode(String id, String couponCode);
}
