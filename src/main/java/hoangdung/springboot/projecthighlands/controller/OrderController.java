package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.OrderRequestEntity;
import hoangdung.springboot.projecthighlands.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    public static OrderService orderService;


    @GetMapping
    private ResponseEntity<?> getAllOrders(){
        return controllerWrapper(orderService::getAllOrder);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOrderByID(@RequestParam String orderID) {
        return controllerWrapper(() -> orderService.getOrderByID(orderID));
    }


    @PostMapping()
    public ResponseEntity<?> createNewOrder(@RequestBody OrderRequestEntity entity) {
        return controllerWrapper(() -> orderService.createNewOrder(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingOrder(@PathVariable String id,
                                                      @RequestBody OrderRequestEntity entity) {
        return controllerWrapper(() -> orderService.updateExistingOrder(id, entity));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrderByID(@PathVariable String id) {
        return controllerWrapper(() -> orderService.deleteOrderByID(id));
    }
}
