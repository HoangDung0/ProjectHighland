package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Product;
import hoangdung.springboot.projecthighlands.service.ProductService;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
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

    private String productID;

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private Map<String, Integer> sizeOption;

    private List<TagResponseEntity> listTag;

    private String productCatalogID;

    public static ProductResponseEntity fromProduct(Product dao)  {

        return ProductResponseEntity.builder()
                .productID(dao.getProductID())
                .productName(dao.getProductName())
                .description(dao.getDescription())
                .price(dao.getPrice())
                .imageUrl(dao.getImageUrl())
                .sizeOption(ProductService.convertSizeOptionStringToMap(dao.getSizeOptionJsonString()))
                .listTag(ProductService.convertListTagIDToListTag(dao.getTagJsonString()))
                .productCatalogID(dao.getProductCatalog().getProductCatalogID())
                .build();
    }


}
