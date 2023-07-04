package hoangdung.springboot.projecthighlands.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCatalogRequestEntity {

//    private String productCatalogID;

    private String productCatalogName;

    private String description;

    private String thumbnailUrl;
}
