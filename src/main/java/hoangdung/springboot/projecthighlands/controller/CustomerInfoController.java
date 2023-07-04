package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.service.CustomerInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/customerinfos")
public class CustomerInfoController {

    private final CustomerInfoService customerInfoService;


    @Operation(summary = "Get Customer By UserID")
    @GetMapping("/userID/{userid}")
    private ResponseEntity<?> getCustomerByUserID(@PathVariable String userid) {
        return controllerWrapper(() -> customerInfoService.getCustomerByUserID(userid));
    }

    @Operation(summary = "Get Customer By ID")
    @GetMapping("/{id}")
    private ResponseEntity<?> getCustomerByID(@PathVariable String id){
        return controllerWrapper(() -> customerInfoService.getCustomerByID(id));
    }

    @Operation(summary = "Create New Customer Info")
    @PostMapping()
    public ResponseEntity<?> createNewCustomerInfo(@RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> customerInfoService.createNewCustomerInfo(entity));
    }

    @Operation(summary = "Update Existing Customer Info")
    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingCustomerInfo(@PathVariable String id,
                                                         @RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> customerInfoService.updateExistingCustomerInfo(id, entity));
    }

    @Operation(summary = "Add Used Coupon")
    @PutMapping("/usedcoupon/{id}")
    private ResponseEntity<?> addUsedCoupon(@RequestParam String usedCouponID, String id) {
        return controllerWrapper(() -> customerInfoService.addUsedCoupon(usedCouponID, id));
    }

    @Operation(summary = "Delete Customer By ID")
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCustomerByID(@PathVariable String id) {
        return controllerWrapper(() -> customerInfoService.deleteCustomerByID(id));
    }
}
