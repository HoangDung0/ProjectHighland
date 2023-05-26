package hoangdung.springboot.projecthighlands.model.dto;

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
@Table(name = "tbl_productcatalog")
public class ProductCatalogDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productCatalogID;

    private String productCatalogName;

    private String description;

    private String thumbnailUrl;
}
