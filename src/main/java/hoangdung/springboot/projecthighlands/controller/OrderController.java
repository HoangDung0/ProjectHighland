package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.OrderRequestEntity;
import hoangdung.springboot.projecthighlands.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    public static OrderService orderService;


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

    @DeleteMapping()
    private ResponseEntity<?> deleteOrderByID(@RequestParam String id) {
        return controllerWrapper(() -> orderService.deleteOrderByID(id));
    }
}
