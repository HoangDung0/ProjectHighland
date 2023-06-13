package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.ProductCatalogDto;
import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ProductCatalogResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductCatalogRepository productCatalogRepository;

    public List<ProductCatalogResponseEntity> getAllProductCatalogs() {
        return productCatalogRepository.findAll().stream()
                .map(ProductCatalogResponseEntity::fromProductCatalogDto)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable getProductCatalogById(String id) {
        return productCatalogRepository.findById(id).orElseThrow();
    }

    public List<ProductCatalogResponseEntity> searchProductCatalogsByName(String name) {
        return productCatalogRepository.findProductCatalogByProductCatalogNameContainingIgnoreCase(name).stream()
                .map(ProductCatalogResponseEntity::fromProductCatalogDto)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable createNewProductCatalog(ProductCatalogRequestEntity requestEntity) {
        return productCatalogRepository.save(ProductCatalogDto.builder()
                .productCatalogName(requestEntity.getProductCatalogName())
                .description(requestEntity.getDescription())
                .thumbnailUrl(requestEntity.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingProductCatalog(String id, ProductCatalogRequestEntity requestEntity) {
        ProductCatalogDto loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();

        loadedProductCatalog.setProductCatalogName(requestEntity.getProductCatalogName());
        loadedProductCatalog.setDescription(requestEntity.getDescription());
        loadedProductCatalog.setThumbnailUrl(requestEntity.getThumbnailUrl());

        return productCatalogRepository.save(loadedProductCatalog);
    }

    @TranferToResponseEntity
    public Tranformable deleteProductCatalogByID(String id) {
        ProductCatalogDto loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();
        productCatalogRepository.deleteById(id);
        return loadedProductCatalog;
    }

}
