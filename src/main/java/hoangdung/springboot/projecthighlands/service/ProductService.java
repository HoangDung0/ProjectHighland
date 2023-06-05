package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.model.dto.ProductDto;
import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ProductResponseEntity;
import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ProductCatalogRepository;
import hoangdung.springboot.projecthighlands.repository.ProductRepository;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
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
    public static List<TagResponseEntity> convertListTagIDToListTag(String listTagID){
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
    public static Map<String, Integer> convertSizeOptionStringToMap(String sizeOptionJsonString){
        return objectMapper.readValue(sizeOptionJsonString, HashMap.class);
    }
    @SneakyThrows
    public static String convertSizeOptionMapToString(Map<String, Integer> sizeOption) {
        return objectMapper.writeValueAsString(sizeOption);
    }



    public ProductResponseEntity createNewProduct(ProductRequestEntity entity) throws JsonProcessingException {
        ProductDto preparedProduct = ProductDto.builder()
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
//                .sizeOptionJsonString(convertSizeOptionMapToString(entity.getSizeOption()))
//                .tagJsonString(convertListTagToListTagID(entity.getListTag()))
                .productCatalogDto(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow())
                .build();

        return ProductResponseEntity.fromProductDto(productRepository.save(preparedProduct));

    }

    public ProductResponseEntity updateExistingProduct(String id, ProductRequestEntity entity) throws JsonProcessingException {
        ProductDto loadedProduct = productRepository.findById(id).orElseThrow();

        loadedProduct.setProductName(entity.getProductName());
        loadedProduct.setDescription(entity.getDescription());
        loadedProduct.setPrice(entity.getPrice());
        loadedProduct.setImageUrl(entity.getImageUrl());
        loadedProduct.setProductCatalogDto(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow());

        return ProductResponseEntity.fromProductDto(productRepository.save(loadedProduct));
    }


    public ProductResponseEntity deleteProductByID(String id) throws JsonProcessingException {
        ProductDto loadedProduct = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return ProductResponseEntity.fromProductDto(loadedProduct);
    }


    public List<ProductResponseEntity> searchProductByProductName(String name) throws JsonProcessingException {
        return productRepository.searchProductByProductName(name)
                .stream()
                .map(ProductResponseEntity::fromProductDto)
                .toList();
    }

    public List<ProductResponseEntity> getAllProduct(){
        return productRepository.findAll()
                .stream().map(ProductResponseEntity::fromProductDto)
                .toList();
    }

    public ProductResponseEntity getProductByID(String id) throws JsonProcessingException {
        return ProductResponseEntity.fromProductDto(productRepository.findById(id).orElseThrow());
    }


    //CRUD của Size Option
    public ProductResponseEntity addSizeOption(String productID, String size, int price) throws JsonProcessingException {
        ProductDto loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.put(size, price);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return ProductResponseEntity.fromProductDto(loadedProduct);
    }

    public ProductResponseEntity updateSizeOption(String productID, String size, int newPrice) throws JsonProcessingException {
        ProductDto loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.replace(size, newPrice);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return ProductResponseEntity.fromProductDto(loadedProduct);
    }

    public ProductResponseEntity deleteSizeOption(String productID, String size) throws JsonProcessingException {
        ProductDto loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = convertSizeOptionStringToMap(loadedProduct.getSizeOptionJsonString());
        sizeOption.remove(size);
        loadedProduct.setSizeOptionJsonString(convertSizeOptionMapToString(sizeOption));

        return ProductResponseEntity.fromProductDto(loadedProduct);
    }

    //CRUD của Tag ID


}
