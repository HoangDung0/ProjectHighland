package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.OrderItemRequestEntity;
import hoangdung.springboot.projecthighlands.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/orderitems")
public class OrderItemController {

    public final OrderItemService orderItemService;


    @Operation(summary = "Get Order Item By Order ID")
    @GetMapping("/oderid/{orderID}")
    public ResponseEntity<?> getOrderItemByOrderID(@PathVariable String orderID) {
        return controllerWrapper(() -> orderItemService.getOrderItemByOrderID(orderID));
    }

    @Operation(summary = "Get Order Item By ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemByID(@PathVariable String id) {
        return controllerWrapper(() -> orderItemService.getOrderItemByID(id));
    }

    @Operation(summary = "Create Order Item")
    @PostMapping()
    public ResponseEntity<?> createNewOrderItem(@RequestBody OrderItemRequestEntity entity) {
        return controllerWrapper(() -> orderItemService.createNewOrderItem(entity));
    }

    @Operation(summary = "Update Order Item")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingOrderItem(@PathVariable String id,
                                                      @RequestBody OrderItemRequestEntity entity) {
        return controllerWrapper(() -> orderItemService.updateExistingOrderItem(id, entity));
    }

    @Operation(summary = "Delete Order Item")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductCatalogByID(@PathVariable String id) {
        return controllerWrapper(() -> orderItemService.deleteOrderItemByID(id));
    }

}
