package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Product;
import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import hoangdung.springboot.projecthighlands.repository.ProductRepository;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hoangdung.springboot.projecthighlands.common.MappingUtils.convertIdsToObjects;
import static hoangdung.springboot.projecthighlands.common.MappingUtils.convertObjectsToIds;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public final ProductCatalogRepository productCatalogRepository;

    public final TagRepository tagRepository;

    public static ObjectMapper objectMapper = new ObjectMapper();

//    @SneakyThrows
//    public static List<TagResponseEntity> convertListTagIDToListTag(String listTagID ) {
//
//        return (List<TagResponseEntity>) objectMapper.readValue(listTagID, List.class)
//                .stream()
//                .map(s -> tagRepository.findById(s.toString()).orElseThrow())
//                .toList();
//    }


//    @SneakyThrows
//    public String convertListTagToListTagID(List<TagResponseEntity> listTag) {
//        List<String> listTagID = listTag
//                .stream()
//                .map(TagResponseEntity::getTagID)
//                .toList();
//        return objectMapper.writeValueAsString(listTagID);
//    }

    @SneakyThrows
    public static Map<String, Integer> convertSizeOptionStringToMap(String sizeOptionJsonString) {
        return objectMapper.readValue(sizeOptionJsonString, HashMap.class);
    }

    @SneakyThrows
    public static String convertSizeOptionMapToString(Map<String, Integer> sizeOption) {
        return objectMapper.writeValueAsString(sizeOption);
    }


    @TranferToResponseEntity
    public Transformable createNewProduct(ProductRequestEntity entity) {
        return productRepository.save(Product.builder()
                .productName(entity.getProductName())
                .description(entity.getDescription())
//                .sizeOptionJsonString(null)
//                .tagJsonString(null)
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .productCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow())
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingProduct(String id, ProductRequestEntity entity) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();

        loadedProduct.setProductName(entity.getProductName());
        loadedProduct.setDescription(entity.getDescription());
        loadedProduct.setPrice(entity.getPrice());
        loadedProduct.setImageUrl(entity.getImageUrl());
        loadedProduct.setProductCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow());

        return productRepository.save(loadedProduct);
    }

    @TranferToResponseEntity
    public Transformable deleteProductByID(String id) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return loadedProduct;
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> searchProductByProductName(String name) {
        return productRepository.getProductsByProductNameIgnoreCase(name);
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllProduct() {
        return productRepository.findAll();
    }

    @TranferToResponseEntity
    public Transformable getProductByID(String id) {
        return productRepository.findById(id).orElseThrow();
    }


    //CRUD của Size Option
    @TranferToResponseEntity
    public Transformable addSizeOption(String productID, String size, int price) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = loadedProduct.getSizeOptionJsonString() == null ?
                new HashMap<>() :
                convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.put(size, price);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return productRepository.save(loadedProduct);
    }

    @TranferToResponseEntity
    public Transformable updateSizeOption(String productID, String size, int newPrice) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = loadedProduct.getSizeOptionJsonString() == null ?
                new HashMap<>() :
                convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.replace(size, newPrice);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return productRepository.save(loadedProduct);
    }

    @TranferToResponseEntity
    public Transformable deleteSizeOption(String productID, String size) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.remove(size);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return productRepository.save(loadedProduct);
    }

    //CRUD của Tag ID
    @TranferToResponseEntity
    public Transformable addTag(String productID, String tagID) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        List<Transformable> listTag = loadedProduct.getTagJsonString() == null ?
                new ArrayList<>() :
                convertIdsToObjects(loadedProduct.getTagJsonString(),(JpaRepository) tagRepository);
        listTag.add(TagResponseEntity.fromTag(tagRepository.findById(tagID).orElseThrow()));
        loadedProduct.setTagJsonString(convertObjectsToIds(listTag, (JpaRepository) tagRepository));
        return productRepository.save(loadedProduct);
    }

    @TranferToResponseEntity
    public Transformable deleteTag(String productID, String tagID) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        List<Transformable> listTag =  convertIdsToObjects(loadedProduct.getTagJsonString(),(JpaRepository) tagRepository);
        listTag.stream()
                .filter(tagEntity -> tagEntity.getId().equalsIgnoreCase(tagID))
                .forEach(listTag::remove);
        loadedProduct.setTagJsonString(convertObjectsToIds(listTag, (JpaRepository) tagRepository));

        return productRepository.save(loadedProduct);
    }

}
