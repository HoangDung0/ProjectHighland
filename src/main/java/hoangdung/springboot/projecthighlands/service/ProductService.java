package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Product;
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
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public final ProductCatalogRepository productCatalogRepository;

    public final TagRepository tagRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

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
//                .map(TagResponseEntity::getId)
//                .toList();
//        return objectMapper.writeValueAsString(listTagID);
//    }

    @SneakyThrows
    private Transformable mapFromProductToResponseEntity(Product persistedProduct) {
        var mappedResponse = ProductResponseEntity.fromProduct(persistedProduct);
        mappedResponse.setSizeOption(objectMapper.readValue(persistedProduct.getSizeOptionJsonString(), HashMap.class));

        var tags = objectMapper.readTree(persistedProduct.getTagJsonString());

        var responseTags = StreamSupport.stream(tags.spliterator(), false)
                .map(s -> tagRepository.findById(s.asText("")).orElse(null))
                .map(TagResponseEntity::fromTag)
                .toList();

        mappedResponse.setListTag(responseTags);

        return mappedResponse;
    }


    public Transformable createNewProduct(ProductRequestEntity entity) {
        var preparedProduct = Product.builder()
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .productCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow())
                .build();

        return mapFromProductToResponseEntity(productRepository.save(preparedProduct));

    }

    public Transformable updateExistingProduct(String id, ProductRequestEntity entity) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();

        loadedProduct.setProductName(entity.getProductName());
        loadedProduct.setDescription(entity.getDescription());
        loadedProduct.setPrice(entity.getPrice());
        loadedProduct.setImageUrl(entity.getImageUrl());
        loadedProduct.setProductCatalog(productCatalogRepository.findById(entity.getProductCatalogID()).orElseThrow());

        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));
    }

    public Transformable deleteProductByID(String id) {
        Product loadedProduct = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return mapFromProductToResponseEntity(loadedProduct);
    }


    public List<? extends Transformable> searchProductByProductName(String name) {
        return productRepository.getProductsByProductNameIgnoreCase(name).stream()
                .map(this::mapFromProductToResponseEntity)
                .toList();
    }


    public List<? extends Transformable> getAllProduct() {
        return productRepository.findAll().stream()
                .map(this::mapFromProductToResponseEntity)
                .toList();
    }


    public Transformable getProductByID(String id) {
        return mapFromProductToResponseEntity(productRepository.findById(id).orElseThrow());
    }


    //CRUD của Size Option

    @SneakyThrows
    public Transformable addSizeOption(String productID, String size, int price) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        var sizeOption = ofNullable(loadedProduct.getSizeOptionJsonString())
                .map(s -> {
                    try {
                        return objectMapper.readValue(s, Map.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(new HashMap());

        sizeOption.put(size, price);
        loadedProduct.setSizeOptionJsonString(objectMapper.writeValueAsString(sizeOption));

        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));
    }

    @SneakyThrows
    public Transformable updateSizeOption(String productID, String size, int newPrice) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        var sizeOption = ofNullable(loadedProduct.getSizeOptionJsonString())
                .map(s -> {
                    try {
                        return objectMapper.readValue(s, Map.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(new HashMap());
        sizeOption.replace(size, newPrice);
        loadedProduct.setSizeOptionJsonString(objectMapper.writeValueAsString(sizeOption));

        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));
    }

    @SneakyThrows
    public Transformable deleteSizeOption(String productID, String size) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();
        Map<String, Integer> sizeOption = objectMapper.readValue(loadedProduct.getSizeOptionJsonString(), Map.class);
        sizeOption.remove(size);
        loadedProduct.setSizeOptionJsonString(objectMapper.writeValueAsString(sizeOption));

        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));
    }

    //CRUD của Tag ID
    @SneakyThrows
    public Transformable addTag(String productID, String tagID) {
        //1. lấy product. kiểm tra xem product có hay ko
        Product loadedProduct = productRepository.findById(productID).orElseThrow();

        //2. lấy ra tag. xem tag có hay ko
        tagRepository.findById(tagID).orElseThrow();

        //3. Lấy ra json chứa list các ID
        var tagsJson = ofNullable(loadedProduct.getTagJsonString()).orElse("[]");

        //4. Đọc array json và thêm mới vào
        var tags = (ArrayNode) objectMapper.readTree(tagsJson);
        tags.add(tagID);

        //5. Chuyển đổi list có giá trị mới sang Json và gắn vào Product
        loadedProduct.setTagJsonString(tags.toString());

        //6. Lưu vào db
        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));

    }

    @SneakyThrows
    public Transformable deleteTag(String productID, String tagID) {
        Product loadedProduct = productRepository.findById(productID).orElseThrow();

        var tagsJson = ofNullable(loadedProduct.getTagJsonString()).orElse("[]");

        var tags = (ArrayNode) objectMapper.readTree(tagsJson);

        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i).asText().equalsIgnoreCase(tagID)){
                tags.remove(i);
            }
        }

        loadedProduct.setTagJsonString(tags.toString());

        return mapFromProductToResponseEntity(productRepository.save(loadedProduct));
    }

}
