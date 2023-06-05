package hoangdung.springboot.projecthighlands.model.request;

import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ProductRequestEntity {

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private Map<String, Integer> sizeOption;

    private List<TagResponseEntity> listTag;

    private String productCatalogID;


}
