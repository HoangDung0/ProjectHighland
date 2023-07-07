package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.OrderItem;
import hoangdung.springboot.projecthighlands.model.request.OrderItemRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.OrderItemResponseEntity;
import hoangdung.springboot.projecthighlands.repository.OrderItemRepository;
import hoangdung.springboot.projecthighlands.repository.OrderRepository;
import hoangdung.springboot.projecthighlands.repository.ProductRepository;
import hoangdung.springboot.projecthighlands.repository.ToppingRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    public static OrderItemRepository orderItemRepository;

    public static OrderRepository orderRepository;

    public static ProductRepository productRepository;

    public static ToppingRepository toppingRepository;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Map<String, Integer> convertSizeStringToMap(String sizeJsonString) {
        return objectMapper.readValue(sizeJsonString, Map.class);
    }

    @SneakyThrows
    public String convertSizeMapToString(Map<String, Integer> size) {
        return objectMapper.writeValueAsString(size);
    }

    @SneakyThrows
    public Map<String, Integer> convertListToppingStringToMap(String listToppingJsonString) {
        return objectMapper.readValue(listToppingJsonString, HashMap.class);
    }

    @SneakyThrows
    public String convertListToppingMapToString(Map<String, Integer> listTopping) {
        return objectMapper.writeValueAsString(listTopping);
    }

    @SneakyThrows
    private Transformable mapFromOrderItemToResponseEntity(OrderItem persistedOrderItem) {
        var mappedResponse = OrderItemResponseEntity.fromOrderItem(persistedOrderItem);

        mappedResponse.setSize(convertSizeStringToMap(persistedOrderItem.getSizeJsonString()));

        mappedResponse.setListTopping(convertListToppingStringToMap(persistedOrderItem.getListToppingJsonString()));

        return mappedResponse;
    }


    public float pricePerOrderItem(OrderItemRequestEntity entity) {
        Map<String, Integer> listTopping = entity.getListTopping();
        Map<String, Integer> size = entity.getSize();

        //Giá mặc định 1 sản phẩm
        float priceProduct = productRepository.findById(entity.getProductID()).orElseThrow().getPrice();

        //Giá khi topping
        //viết reduce
        float priceTopping = 0;
        Set<String> listToppingID = listTopping.keySet();
        for (String s : listToppingID) {
            priceTopping = priceTopping
                    + toppingRepository.findById(s).orElseThrow().getPrice() * listTopping.get(s);
        }

        //Giá nâng size
        //reduce
        float priceSize = 0;
        Set<String> listSize = size.keySet();
        for (String s : listSize) {
            priceSize = priceSize + (float) size.get(s);
        }

        return (priceProduct + priceTopping + priceSize) * entity.getQuantity();

    }

    public Transformable createNewOrderItem(OrderItemRequestEntity entity) {
        OrderItem prepareOrderItem = OrderItem.builder()
                .quantity(entity.getQuantity())
                .listToppingJsonString(convertListToppingMapToString(entity.getListTopping()))
                .price(pricePerOrderItem(entity))
                .order(orderRepository.findById(entity.getOrderID()).orElseThrow())
                .product(productRepository.findById(entity.getProductID()).orElseThrow())
                .sizeJsonString(convertSizeMapToString(entity.getSize()))
                .build();

        return mapFromOrderItemToResponseEntity(orderItemRepository.save(prepareOrderItem));
    }

    public Transformable updateExistingOrderItem(String id, OrderItemRequestEntity entity) {
        OrderItem loadedOrderItem = orderItemRepository.findById(id).orElseThrow();

        loadedOrderItem.setQuantity(entity.getQuantity());
        loadedOrderItem.setListToppingJsonString(convertListToppingMapToString(entity.getListTopping()));
        loadedOrderItem.setSizeJsonString(convertSizeMapToString(entity.getSize()));
        loadedOrderItem.setPrice(pricePerOrderItem(entity));

        return mapFromOrderItemToResponseEntity(orderItemRepository.save(loadedOrderItem));
    }


    public Transformable deleteOrderItemByID(String id) {
        OrderItem loadedOrderItem = orderItemRepository.findById(id).orElseThrow();
        orderItemRepository.deleteById(id);
        return mapFromOrderItemToResponseEntity(loadedOrderItem);
    }



    public List<? extends Transformable> getOrderItemByOrderID(String id) {
        return orderItemRepository.getOrderItemByOrderID(id).stream()
                .map(this::mapFromOrderItemToResponseEntity)
                .toList();
    }

    public Transformable getOrderItemByID(String id) {
        return mapFromOrderItemToResponseEntity(orderItemRepository.findById(id).orElseThrow());
    }

}
