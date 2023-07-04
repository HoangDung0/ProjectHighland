package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    public final ProductService productService;

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        return controllerWrapper(productService::getAllProduct);
    }

    @Operation(summary = "Search Product By Product Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchProductByProductName(@RequestParam String name) {
        return controllerWrapper(() -> productService.searchProductByProductName(name));
    }

    @Operation(summary = "Get Product By Product ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable String id) {
        return controllerWrapper(() -> productService.getProductByID(id));
    }


    @Operation(summary = "Create New Product")
    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequestEntity entity) {
        return controllerWrapper(() ->productService.createNewProduct(entity));
    }

    @Operation(summary = "Update Existing Product")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingProductCatalog(@PathVariable String id,
                                                           @RequestBody ProductRequestEntity entity) {
        return controllerWrapper(() -> productService.updateExistingProduct(id, entity));
    }

    @Operation(summary = "Delete Existing Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductCatalogByID(@PathVariable String id) {
        return controllerWrapper(() -> productService.deleteProductByID(id));
    }


    //API CRUD Size Option
    @Operation(summary = "Add Size Option")
    @PostMapping("/{id}/sizeoption")
    public ResponseEntity<?> addSizeOption(@PathVariable String id,
                                            @RequestParam String size,
                                            @RequestParam int price) {
        return controllerWrapper(() -> productService.addSizeOption(id, size, price));
    }

    @Operation(summary = "Update Size Option")
    @PutMapping("/{id}/sizeoption")
    public ResponseEntity<?> updateSizeOption(@PathVariable String id,
                                               @RequestParam String size,
                                               @RequestParam int newPrice) {
        return controllerWrapper(() -> productService.updateSizeOption(id, size, newPrice));
    }

    @Operation(summary = "Delete Size Option")
    @DeleteMapping("/{id}/sizeoption")
    public ResponseEntity<?> deleteSizeOption(@PathVariable String id,
                                               @RequestParam String size) {
        return controllerWrapper(() -> productService.deleteSizeOption(id, size));
    }


    //API CRUD Tag
    @Operation(summary = "Add Tag")
    @PostMapping("/{productID}/tag")
    public ResponseEntity<?> addTag(@PathVariable String productID,
                                     @RequestParam String tagID){
        return controllerWrapper(() -> productService.addTag(productID,tagID));
    }

    @Operation(summary = "Delete Tag")
    @DeleteMapping("/{productID}/tag")
    public ResponseEntity<?> deleteTag(@PathVariable String productID,
                                        @RequestParam String tagID){
        return controllerWrapper(() -> productService.deleteTag(productID,tagID));
    }
}

