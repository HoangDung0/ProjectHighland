package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.OrderRequestEntity;
import hoangdung.springboot.projecthighlands.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    public final OrderService orderService;


    @Operation(summary = "Get All Orders")
    @GetMapping
    private ResponseEntity<?> getAllOrders(){
        return controllerWrapper(orderService::getAllOrder);
    }

    @Operation(summary = "Get Order By ID")
    @GetMapping("/{id}")
    private ResponseEntity<?> getOrderByID(@RequestParam String orderID) {
        return controllerWrapper(() -> orderService.getOrderByID(orderID));
    }

    @Operation(summary = "Create New Order")
    @PostMapping()
    public ResponseEntity<?> createNewOrder(@RequestBody OrderRequestEntity entity) {
        return controllerWrapper(() -> orderService.createNewOrder(entity));
    }

    @Operation(summary = "Update Existing Order")
    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingOrder(@PathVariable String id,
                                                      @RequestBody OrderRequestEntity entity) {
        return controllerWrapper(() -> orderService.updateExistingOrder(id, entity));
    }

    @Operation(summary = "Delete Existing Order")
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrderByID(@PathVariable String id) {
        return controllerWrapper(() -> orderService.deleteOrderByID(id));
    }
}
