package hoangdung.springboot.projecthighlands.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hoangdung.springboot.projecthighlands.model.request.CustomerInfoRequestEntity;
import hoangdung.springboot.projecthighlands.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/customerinfos")
public class CustomerInfoController {

    private final CustomerInfoService customerInfoService;


    @GetMapping("/userID/{userid}")
    private ResponseEntity<?> getCustomerByUserID(@PathVariable String userid) {
        return controllerWrapper(() -> customerInfoService.getCustomerByUserID(userid));
    }


    @PostMapping()
    public ResponseEntity<?> createNewCustomerInfo(@RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> {
            try {
                return customerInfoService.createNewCustomerInfo(entity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingCustomerInfo(@PathVariable String id,
                                                         @RequestBody CustomerInfoRequestEntity entity) {
        return controllerWrapper(() -> {
            try {
                return customerInfoService.updateExistingCustomerInfo(id, entity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PutMapping("/usedcoupon/{id}")
    private ResponseEntity<?> searchCouponByCouponName(@RequestParam String usedCouponID, String id) {
        return controllerWrapper(() -> {
            try {
                return customerInfoService.addUsedCoupon(usedCouponID, id);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteCouponByID(@RequestParam String id) {
        return controllerWrapper(() -> {
            try {
                return customerInfoService.deleteCustomerByID(id);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
