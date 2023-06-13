package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.ProductCatalogDto;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCatalogResponseEntity implements Tranformable {

    private String productCatalogID;

    private String productCatalogName;

    private String description;

    private String thumbnailUrl;

    public static ProductCatalogResponseEntity fromProductCatalogDto(ProductCatalogDto dto) {
        return  ProductCatalogResponseEntity.builder()
                .productCatalogID(dto.getProductCatalogID())
                .productCatalogName(dto.getProductCatalogName())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build();
    }


}
