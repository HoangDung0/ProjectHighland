package hoangdung.springboot.projecthighlands.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
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
@Table(name = "tbl_product")
public class Product implements Transformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productID;

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private String sizeOptionJsonString;

    private String tagJsonString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCatalogID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private ProductCatalog productCatalog;



}
