package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemDto, String> {

    @Query("select dto from OrderItemDto dto where dto.orderDto.orderID = ?1")
    List<OrderItemDto> getOrderItemByOrderID(String id);

}
