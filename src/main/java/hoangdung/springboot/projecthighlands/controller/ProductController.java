package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    public static ProductService productService;

    @GetMapping
    private ResponseEntity<?> getAllProduct() {
        return controllerWrapper(productService::getAllProduct);
    }

    @GetMapping
    private ResponseEntity<?> searchProductByProductName(@RequestParam String name) {
        return controllerWrapper(() -> productService.searchProductByProductName(name));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getProductByID(@PathVariable String id) {
        return controllerWrapper(() -> productService.getProductByID(id));
    }


    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequestEntity entity) {
        return controllerWrapper(() ->productService.createNewProduct(entity));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingProductCatalog(@PathVariable String id,
                                                           @RequestBody ProductRequestEntity entity) {
        return controllerWrapper(() -> productService.updateExistingProduct(id, entity));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteProductCatalogByID(@RequestParam String id) {
        return controllerWrapper(() -> productService.deleteProductByID(id));
    }


    //API CRUD Size Option
    @PostMapping("/{id}/sizeoption")
    private ResponseEntity<?> addSizeOption(@PathVariable String id,
                                            @RequestParam String size,
                                            @RequestParam int price) {
        return controllerWrapper(() -> productService.addSizeOption(id, size, price));
    }


    @PutMapping("/{id}/sizeoption")
    private ResponseEntity<?> updateSizeOption(@PathVariable String id,
                                               @RequestParam String size,
                                               @RequestParam int newPrice) {
        return controllerWrapper(() -> productService.updateSizeOption(id, size, newPrice));
    }

    @DeleteMapping("/{id}/sizeoption")
    private ResponseEntity<?> deleteSizeOption(@PathVariable String id,
                                               @RequestParam String size) {
        return controllerWrapper(() -> productService.deleteSizeOption(id, size));
    }


    //API CRUD Tag
    @PostMapping("/{id}/tag")
    private ResponseEntity<?> addTag(@PathVariable String id,
                                     @RequestParam String productID,
                                     @RequestParam String tagID){
        return controllerWrapper(() -> productService.addTag(productID,tagID));
    }

    @DeleteMapping("/{id}/tag")
    private ResponseEntity<?> deleteTag(@PathVariable String id,
                                     @RequestParam String productID,
                                     @RequestParam String tagID){
        return controllerWrapper(() -> productService.deleteTag(productID,tagID));
    }
}

