package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.ProductCatalogDto;
import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ProductCatalogResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductCatalogRepository productCatalogRepository;

    public List<ProductCatalogResponseEntity> getAllProductCatalogs() {
        List<ProductCatalogDto> listProductCatalogDto = productCatalogRepository.findAll();
        List<ProductCatalogResponseEntity> listProductCatalogsResponse = new ArrayList<>();
        for (ProductCatalogDto dto : listProductCatalogDto ) {
            listProductCatalogsResponse.add( ProductCatalogResponseEntity.fromProductCatalogDto(dto));
        }
        return listProductCatalogsResponse;
    }

    public ProductCatalogResponseEntity getProductCatalogById(String id) {
        return ProductCatalogResponseEntity.fromProductCatalogDto(productCatalogRepository.findById(id).orElse(null));
    }

    public List<ProductCatalogResponseEntity> searchProductCatalogsByName(String name) {
        List<ProductCatalogDto> listProductCatalogDto =
                productCatalogRepository.findProductCatalogByProductCatalogNameContainingIgnoreCase(name);

        List<ProductCatalogResponseEntity> listProductCatalogResponse = new ArrayList<>();
        for (ProductCatalogDto dto : listProductCatalogDto ) {
            listProductCatalogResponse.add( ProductCatalogResponseEntity.fromProductCatalogDto(dto));
        }
        return listProductCatalogResponse;

    }


    public ProductCatalogResponseEntity createNewProductCatalog(ProductCatalogRequestEntity requestEntity) {
        ProductCatalogDto preparedProductCatalog = ProductCatalogDto.builder()
                .productCatalogName(requestEntity.getProductCatalogName())
                .description(requestEntity.getDescription())
                .thumbnailUrl(requestEntity.getThumbnailUrl())
                .build();

        return ProductCatalogResponseEntity.fromProductCatalogDto(productCatalogRepository.save(preparedProductCatalog));

    }

    public ProductCatalogResponseEntity updateExistingProductCatalog(String id, ProductCatalogRequestEntity requestEntity){
        ProductCatalogDto loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();

        loadedProductCatalog.setProductCatalogName(requestEntity.getProductCatalogName());
        loadedProductCatalog.setDescription(requestEntity.getDescription());
        loadedProductCatalog.setThumbnailUrl(requestEntity.getThumbnailUrl());

        return ProductCatalogResponseEntity.fromProductCatalogDto(productCatalogRepository.save(loadedProductCatalog));
    }



    public ProductCatalogResponseEntity deleteProductCatalogByID(String id) {
        ProductCatalogDto loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();
        productCatalogRepository.deleteById(id);
        return ProductCatalogResponseEntity.fromProductCatalogDto(loadedProductCatalog);
    }

}
