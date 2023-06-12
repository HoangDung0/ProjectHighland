package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.CouponRequestEntity;
import hoangdung.springboot.projecthighlands.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    // add <?> into controller signature
    // adjust endpoints
    // apply controllerWrapper (rewrite)

    @GetMapping("/{code}")
    private ResponseEntity<?> getCouponByCouponCode(@PathVariable String code) {
        return controllerWrapper(() -> couponService.getCouponByCouponCode(code));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchCouponByCouponName(@RequestParam String name) {
        return controllerWrapper(() -> couponService.searchCouponByCouponName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewCoupon(@RequestBody CouponRequestEntity entity) {
        return controllerWrapper(() -> couponService.createNewCoupon(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingCoupon(@PathVariable String id,
                                                   @RequestBody CouponRequestEntity entity) {
        return controllerWrapper(() -> couponService.updateExistingCoupon(id, entity));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCouponByID(@PathVariable String id) {
        return controllerWrapper(() -> couponService.deleteCouponByID(id));
    }
}
