package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    @Query("select orderItem from OrderItem orderItem where orderItem.order.id = ?1")
    List<OrderItem> getOrderItemByOrderID(String id);

}
