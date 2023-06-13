package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.service.CustomerInfoService;
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


    @GetMapping("/userID/{userid}")
    private ResponseEntity<?> getCustomerByUserID(@PathVariable String userid) {
        return controllerWrapper(() -> customerInfoService.getCustomerByUserID(userid));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getCustomerByID(@PathVariable String id){
        return controllerWrapper(() -> customerInfoService.getCustomerByID(id));
    }


    @PostMapping()
    public ResponseEntity<?> createNewCustomerInfo(@RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> customerInfoService.createNewCustomerInfo(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingCustomerInfo(@PathVariable String id,
                                                         @RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> customerInfoService.updateExistingCustomerInfo(id, entity));
    }

    @PutMapping("/usedcoupon/{id}")
    private ResponseEntity<?> addUsedCoupon(@RequestParam String usedCouponID, String id) {
        return controllerWrapper(() -> customerInfoService.addUsedCoupon(usedCouponID, id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCustomerByID(@PathVariable String id) {
        return controllerWrapper(() -> customerInfoService.deleteCustomerByID(id));
    }
}
