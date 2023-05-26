package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.CouponRequestEntity;
import hoangdung.springboot.projecthighlands.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    // add <?> into controller signature
    // adjust endpoints
    // apply controllerWrapper (rewrite)

    @GetMapping("/{name}")
    private ResponseEntity getCouponByCouponCode(@PathVariable String name) {
        return ResponseEntity.ok().body(couponService.getCouponByCouponCode(name));
    }

    @GetMapping("/search")
    private ResponseEntity searchCouponByCouponName(@RequestParam String name) {
        return ResponseEntity.ok().body(couponService.searchCouponByCouponName(name));
    }

    @PostMapping()
    public ResponseEntity createNewCoupon(@RequestBody CouponRequestEntity entity) {
        return ResponseEntity.ok().body(couponService.createNewCoupon(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateExistingCoupon(@PathVariable String id,
                                                        @RequestBody CouponRequestEntity entity) {
        return ResponseEntity.ok().body(couponService.updateExistingCoupon(id, entity));
    }

    @DeleteMapping()
    private ResponseEntity deleteCouponByID(@RequestParam String id) {
        return ResponseEntity.ok().body(couponService.deleteCouponByID(id));
    }
}
