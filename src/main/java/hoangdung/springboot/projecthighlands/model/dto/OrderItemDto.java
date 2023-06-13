package hoangdung.springboot.projecthighlands.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_orderitem")
public class OrderItemDto implements Tranformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String oderItemID;

    private int quantity;

    private String listToppingJsonString;

    private String sizeJsonString;

    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private OrderDto orderDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private ProductDto productDto;

}
