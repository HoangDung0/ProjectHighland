package hoangdung.springboot.projecthighlands.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_orderitem")
public class OrderItem implements Transformable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "orderItemID")
    private String id;

    private int quantity;

    private String listToppingJsonString;

    private String sizeJsonString;

    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Product product;

}
