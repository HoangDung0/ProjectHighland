package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dao.ProductCatalog;
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
                .map(ProductCatalogResponseEntity::fromProductCatalog)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable getProductCatalogById(String id) {
        return productCatalogRepository.findById(id).orElseThrow();
    }

    public List<ProductCatalogResponseEntity> searchProductCatalogsByName(String name) {
        return productCatalogRepository.findProductCatalogByProductCatalogNameContainingIgnoreCase(name).stream()
                .map(ProductCatalogResponseEntity::fromProductCatalog)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable createNewProductCatalog(ProductCatalogRequestEntity entity) {
        return productCatalogRepository.save(ProductCatalog.builder()
                .productCatalogName(entity.getProductCatalogName())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingProductCatalog(String id, ProductCatalogRequestEntity entity) {
        ProductCatalog loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();

        loadedProductCatalog.setProductCatalogName(entity.getProductCatalogName());
        loadedProductCatalog.setDescription(entity.getDescription());
        loadedProductCatalog.setThumbnailUrl(entity.getThumbnailUrl());

        return productCatalogRepository.save(loadedProductCatalog);
    }

    @TranferToResponseEntity
    public Tranformable deleteProductCatalogByID(String id) {
        ProductCatalog loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();
        productCatalogRepository.deleteById(id);
        return loadedProductCatalog;
    }

}
