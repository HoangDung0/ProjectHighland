package hoangdung.springboot.projecthighlands.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ProductDto {

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
    private ProductCatalogDto productCatalogDto;



}
