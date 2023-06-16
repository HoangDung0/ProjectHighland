package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.ProductCatalog;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCatalogResponseEntity implements Transformable {

    private String productCatalogID;

    private String productCatalogName;

    private String description;

    private String thumbnailUrl;

    public static ProductCatalogResponseEntity fromProductCatalog(ProductCatalog dao) {
        return  ProductCatalogResponseEntity.builder()
                .productCatalogID(dao.getProductCatalogID())
                .productCatalogName(dao.getProductCatalogName())
                .description(dao.getDescription())
                .thumbnailUrl(dao.getThumbnailUrl())
                .build();
    }


}
