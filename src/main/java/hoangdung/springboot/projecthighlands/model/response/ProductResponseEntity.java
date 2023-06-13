package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.ProductDto;
import hoangdung.springboot.projecthighlands.service.ProductService;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseEntity implements Tranformable {

    private String productID;

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private Map<String, Integer> sizeOption;

    private List<TagResponseEntity> listTag;

    private String productCatalogID;

    public static ProductResponseEntity fromProductDto(ProductDto dto)  {

        return ProductResponseEntity.builder()
                .productID(dto.getProductID())
                .productName(dto.getProductName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .sizeOption(ProductService.convertSizeOptionStringToMap(dto.getSizeOptionJsonString()))
                .listTag(ProductService.convertListTagIDToListTag(dto.getTagJsonString()))
                .productCatalogID(dto.getProductCatalogDto().getProductCatalogID())
                .build();
    }


}
