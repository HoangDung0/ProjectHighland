package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.OrderItemRequestEntity;
import hoangdung.springboot.projecthighlands.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/orderitems")
public class OrderItemController {

    public static OrderItemService orderItemService;


    @GetMapping()
    private ResponseEntity<?> getOrderItemByOrderID(@RequestParam String orderID) {
        return controllerWrapper(() -> orderItemService.getOrderItemByOrderID(orderID));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOrderItemByID(@PathVariable String id) {
        return controllerWrapper(() -> orderItemService.getOrderItemByID(id));
    }


    @PostMapping()
    public ResponseEntity<?> createNewOrderItem(@RequestBody OrderItemRequestEntity entity) {
        return controllerWrapper(() -> orderItemService.createNewOrderItem(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingOrderItem(@PathVariable String id,
                                                      @RequestBody OrderItemRequestEntity entity) {
        return controllerWrapper(() -> orderItemService.updateExistingOrderItem(id, entity));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteProductCatalogByID(@RequestParam String id) {
        return controllerWrapper(() -> orderItemService.deleteOrderItemByID(id));
    }

}
