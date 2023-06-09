package hoangdung.springboot.projecthighlands.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.model.dto.OrderItemDto;
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
    public static Map<String, Integer> convertSizeStringToMap(String sizeJsonString) {
        return objectMapper.readValue(sizeJsonString, HashMap.class);
    }

    @SneakyThrows
    public static String convertSizeMapToString(Map<String, Integer> size) {
        return objectMapper.writeValueAsString(size);
    }

    @SneakyThrows
    public static Map<String, Integer> convertListToppingStringToMap(String listToppingJsonString) {
        return objectMapper.readValue(listToppingJsonString, HashMap.class);
    }

    @SneakyThrows
    public static String convertListToppingMapToString(Map<String, Integer> listTopping) {
        return objectMapper.writeValueAsString(listTopping);
    }

    public float pricePerOrderItem (OrderItemRequestEntity entity) {
        Map<String, Integer> listTopping = entity.getListTopping();
        Map<String, Integer> size = entity.getSize();

        //Giá mặc định 1 sản phẩm
        float priceProduct = productRepository.findById(entity.getProductID()).orElseThrow().getPrice();

        //Giá khi topping
        float priceTopping = 0;
        Set<String> listToppingID = listTopping.keySet();
        for (String s : listToppingID) {
            priceTopping = priceTopping
                    + toppingRepository.findById(s).orElseThrow().getPrice() * listTopping.get(s);
        }

        //Giá nâng size
        float priceSize = 0;
        Set<String> listSize = size.keySet();
        for (String s : listSize) {
            priceSize = priceSize + (float) size.get(s);
        }

        return priceProduct + priceTopping + priceSize;

    }


    public OrderItemResponseEntity createNewOrderItem(OrderItemRequestEntity entity) {
        OrderItemDto preparedOrderItem = OrderItemDto.builder()
                .quantity(entity.getQuantity())
                .listToppingJsonString(convertListToppingMapToString(entity.getListTopping()))
                .price(pricePerOrderItem(entity))
                .orderDto(orderRepository.findById(entity.getOrderID()).orElseThrow())
                .productDto(productRepository.findById(entity.getProductID()).orElseThrow())
                .sizeJsonString(convertSizeMapToString(entity.getSize()))
                .build();

        return OrderItemResponseEntity.fromOrderItemDto(orderItemRepository.save(preparedOrderItem));

    }

    public OrderItemResponseEntity updateExistingOrderItem(String id, OrderItemRequestEntity entity) {
        OrderItemDto loadedOrderItem = orderItemRepository.findById(id).orElseThrow();

        loadedOrderItem.setQuantity(entity.getQuantity());
        loadedOrderItem.setListToppingJsonString(convertListToppingMapToString(entity.getListTopping()));
        loadedOrderItem.setSizeJsonString(convertSizeMapToString(entity.getSize()));
        loadedOrderItem.setPrice(pricePerOrderItem(entity) );

        return OrderItemResponseEntity.fromOrderItemDto(orderItemRepository.save(loadedOrderItem));
    }


    public OrderItemResponseEntity deleteOrderItemByID(String id) {
        OrderItemDto loadedOrderItem = orderItemRepository.findById(id).orElseThrow();
        orderItemRepository.deleteById(id);
        return OrderItemResponseEntity.fromOrderItemDto(loadedOrderItem);
    }


    public List<OrderItemResponseEntity> getOrderItemByOrderID(String id) {
        return orderItemRepository.getOrderItemByOrderID(id)
                .stream()
                .map(OrderItemResponseEntity::fromOrderItemDto)
                .toList();
    }

    public OrderItemResponseEntity getOrderItemByID(String id) {
        return OrderItemResponseEntity.fromOrderItemDto(orderItemRepository.findById(id).orElseThrow());
    }

}
