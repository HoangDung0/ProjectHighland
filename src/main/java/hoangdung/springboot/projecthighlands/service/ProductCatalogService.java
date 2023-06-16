package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.ProductCatalog;
import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductCatalogRepository productCatalogRepository;

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllProductCatalogs() {
        return productCatalogRepository.findAll();
    }

    @TranferToResponseEntity
    public Transformable getProductCatalogById(String id) {
        return productCatalogRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> searchProductCatalogsByName(String name) {
        return productCatalogRepository.findProductCatalogByProductCatalogNameContainingIgnoreCase(name);
    }

    @TranferToResponseEntity
    public Transformable createNewProductCatalog(ProductCatalogRequestEntity entity) {
        return productCatalogRepository.save(ProductCatalog.builder()
                .productCatalogName(entity.getProductCatalogName())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingProductCatalog(String id, ProductCatalogRequestEntity entity) {
        ProductCatalog loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();

        loadedProductCatalog.setProductCatalogName(entity.getProductCatalogName());
        loadedProductCatalog.setDescription(entity.getDescription());
        loadedProductCatalog.setThumbnailUrl(entity.getThumbnailUrl());

        return productCatalogRepository.save(loadedProductCatalog);
    }

    @TranferToResponseEntity
    public Transformable deleteProductCatalogByID(String id) {
        ProductCatalog loadedProductCatalog = productCatalogRepository.findById(id).orElseThrow();
        productCatalogRepository.deleteById(id);
        return loadedProductCatalog;
    }

}
