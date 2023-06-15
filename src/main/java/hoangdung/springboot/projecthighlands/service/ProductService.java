package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.model.dao.Product;
import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ProductResponseEntity;
import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import hoangdung.springboot.projecthighlands.repository.ProductRepository;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    public static ProductRepository productRepository;

    public static ProductCatalogRepository productCatalogRepository;

    public static TagRepository tagRepository;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static List<TagResponseEntity> convertListTagIDToListTag(String listTagID) {
        return (List<TagResponseEntity>) objectMapper.readValue(listTagID, List.class)
                .stream()
                .map(s -> tagRepository.findById(s.toString()).orElseThrow())
                .toList();
    }

    @SneakyThrows
    public String convertListTagToListTagID(List<TagResponseEntity> listTag) {
        List<String> listTagID = listTag
                .stream()
                .map(TagResponseEntity::getTagID)
                .toList();
        return objectMapper.writeValueAsString(listTagID);
    }

    @SneakyThrows
    public static Map<String, Integer> convertSizeOptionStringToMap(String sizeOptionJsonString) {
        return objectMapper.readValue(sizeOptionJsonString, HashMap.class);
    }

    @SneakyThrows
    public static String convertSizeOptionMapToString(Map<String, Integer> sizeOption) {
        return objectMapper.writeValueAsString(sizeOption);
    }


    @TranferToResponseEntity
    public Tranformable createNewProduct(ProductRequestEntity entity) {
        return productRepository.save(Product.builder()
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .productCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingProduct(String id, ProductRequestEntity entity) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();

        loadedProduct.setProductName(entity.getProductName());
        loadedProduct.setDescription(entity.getDescription());
        loadedProduct.setPrice(entity.getPrice());
        loadedProduct.setImageUrl(entity.getImageUrl());
        loadedProduct.setProductCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow());

        return productRepository.save(loadedProduct);
    }

    @TranferToResponseEntity
    public Tranformable deleteProductByID(String id) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return loadedProduct;
    }

    public List<ProductResponseEntity> searchProductByProductName(String name) {
        return productRepository.searchProductByProductName(name)
                .stream()
                .map(ProductResponseEntity::fromProduct)
                .toList();
    }

    public List<ProductResponseEntity> getAllProduct() {
        return productRepository.findAll()
                .stream().map(ProductResponseEntity::fromProduct)
                .toList();
    }

    @TranferToResponseEntity
    public ProductResponseEntity getProductByID(String id) {
        return ProductResponseEntity.fromProduct(productRepository.findById(id).orElseThrow());
    }


    //CRUD của Size Option
    @TranferToResponseEntity
    public Tranformable addSizeOption(String productID, String size, int price) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.put(size, price);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return loadedProduct;
    }

    @TranferToResponseEntity
    public Tranformable updateSizeOption(String productID, String size, int newPrice) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.replace(size, newPrice);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return loadedProduct;
    }

    @TranferToResponseEntity
    public Tranformable deleteSizeOption(String productID, String size) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.remove(size);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return loadedProduct;
    }

    //CRUD của Tag ID
    @TranferToResponseEntity
    public Tranformable addTag(String productID, String tagID) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        List<TagResponseEntity> listTag = convertListTagIDToListTag(loadedProduct.getTagJsonString());
        listTag.add(TagResponseEntity.fromTag(tagRepository.findById(tagID).orElseThrow()));
        loadedProduct.setTagJsonString(convertListTagToListTagID(listTag));
        return loadedProduct;
    }

    @TranferToResponseEntity
    public Tranformable deleteTag(String productID, String tagID) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        List<TagResponseEntity> listTag = convertListTagIDToListTag(loadedProduct.getTagJsonString());
        listTag.stream()
                .filter(tagEntity -> tagEntity.getTagID().equalsIgnoreCase(tagID))
                .forEach(listTag::remove);
        loadedProduct.setTagJsonString(convertListTagToListTagID(listTag));

        return loadedProduct;
    }

}
