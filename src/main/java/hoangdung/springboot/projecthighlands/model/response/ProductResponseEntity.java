package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Product;
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
public class ProductResponseEntity implements Transformable {

    private String id;

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private Map<String, Integer> sizeOption;

    private List<TagResponseEntity> listTag;

    private String productCatalogID;


    public static ProductResponseEntity fromProduct(Product dao) {

        return ProductResponseEntity.builder()
                .id(dao.getId())
                .productName(dao.getProductName())
                .description(dao.getDescription())
                .price(dao.getPrice())
                .imageUrl(dao.getImageUrl())
//                .sizeOption(ofNullable(dao.getSizeOptionJsonString()).map(ProductService::convertSizeOptionStringToMap).orElse(null))
//                .listTag(ofNullable(dao.getTagJsonString()).map(s -> MappingUtils.convertIdsToObjects(s, (JpaRepository) tagRepository)).orElse(null))
                .productCatalogID(dao.getProductCatalog().getId())
                .build();
    }


}
